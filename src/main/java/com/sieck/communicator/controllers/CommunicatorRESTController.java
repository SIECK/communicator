package com.sieck.communicator.controllers;

import com.sieck.communicator.domain.Message;
import com.sieck.communicator.services.MessageService;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CommunicatorRESTController {
    private MessageService messageService;

    public CommunicatorRESTController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping(value = "/messages")
    public @ResponseBody
    Message sendMessage(@RequestBody Message message){
        return messageService.sendMessage(message);
    }

    @GetMapping("/messages")
    public @ResponseBody
    Resources<Resource<Message>> all(){

        //Source of this piece of code:
        //https://spring.io/guides/tutorials/bookmarks/?fbclid=IwAR3_N0aoQ0Y0NtlaBQgHk77w_C_A4zGDGbmAaqYqqAZvjfOkITipGnjOBY4
        List<Resource<Message>> messages = messageService.getAllMessages().stream()
                .map(message -> new Resource<>(message,
                    linkTo(methodOn(CommunicatorRESTController.class).one(message.getMessageId())).withSelfRel(),
                    linkTo(methodOn(CommunicatorRESTController.class).all()).withRel("message")))
                .collect(Collectors.toList());

        return new Resources<>(messages,
                linkTo(methodOn(CommunicatorRESTController.class).all()).withSelfRel());
        //
    }

    @GetMapping("/messages/{id}")
    public @ResponseBody
    Resource<Message> one(@PathVariable("id") String id) {

        Message message = messageService.getMessageById(id);

        //Source of this piece of code:
        //https://spring.io/guides/tutorials/bookmarks/?fbclid=IwAR3_N0aoQ0Y0NtlaBQgHk77w_C_A4zGDGbmAaqYqqAZvjfOkITipGnjOBY4
        return new Resource<>(message,
                linkTo(methodOn(CommunicatorRESTController.class).one(id)).withSelfRel(),
                linkTo(methodOn(CommunicatorRESTController.class).all()).withRel("message"));
        //
    }

    @DeleteMapping("/messages/{id}")
    public @ResponseBody
    void deleteMessageById(@PathVariable("id") String id){messageService.deleteMessageById(id); }
}
