package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	private static Connection conn = null;
	
	public static Connection getConnection() {	
		try {
			
			try {//force connect with jdbc
				Class.forName("oracle.jdbc.driver.OracleDriver");
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		String[] creds = System.getenv("DBProject1").split(";");
		conn = DriverManager.getConnection(creds[0], creds[1], creds[2]);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return conn; //return the connection
	}
}

