package com.example.demo.service;

import javax.mail.MessagingException;

public interface CorreoService {
	public void mandarCorreo(String supervisor,String destinatario,String cc,String asunto,String cuerpo)  throws MessagingException;
}
