package com.usersDAB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	
    public Connection getConnection(){
    	System.out.println("Conectando ao banco de dados");

        try {
        	return  DriverManager.getConnection("jdbc:h2:~/test2","sa","sa");
        	} 
        catch (Exception e) {
        	throw new RuntimeException(e);
        }
    }
    
}