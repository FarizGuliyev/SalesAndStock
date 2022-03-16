package com.company.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.company.interfaces.CoreInterfaces;

public class ObjectHelper extends CoreFields implements CoreInterfaces {

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}

	}
	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection( getUrl(),getUserName(),getPassword());
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return connection;
	}

}
