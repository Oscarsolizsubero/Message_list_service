package com.websystique.springboot.service;


import java.util.Date;
import java.util.List;

import com.websystique.springboot.model.Message;
import com.websystique.springboot.model.User;

public interface MessageService {
	
	Message findById(long id);
	
	List<Message> findByUser(User user);
	
	void saveMessage(Message message);

	List<Message> findAllMessages();

	void seenMessages(User user, Date hora);
	
	
	
}
