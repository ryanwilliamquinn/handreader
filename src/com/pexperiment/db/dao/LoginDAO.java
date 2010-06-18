package com.pexperiment.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.pexperiment.db.conn.DBConnector;
import com.pexperiment.db.conn.DbConnectionManager;
import com.pexperiment.model.Login;

public class LoginDAO {
	
	static DBConnector dbConn;
	
	public Login select(String playerName) throws SQLException{		
		Login login = null;				
		dbConn = DbConnectionManager.newConnection();		
		try{
			String sql = "SELECT * FROM login WHERE playerName = '" + playerName;			
			ResultSet rs = dbConn.execSql(sql);			
			while (rs.next()){
				login = new Login(rs.getString("playerName"), rs.getString("password"));
			}
		} finally { if (dbConn != null) dbConn.disconnect(); }		
		
		return login;
	}
	
	public void insert(Login login) throws SQLException{		
		dbConn = DbConnectionManager.newConnection();		
		try{
			String sql = "INSERT INTO login (playerName, password) VALUES ('" + login.getPlayerName() + "', '" + login.getPassword() + "')";	
			dbConn.execSql(sql);			
		} finally { if (dbConn != null) dbConn.disconnect(); }
		
	}
	
	public void update(Login login) throws SQLException{
		dbConn = DbConnectionManager.newConnection();
		try{
			String sql = "UPDATE login SET password = '" +login.getPassword()+"' WHERE playerName = '" +login.getPlayerName()+ "'";					
			dbConn.execSql(sql);		
		} finally { if (dbConn != null) dbConn.disconnect(); }
	}
		
	public void delete(Login login) throws SQLException{
		dbConn = DbConnectionManager.newConnection();

		try{
			String sql = "DELETE FROM login WHERE playerName = '" + login.getPlayerName() + "'";
			dbConn.execSql(sql);		
		} finally { if (dbConn != null) dbConn.disconnect(); }
	}	
	
}
