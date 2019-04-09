package com.sieck.communicator;

import com.sieck.communicator.domain.Message;
import com.sieck.communicator.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class CommunicatorApplication implements CommandLineRunner{

	@Autowired
	MessageService messageService;

	public static void main(String[] args) {
		SpringApplication.run(CommunicatorApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Date date = new Date();
		Message message = new Message("TXT", date, "AUT");
		messageService.send(message);
	}
}
