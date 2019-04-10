package com.sieck.communicator;

import com.sieck.communicator.domain.Message;
import com.sieck.communicator.domain.Picture;
import com.sieck.communicator.services.MessageService;
import com.sieck.communicator.services.PictureService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommunicatorApplicationTests {

	@Autowired
	MessageService messageService;

	@Autowired
	PictureService pictureService;

	private Date date;
	private Message message;

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
		messageService.send(message);
		assertEquals(message, messageService.getById(message.getMessageId()));
	}

	@Test
	public void messageShouldBeDeleted(){
		messageService.send(message);
		assertEquals(message, messageService.getById(message.getMessageId()));
		messageService.deleteById(message.getMessageId());
		assertFalse(messageService.getAll().contains(message.getMessageId()));
	}
}
