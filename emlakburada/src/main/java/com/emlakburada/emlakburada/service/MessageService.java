package com.emlakburada.emlakburada.service;

import com.emlakburada.emlakburada.core.Result;
import com.emlakburada.emlakburada.dto.request.MessageRequest;
import com.emlakburada.emlakburada.dto.response.MessageResponse;

import java.util.List;

public interface MessageService {
    List<MessageResponse> getAll();
    MessageResponse getMessageById(Integer id);
    MessageResponse add(MessageRequest messageRequest);
    MessageResponse update(MessageRequest messageRequest);
    Result deleteById(Integer id);
}