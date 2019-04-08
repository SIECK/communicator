package com.sieck.communicator.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.ResourceSupport;

import java.util.Date;

@Data
@Document
public class Message {

    @Id
    private String messageId;

    private String text;
    private Date date;
    private String author;

    public Message(){

    }

    public Message(String text, Date date, String author) {
        this.text = text;
        this.date = date;
        this.author = author;
    }
}
