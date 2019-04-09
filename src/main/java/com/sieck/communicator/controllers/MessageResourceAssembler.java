package com.sieck.communicator.controllers;

import com.sieck.communicator.domain.Message;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

//Source of this piece of code:
//https://spring.io/guides/tutorials/bookmarks/?fbclid=IwAR3_N0aoQ0Y0NtlaBQgHk77w_C_A4zGDGbmAaqYqqAZvjfOkITipGnjOBY4
@Component
public class MessageResourceAssembler implements ResourceAssembler<Message, Resource<Message>> {
    @Override
    public Resource<Message> toResource(Message message){
        return new Resource<>(message,
                linkTo(methodOn(CommunicatorRESTController.class).oneMessage(message.getMessageId())).withSelfRel(),
                linkTo(methodOn(CommunicatorRESTController.class).allMessages()).withRel("message"));
    }
}
//