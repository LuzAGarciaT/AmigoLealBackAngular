package com.example.ElAmigoLeal.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClienteController {
	
	@GetMapping("/cliente")//YA
	public String mostrarLogin() {
		return "/usuario/login";
	}
	
	@GetMapping("/cliente/usuario")//YA
	public String mostrarUsuario() {
		return "/usuario/usuario";
	}
	@GetMapping("/cliente/inicious")//
	public String mostrarInicioUsuario() {
		return "/usuario/inicious";
	}
	@GetMapping("/cliente/contactous")//
	public String mostrarContactousario() {
		return "/usuario/contactous";
	}
	@GetMapping("/cliente/catalogous")//
	public String mostrarCatalogo() {
		return "/usuario/catalogous";
	}
	@GetMapping("/cliente/nosotrosus")//
	public String mostrarNosotros() {
		return "/usuario/nosotrosus";
	}


}
