package com.websystique.springboot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.websystique.springboot.model.User;
import com.websystique.springboot.model.Message;


@Service("messageService")
public class MessageServiceImpl implements MessageService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<Message> messages;
	private List<Message> messageperuser= new ArrayList<Message>();
	
	
	static{
		messages= populateDummyMessages();
	}

	public List<Message> findAllMessages() {
		return messages;
	}
	
	public Message findById(long id) {
		for(Message message : messages){
			if(message.getId() == id){
				return message;
			}
		}
		return null;
	}
	
	
	
	public void saveMessage(Message message) {
		message.setId(counter.incrementAndGet());
		messages.add(message);
	}

	
	public List<Message> findByUser(User user) {
		for(Message message : messages){
			if(message.getUser2().getId() == user.getId()){
				messageperuser.add(message);
			}
		}
		return messageperuser;
	}

	private static List<Message> populateDummyMessages(){
		List<Message> messages = new ArrayList<Message>();
		
		
		return messages;
	}

	public void seenMessages(User user, Date hora)
	{
		for(Message message : messages){
			if(message.getUser2().getId() == user.getId()){
				message.setSeen(hora);
			}
		}
	}
	
	
}
