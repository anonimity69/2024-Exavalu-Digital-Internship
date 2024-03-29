package exavalu.com.utilities;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.exavalu.pojos.PropertyValues;
 
public class DbConnectionProvider {
	
	
	private static DbConnectionProvider dbConnectionProvider = null;
	
	private DbConnectionProvider() {
		
	}
	
	public static DbConnectionProvider getInstance() {
		if(dbConnectionProvider == null) {
			dbConnectionProvider = new DbConnectionProvider();
			System.out.println("Returning a new connection");
		} else {
			System.out.println("Returning an existing connection");
		}
		return dbConnectionProvider;
	}
	
	public  Connection getDbConnection(PropertyValues propertyValues) 
	{
		Connection con = null;
		
		String url = propertyValues.getUrl();
		String dbname= propertyValues.getDbname();
		String user = propertyValues.getUser();
		String password = propertyValues.getPassword();
		
		//Class loader - initializing a class
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			//Will give Driver Manager
			con = DriverManager.getConnection(url+dbname, user, password);
			System.out.println("DB Connection ="+con);
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return con;
	}
 
}
 