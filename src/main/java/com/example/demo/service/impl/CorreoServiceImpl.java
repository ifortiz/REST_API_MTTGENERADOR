package com.example.demo.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.service.CorreoService;

@Service
public class CorreoServiceImpl  implements CorreoService{
	private static final String TEXT_UNICODE="UTF-8";
	private static final  String SEPARATOR_FORMATO_CORREO="¬";
	
	 @Autowired
	 private JavaMailSender emailSender;
	 
	 
	 @Async
	 @Override
	public void mandarCorreo(String supervisor,String destinatario,String cc,String asunto,String cuerpo)  throws MessagingException {
 
       try {
           MimeMessage message = emailSender.createMimeMessage();
           MimeMessageHelper helper = new MimeMessageHelper(message, true, TEXT_UNICODE);
           
            helper.setTo(destinatario);
            if (!cc.trim().isEmpty())
            	helper.addCc(cc);
            helper.addCc(supervisor);
            helper.setSubject(asunto);
            helper.setText(cuerpo, true);
            
            emailSender.send(message);
        
        } catch (MessagingException e) {
            throw new RuntimeException(e);      
        }
    }
	
}
