package com.emlakburada.emlakburada.service;

import com.emlakburada.emlakburada.dto.request.MessageRequest;
import com.emlakburada.emlakburada.dto.response.MessageResponse;
import com.emlakburada.emlakburada.model.Message;
import org.springframework.stereotype.Service;

@Service
public class MessageBaseService {

    protected Message convertToMessageEntity(MessageRequest messageRequest) {
        Message message = new Message();
        message.setTitle(messageRequest.getTitle());
        message.setDescription(messageRequest.getDescription());
        //message.setSender(messageRequest.getSender());
        //message.setReceiver(messageRequest.getReceiver());
        return message;
    }

    protected MessageResponse convertToMessageResponse(Message message) {
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setTitle(message.getTitle());
        messageResponse.setDescription(message.getDescription());
        //messageResponse.setSender(message.getSender());
        //messageResponse.setReceiver(message.getReceiver());
        return messageResponse;
    }
}