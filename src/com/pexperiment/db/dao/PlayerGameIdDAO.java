package com.pexperiment.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.pexperiment.db.conn.DBConnector;
import com.pexperiment.db.conn.DbConnectionManager;
import com.pexperiment.model.PlayerGameId;

public class PlayerGameIdDAO {
	
	static DBConnector dbConn;
	
	public PlayerGameId select(String playerName, String gameId) throws SQLException{		
		PlayerGameId playerGameId = null;			
		dbConn = DbConnectionManager.newConnection();		
		try{
			String sql = "SELECT * FROM playerGameId WHERE playername = '" + playerName + "' and gameId = '" + gameId + "';";			
			ResultSet rs = dbConn.execSql(sql);			
			while (rs.next()){
				playerGameId = new PlayerGameId(rs.getString("playerName"), rs.getString("gameId"));
			}
		} finally { if (dbConn != null) dbConn.disconnect(); }		
		
		return playerGameId;
	}
	
	public void insert(PlayerGameId playerGameId) throws SQLException{		
		dbConn = DbConnectionManager.newConnection();		
		try{
			String sql = "INSERT INTO playerGameId (playerName, gameId) VALUES ('" + playerGameId.getPlayerName() + "', '" + playerGameId.getGameId() + "')";	
			dbConn.execSql(sql);			
		} finally { if (dbConn != null) dbConn.disconnect(); }
		
	}
	
	public void update(PlayerGameId playerGameId) throws SQLException{
		dbConn = DbConnectionManager.newConnection();
		try{
			String sql = "UPDATE playerGameId SET gameId = '" +playerGameId.getGameId()+"' WHERE playerName = '" +playerGameId.getPlayerName()+ "'";					
			dbConn.execSql(sql);		
		} finally { if (dbConn != null) dbConn.disconnect(); }
	}
		
	public void delete(PlayerGameId playerGameId) throws SQLException{
		dbConn = DbConnectionManager.newConnection();

		try{
			String sql = "DELETE FROM playerGameId WHERE playerName = " + playerGameId.getPlayerName();
			dbConn.execSql(sql);		
		} finally { if (dbConn != null) dbConn.disconnect(); }
	}	
	
}
