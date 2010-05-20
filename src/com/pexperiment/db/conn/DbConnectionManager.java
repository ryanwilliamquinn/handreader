package com.pexperiment.db.conn;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DbConnectionManager {
	private static DbConnectionManager instance = null;
	private static Logger log = Logger.getLogger(DBConnector.class);

	private static String host;
	private static int port;
	private static String dbName;
	private static String username;
	private static String password;

	private DbConnectionManager() {
		try {
			getProperties();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static DbConnectionManager getInstance(){
		if (instance != null) {
			return instance;
		} else {
			instance = new DbConnectionManager();
			return instance;
		}
	}
	
	//a connection method that will return a DBConnector object
	public static DBConnector newConnection() throws SQLException{
		DbConnectionManager.getInstance();
		//creates a new DBConnector object, and initializes it with appropriate parameters
		DBConnector dbc = new DBConnector(host, port, dbName, username, password);
		
		log.info("DBConnector is instantiated");
		
		dbc.connect();
	
		log.info("DBConnector is connected via DbConnectionManager");
		
		return dbc; //returns DBConnector object dbc
	}
	
	public static void getProperties() throws IOException {
	
	
		Properties props = new Properties();
		ClassLoader cl = DBConnector.class.getClassLoader();
		
		
		java.net.URL url = cl.getResource("dbconn.properties");
	    log.info(url);
		props.load(url.openStream());
	    System.out.println(props);
		
		//initialize variables
		//tComment: slightly different way of initializing variables. Your way was fine too, just a bit redundant. 
		host = props.getProperty("host");
		port = Integer.parseInt(props.getProperty("port"));
		dbName = props.getProperty("name");
		username = props.getProperty("username");
		password = props.getProperty("password");
		
		//print to console to see that everything makes sense
		//tComment: consider logging vs doing System.out
		System.out.println("Here are the connection details: " + host + " " + port + " " + dbName + " " + username + " " + password);
	}

}





	
	
	
