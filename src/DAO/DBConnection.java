package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static Connection getConnection() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");

		Connection conn = null;
		try {
			String user = "user"; // window
			String pw = "513246";
			String url = "jdbc:mysql://localhost:3306/smoke";

			conn = DriverManager.getConnection(url, user, pw);
		} catch (SQLException sqle) {
			System.out.println("DB error : " + sqle.toString());
		} catch (Exception e) {
			System.out.println("Unkonwn error");
		}
		return conn;
	}

}
