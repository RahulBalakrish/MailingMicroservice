package com.mailService.Mailing.controller;

import com.mailService.Mailing.Model.EmailRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import org.slf4j.Logger;

@RestController
public class gmailServiceController {
    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request) {
        try {
            Logger logger =  LoggerFactory.getLogger(gmailServiceController.class);
            logger.info("Inside Api");
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(request.getToAddress());
            helper.setSubject(request.getSubject());
            helper.setText(request.getContent(), true);
            javaMailSender.send(message);
            return new ResponseEntity<String>("Email Sent", HttpStatus.OK);
        } catch (MessagingException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }
}
