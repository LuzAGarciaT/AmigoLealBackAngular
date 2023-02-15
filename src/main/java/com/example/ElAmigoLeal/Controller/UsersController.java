package com.example.ElAmigoLeal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ElAmigoLeal.Impl.UsersService;

@CrossOrigin(origins = "*", methods = { RequestMethod.PUT, RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE })
@RestController
public class UsersController {

	@Autowired
	private UsersService usersservice;
	
	@GetMapping("users/{correo}/{password}")
	public int UsersLogin(@PathVariable("correo") String correo1, @PathVariable ("password") String password1) {
		 
		int flag = usersservice.loginValidation(correo1, password1);
		
		return flag;
	}
}
