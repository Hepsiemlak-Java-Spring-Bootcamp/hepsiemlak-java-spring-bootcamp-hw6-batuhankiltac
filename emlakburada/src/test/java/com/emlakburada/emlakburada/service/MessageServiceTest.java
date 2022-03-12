package com.emlakburada.emlakburada.service;

import com.emlakburada.emlakburada.dto.request.MessageRequest;
import com.emlakburada.emlakburada.dto.response.MessageResponse;
import com.emlakburada.emlakburada.model.Message;
import com.emlakburada.emlakburada.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class MessageServiceTest {

    @InjectMocks
    private MessageServiceImpl messageService;

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private MessageBaseService messageBaseService;

    @Test
    void addMessageTest() {
        MessageRequest messageRequest = prepareMessageRequest();
        Optional<Message> message = Optional.of(prepareMessage());

        Mockito
                .when(messageRepository.findById(messageRequest.getId()))
                .thenReturn(message);

        Mockito
                .when(messageRepository.save(any()))
                .thenReturn(prepareMessage());

        Mockito
                .when(messageBaseService.convertToMessageResponse(prepareMessage()))
                .thenReturn(prepareMessageResponse());

        MessageResponse messageResponse = messageService.add(messageRequest);
        assertEquals("test-title", messageResponse.getTitle());
    }

    private MessageRequest prepareMessageRequest() {
        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setId(1);
        messageRequest.setTitle("test-title");
        messageRequest.setDescription("test-description");
        return messageRequest;
    }

    private Message prepareMessage() {
        Message message = new Message();
        message.setId(1);
        message.setTitle("test-title");
        message.setDescription("test-description");
        return message;
    }

    @Test
    void getAllMessagesTest() {
        List<MessageResponse> responseList = messageService.getAll();

        Mockito.when(messageRepository.findAll()).thenReturn(prepareMessageList());

        Mockito
                .when(messageBaseService.convertToMessageResponse(prepareMessage()))
                .thenReturn(prepareMessageResponse());

        assertEquals(0, responseList.size());
        assertThat(responseList.size());

        for (MessageResponse response : responseList) {
            assertThat(response.getTitle());
            assertEquals("test-title", response.getTitle());
            assertEquals("test-description", response.getDescription());
        }
    }

    private List<Message> prepareMessageList() {
        List<Message> messageList = new ArrayList<>();
        messageList.add(prepareMessage());
        return messageList;
    }

    @Test
    void getMessageByIdTest() {
        Mockito
                .when(messageRepository.findById(1))
                .thenReturn(Optional.of(prepareMessage()));

        Mockito
                .when(messageBaseService.convertToMessageResponse(prepareMessage()))
                .thenReturn(prepareMessageResponse());

        MessageResponse messageResponse = messageService.getMessageById(1);
        assertNotNull(messageResponse);
        assertEquals("test-title", messageResponse.getTitle());
        assertEquals("test-description", messageResponse.getDescription());
    }

    private MessageResponse prepareMessageResponse() {
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setTitle("test-title");
        messageResponse.setDescription("test-description");
        messageResponse.setIsSeen(true);
        messageResponse.setSentDate(new Date());
        messageResponse.setReadDate(new Date());
        return messageResponse;
    }

    @Test
    void updateMessageTest() {
        MessageRequest messageRequest = prepareMessageRequest();

        Mockito
                .when(messageRepository.save(any()))
                .thenReturn(prepareMessage());

        Mockito
                .when(messageRepository.findById(any()))
                .thenReturn(Optional.of(prepareMessage()));

        Mockito
                .when(messageBaseService.convertToMessageResponse(prepareMessage()))
                .thenReturn(prepareMessageResponse());

        MessageResponse messageResponse = messageService.update(messageRequest);
        assertEquals("test-title", messageResponse.getTitle());
    }

    @Test
    void deleteMessageByIdTest() {
        Mockito
                .doNothing().when(messageRepository)
                .deleteById(1);

        Mockito
                .when(messageRepository.findById(1))
                .thenReturn(Optional.of(prepareMessage()));

        messageService.deleteById(1);
        verify(messageRepository).deleteById(1);
    }
}