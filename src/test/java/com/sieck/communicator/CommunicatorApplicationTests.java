package com.sieck.communicator;

import com.sieck.communicator.domain.Message;
import com.sieck.communicator.services.MessageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommunicatorApplicationTests {

	@Autowired
	MessageService messageService;

	Date date;
	Message message;

	@Before
	public void initializeObjects(){
		date = new Date();
		message = new Message("test message", date, "test author");
	}

	@Test
	public void messageShouldBeCreated(){
		assertEquals("test message", message.getText());
		assertEquals(date, message.getDate());
		assertEquals("test author", message.getAuthor());
	}

	@Test
	public void messageShouldBeSent(){
		messageService.sendMessage(message);
		assertEquals(message, messageService.getMessageById(message.getMessageId()));
	}

	@Test
	public void messageShouldBeDeleted(){
		messageService.sendMessage(message);
		assertEquals(message, messageService.getMessageById(message.getMessageId()));
		messageService.deleteMessageById(message.getMessageId());
		assertFalse(messageService.getAllMessages().contains(message.getMessageId()));
	}
}
