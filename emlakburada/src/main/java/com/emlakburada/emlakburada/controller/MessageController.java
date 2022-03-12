package com.emlakburada.emlakburada.controller;

import com.emlakburada.emlakburada.core.Result;
import com.emlakburada.emlakburada.dto.request.MessageRequest;
import com.emlakburada.emlakburada.dto.response.MessageResponse;
import com.emlakburada.emlakburada.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping(value = "/messages")
    public ResponseEntity<MessageResponse> addMessage(@RequestBody MessageRequest messageRequest) {
        return new ResponseEntity<>(messageService.add(messageRequest), HttpStatus.CREATED);
    }

    @GetMapping(value = "/messages")
    public ResponseEntity<List<MessageResponse>> getAllMessages() {
        return new ResponseEntity<>(messageService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/messages/{id}")
    public ResponseEntity<MessageResponse> getMessageById(@PathVariable Integer id) {
        return new ResponseEntity<>(messageService.getMessageById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/messages")
    public ResponseEntity<MessageResponse> updateMessage(@RequestBody MessageRequest messageRequest) {
        return new ResponseEntity<>(messageService.update(messageRequest), HttpStatus.OK);
    }

    @DeleteMapping(value = "/messages/{id}")
    public ResponseEntity<Result> deleteMessageById(@PathVariable Integer id) {
        return new ResponseEntity<>(messageService.deleteById(id), HttpStatus.OK);
    }
}