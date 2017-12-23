package com.websystique.springboot.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.websystique.springboot.model.Message;
import com.websystique.springboot.model.User;
import com.websystique.springboot.service.MessageService;
import com.websystique.springboot.service.UserService;
import com.websystique.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	UserService userService; //Service which will do all data retrieval/manipulation work
	@Autowired
	MessageService messageService;
	// -------------------Retrieve All Users---------------------------------------------

	//@RequestMapping(value = "/message/", method = RequestMethod.GET)
	
	
	
	
	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userService.findAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	// -------------------Retrieve Single User------------------------------------------

	@RequestMapping(value = "/user/{name}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable("name") String name) {
		logger.info("Fetching User with name {}", name);
		User user = userService.findByName(name);
		if (user == null) {
			logger.error("User with name {} not found.", name);
			return new ResponseEntity(new CustomErrorType("User with name " + name 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// -------------------Create a User-------------------------------------------

	@RequestMapping(value = "/user/{name}", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@PathVariable("name")String name, User user, UriComponentsBuilder ucBuilder) {
		logger.info("Creating User : {}", user);

		user.setName(name);
		if (userService.isUserExist(user)) {
			logger.error("Unable to create. A User with name {} already exist", user.getName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A User with name " + 
			user.getName() + " already exist."),HttpStatus.CONFLICT);
		}
		userService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a User ------------------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		logger.info("Updating User with id {}", id);

		User currentUser = userService.findById(id);

		if (currentUser == null) {
			logger.error("Unable to update. User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentUser.setName(user.getName());
		

		userService.updateUser(currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

	// ------------------- Delete a User-----------------------------------------

	@RequestMapping(value = "/user/{name}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("name") String name) {
		logger.info("Fetching & Deleting User with name {}", name);

		User user = userService.findByName(name);
		if (user == null) {
			logger.error("Unable to delete. User with name {} not found.", name);
			return new ResponseEntity(new CustomErrorType("Unable to delete. User with name " + name + " not found."),
					HttpStatus.NOT_FOUND);
		}
		userService.deleteUserByName(name);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Users-----------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteAllUsers() {
		logger.info("Deleting All Users");

		userService.deleteAllUsers();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
	
	
	@RequestMapping(value = "/message/{user1}/{user2}", method = RequestMethod.POST)
	public ResponseEntity<?> createMessage(@PathVariable("user1")String user1n,@PathVariable("user2") String user2n,@RequestParam String msg, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Message : {}", msg);
		Date hora = new Date();
		User user1 = userService.findByName(user1n);
		User user2 = userService.findByName(user2n);
		Message message= new Message();
		
		
		message.setUser1(user1);
		message.setUser2(user2);
		message.setSent(hora);
		
		message.setMsg(msg);
		
		if (userService.isUserExist(user1)&&(userService.isUserExist(user2))) {
			messageService.saveMessage(message);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/api/Message/{user2}/").buildAndExpand(user2.getName()).toUri());
			return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		}
		else{
			logger.error("Unable to create message user does not exist");
			return new ResponseEntity(new CustomErrorType("Unable to create message user does not exist"),HttpStatus.CONFLICT);
		}
		
		
	}
	
	
	@RequestMapping(value = "/message/{user1}", method = RequestMethod.GET)
	public ResponseEntity<List<Message>> listAllMessage(@PathVariable("user1") String user1){
		User user;
		user = userService.findByName(user1);
		Date hora = new Date();
		logger.info("Searching Messages of : {}", user1);
		List<Message> Messages = messageService.findByUser(user);
		if (!userService.isUserExist(user)) {
			logger.error("user does not exist");
			return new ResponseEntity(new CustomErrorType("user does not exist"),HttpStatus.CONFLICT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		else
		{
			messageService.seenMessages(user, hora);
			return new ResponseEntity<List<Message>>(Messages, HttpStatus.OK);
		}
		
	}

}