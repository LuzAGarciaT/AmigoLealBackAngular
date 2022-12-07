package com.example.ElAmigoLeal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.ElAmigoLeal.Entity.Rol;
import com.example.ElAmigoLeal.Repository.RolRepository;

@SpringBootApplication
public class ElAmigoLealApplication  implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ElAmigoLealApplication.class, args);
	}

	@Autowired
	private RolRepository rolrepository;
	
	@Override
	public void run(String... args) throws Exception {
		/*
		Rol rol1 = new Rol("rolcito");
		rolrepository.save(rol1);
		
		Rol rol2 = new Rol("rolcito2");
		rolrepository.save(rol2);
	*/
	}
}
