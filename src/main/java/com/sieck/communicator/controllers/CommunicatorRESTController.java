package com.sieck.communicator.controllers;

import com.mongodb.client.gridfs.model.GridFSFile;
import com.sieck.communicator.domain.Message;
import com.sieck.communicator.domain.Picture;
import com.sieck.communicator.services.MessageService;
import com.sieck.communicator.services.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.data.mongodb.gridfs.GridFsOperations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CommunicatorRESTController {
    private MessageService messageService;
    private MessageResourceAssembler messageResourceAssembler;
    private PictureService pictureService;

    public CommunicatorRESTController(MessageService messageService, MessageResourceAssembler messageResourceAssembler, PictureService pictureService) {
        this.messageService = messageService;
        this.messageResourceAssembler = messageResourceAssembler;
        this.pictureService = pictureService;
    }

    @PostMapping(value = "/messages")
    public @ResponseBody
    void sendMessage(@RequestBody Message message){
        messageService.send(message);
    }

    @GetMapping("/messages")
    public @ResponseBody
    Resources<Resource<Message>> allMessages(){

        //Source of this piece of code:
        //https://spring.io/guides/tutorials/bookmarks/?fbclid=IwAR3_N0aoQ0Y0NtlaBQgHk77w_C_A4zGDGbmAaqYqqAZvjfOkITipGnjOBY4
        List<Resource<Message>> messages = messageService.getAll().stream()
                .map(messageResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(messages,
                linkTo(methodOn(CommunicatorRESTController.class).allMessages()).withSelfRel());
        //
    }

    @GetMapping("/messages/{id}")
    public @ResponseBody
    Resource<Message> oneMessage(@PathVariable("id") String id) {

        //Source of this piece of code:
        //https://spring.io/guides/tutorials/bookmarks/?fbclid=IwAR3_N0aoQ0Y0NtlaBQgHk77w_C_A4zGDGbmAaqYqqAZvjfOkITipGnjOBY4
        Message message = messageService.getById(id);
        return messageResourceAssembler.toResource(message);
        //
    }

    @DeleteMapping("/messages/{id}")
    public @ResponseBody
    void deleteMessageById(@PathVariable("id") String id) { messageService.deleteById(id); }

    @PostMapping(value = "/images")
    public @ResponseBody
    void sendPicture(@RequestBody Picture picture) throws FileNotFoundException {
        pictureService.send(picture);
    }

    @GetMapping(value = "/images/{id}")
    public @ResponseBody
    String onePicture(@PathVariable("id") String id) {
        return pictureService.getById(id);
    }

    @DeleteMapping("/images/{id}")
    public @ResponseBody
    void deletePictureById(@PathVariable("id") String id){
        pictureService.deleteById(id);
    }
}
