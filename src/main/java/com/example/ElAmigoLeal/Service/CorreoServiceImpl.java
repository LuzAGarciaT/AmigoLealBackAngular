package com.example.ElAmigoLeal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.ElAmigoLeal.Entity.Rol;
import com.example.ElAmigoLeal.Entity.Usuario;
import com.example.ElAmigoLeal.Impl.CorreoService;
import com.example.ElAmigoLeal.Impl.UsuarioService;

@Service
public class CorreoServiceImpl implements CorreoService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public void enviarcorreo(String asunto, String mensaje) {
		Rol rol = new Rol();
		rol.setIdrol(2);
		List<Usuario> usuarios = usuarioService.findByRol(rol);

		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom("amigoleal492@gmail.com");

		for (Usuario u : usuarios) {
			message.setTo(u.getCorreo());
			message.setSubject(asunto);
			message.setText(mensaje);
			mailSender.send(message);
		}
	}
}
