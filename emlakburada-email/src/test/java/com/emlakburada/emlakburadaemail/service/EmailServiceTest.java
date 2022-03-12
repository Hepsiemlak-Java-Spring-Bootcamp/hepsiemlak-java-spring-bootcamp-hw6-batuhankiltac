package com.emlakburada.emlakburadaemail.service;

import com.emlakburada.emlakburadaemail.config.EmailConfig;
import com.emlakburada.emlakburadaemail.model.Email;
import com.emlakburada.emlakburadaemail.repository.EmailRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;

    @Mock
    private EmailConfig config;

    @Mock
    private EmailRepository emailRepository;

    @BeforeEach
    void init() {
        Mockito
                .when(config.getSmtpServer())
                .thenReturn("smtp.gmail.com");

        Mockito
                .when(config.getSmtpPort())
                .thenReturn("587");

        Mockito
                .when(config.getFrom())
                .thenReturn("emlakburada.patika@gmail.com");

        Mockito
                .when(config.getUsername())
                .thenReturn("username");

        Mockito
                .when(config.getPassword())
                .thenReturn("EmlakBurada2022");

        Mockito
                .when(emailRepository.save(any()))
                .thenReturn(prepareEmail());
    }

    @Test
    void sendEmailTest() {
        assertThrows(MessagingException.class, () -> {
            emailService.sendEmail("batuhan@gmail.com");
        });
    }

    @Test
    void saveEmailTest() {
        assertDoesNotThrow(() -> {
            emailService.saveEmail(prepareEmail());
        });

        verify(emailRepository).save(any());
    }

    private Email prepareEmail() {
        Email email = new Email();
        email.setId(1);
        email.setEmailMessage("test-message");
        return email;
    }
}