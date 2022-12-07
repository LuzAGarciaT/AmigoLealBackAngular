package com.example.ElAmigoLeal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.ElAmigoLeal.Entity.Rol;
import com.example.ElAmigoLeal.Impl.UsuarioService;
import com.example.ElAmigoLeal.Service.SedMailService;

@Controller
public class SinRegistarController {
	
	@Autowired
	private UsuarioService usuarioservice;
	
	@Autowired 
	private SedMailService sedmailservice;
	
	@GetMapping("/")//YA
	public String mostrarInicio() {
		return "sinRegistar/inicio";
	}
	@GetMapping("/nosotros")//Ya
	public String mostrarNosotros() {
		return "sinRegistar/nosotros";
	}
	
	@GetMapping("/catalogo")//YA
	public String mostrarCatalogo() {
		return "/sinRegistar/catalogo";
	}
	
	@GetMapping("/notificaciones")//YA
	public String mostrarNotificacion() {
		return "/sinRegistar/notificaciones";
	}
	@PostMapping("/sendMail")
	public String sendmail(@RequestParam("subject") String subject, @RequestParam("body") String body) {
		Rol rol = new Rol();
		rol.setIdrol(2);
		sedmailservice.notificar("amigoleal492@gmail.com", usuarioservice.findByRol(rol), subject, body);
		return "/sinRegistrar/notificaciones";
		
	}
	
}

	

