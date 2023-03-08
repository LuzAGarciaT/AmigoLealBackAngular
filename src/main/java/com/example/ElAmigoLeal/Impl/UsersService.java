package com.example.ElAmigoLeal.Impl;

import org.springframework.stereotype.Repository;

@Repository
public interface UsersService {

	public int loginValidation(String correo, String password);
}
 