package com.example.ElAmigoLeal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.ElAmigoLeal.Entity.Usuario;

@Service
public class SedMailService {
	
	@Autowired
	private JavaMailSender javamailsender;
	

    public void send(String from, String to, String subject, String body) {
        SimpleMailMessage mailmessage = new SimpleMailMessage();
        mailmessage.setFrom(from);
        mailmessage.setTo(to);
        mailmessage.setSubject(subject);
        mailmessage.setText(body);
        
        javamailsender.send(mailmessage);
    }
    
    public void contra(String from, String to, String subject, String body) {
        SimpleMailMessage mailmessage = new SimpleMailMessage();
        mailmessage.setFrom(from);
        mailmessage.setTo(to);
        mailmessage.setSubject(subject);
        mailmessage.setText(body);
        
        javamailsender.send(mailmessage);
    }
    
    public void notificar(String from, List<Usuario> listaUsuarios, String subject, String body) {
    	
    	for(Usuario u: listaUsuarios) {
        SimpleMailMessage mailmessage = new SimpleMailMessage();
        mailmessage.setFrom(from);
        mailmessage.setTo(u.getCorreo());
        mailmessage.setSubject(subject);
        mailmessage.setText(body);
        
        javamailsender.send(mailmessage);
    	}
    }
    
    

}
