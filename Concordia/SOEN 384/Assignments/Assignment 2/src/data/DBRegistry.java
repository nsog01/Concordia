package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import technical.Registry;

/**
 * @author  paulsmelser
 */
public final class DBRegistry {
	
	/*
	 * Hard-coded properties
	 */
	private static String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	private static String LOCATION = "jdbc:mysql://soen390.db.7953538.hostedresource.com/soen390";
	private static String USER = "soen390";
	private static String PASSWORD = "Pass390";
	private static String LOCALLOCATION = "jdbc:mysql://localhost/soen390";
	private static String LOCALUSER = "soen390";
	private static String LOCALPASSWORD = "Pass390";

	private Connection dbConnection;
	/**
	 * @uml.property  name="soleInstance"
	 * @uml.associationEnd  
	 */
	private static DBRegistry soleInstance;
		
	private DBRegistry() {}
	
	public synchronized static DBRegistry getUniqueInstance() {
		if(soleInstance == null){
    		soleInstance = new DBRegistry();
		}
        return soleInstance;
	}
	
	private void initDBConnection() {
		try {
			DRIVER_CLASS = Registry.getUniqueInstance().getProperty("DBDriver");
			LOCATION = Registry.getUniqueInstance().getProperty("DBName");
			USER = Registry.getUniqueInstance().getProperty("DBUser");
			PASSWORD = Registry.getUniqueInstance().getProperty("DBPass");
			LOCALLOCATION = Registry.getUniqueInstance().getProperty("DBLocalName");
			LOCALUSER = Registry.getUniqueInstance().getProperty("DBLocalUser");
			LOCALPASSWORD = Registry.getUniqueInstance().getProperty("DBLocalPass");
				
			Class.forName(DRIVER_CLASS);
			dbConnection = DriverManager.getConnection(LOCATION, USER, PASSWORD);
		} 
		catch (Exception e) {
			try {
			Class.forName(DRIVER_CLASS);
				System.out.println("oh no can't connect");
				dbConnection = DriverManager.getConnection(LOCALLOCATION, LOCALUSER, LOCALPASSWORD);
			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e2) {
				e.printStackTrace();
			} finally {
			}
		}
	}
	
	public boolean isConnected() throws SQLException {
		if (dbConnection != null){
			return !dbConnection.isClosed();
		} else {
			return false;
		}
	}
	
	public Connection getDBConnection() throws SQLException {
		if (!isConnected()) {
			initDBConnection();
		}
		return dbConnection;
	}
	
	public void closeDbConnection() throws SQLException  {
		if (isConnected()) {
			dbConnection.close();
			dbConnection = null;
		}
	}
}
