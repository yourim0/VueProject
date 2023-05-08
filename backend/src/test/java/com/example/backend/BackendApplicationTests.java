package com.example.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootTest
class BackendApplicationTests {
	private final static String url = "jdbc:mariadb://localhost:3306/gallery";
	private final static String user = "root";
	private final static String password = "1111";

	@Test
	void contextLoads() {
		Connection c = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("connecting");
			c = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println("cannot connect the database");

		}

	}
}