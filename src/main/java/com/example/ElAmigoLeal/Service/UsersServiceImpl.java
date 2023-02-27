package com.example.ElAmigoLeal.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.example.ElAmigoLeal.Impl.UsersService;
import com.example.ElAmigoLeal.Utilities.LoginUtil;

@Service
public class UsersServiceImpl  implements UsersService {

	Connection connection;
	int flag = 0;
	
	public UsersServiceImpl() throws SQLException {
		connection = LoginUtil.getConnection();
		}
	
	@Override
	public int loginValidation(String correo, String password) {

		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM usuarios WHERE correo = '"+correo+"'");
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) 
			{
				if(rs.getString(8).equals(correo) && rs.getString(9).equals(password)) {
					flag = rs.getInt(1);
				}else {
					System.out.println("Correo/contraseña invalidos");
					flag = 0;
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return flag;
	}

}