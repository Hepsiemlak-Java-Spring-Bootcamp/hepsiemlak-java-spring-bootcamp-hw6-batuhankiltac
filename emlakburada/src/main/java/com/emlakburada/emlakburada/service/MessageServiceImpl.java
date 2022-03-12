package com.emlakburada.emlakburada.service;

import com.emlakburada.emlakburada.core.Result;
import com.emlakburada.emlakburada.dto.request.MessageRequest;
import com.emlakburada.emlakburada.dto.response.MessageResponse;
import com.emlakburada.emlakburada.model.Message;
import com.emlakburada.emlakburada.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final MessageBaseService messageBaseService;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, MessageBaseService messageBaseService) {
        this.messageRepository = messageRepository;
        this.messageBaseService = messageBaseService;
    }

    @Override
    public List<MessageResponse> getAll() {
        List<MessageResponse> messageList = new ArrayList<>();
        for (Message message : messageRepository.findAll()) {
            messageList.add(messageBaseService.convertToMessageResponse(message));
        }
        return messageList;
    }

    @Override
    public MessageResponse getMessageById(Integer id) {
        Optional<Message> message = messageRepository.findById(id);
        return messageBaseService.convertToMessageResponse(message.get());
    }

    @Override
    public MessageResponse add(MessageRequest messageRequest) {
        Message message = messageBaseService.convertToMessageEntity(messageRequest);
        return messageBaseService.convertToMessageResponse(messageRepository.save(message));
    }

    @Override
    public MessageResponse update(MessageRequest messageRequest) {
        getMessageById(messageRequest.getId());
        Message message = messageBaseService.convertToMessageEntity(messageRequest);
        return messageBaseService.convertToMessageResponse(messageRepository.save(message));
    }

    @Override
    public Result deleteById(Integer id) {
        getMessageById(id);
        messageRepository.deleteById(id);
        return new Result(true, "Message has been deleted.");
    }
}