package com.pexperiment.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.pexperiment.db.conn.DBConnector;
import com.pexperiment.db.conn.DbConnectionManager;
import com.pexperiment.model.Player;
import com.pexperiment.model.PlayerGameId;

public class PlayerGameIdDAO {
	
	static DBConnector dbConn;
	
	public PlayerGameId select(String playerName, String gameId) throws SQLException{		
		PlayerGameId playerGameId = null;			
		dbConn = DbConnectionManager.newConnection();	
		
		String sql = "SELECT * FROM playerGameId WHERE playername = '" + playerName + "' and gameId = '" + gameId + "';";			
		ResultSet rs = dbConn.execSql(sql);			
		while (rs.next()){
			playerGameId = new PlayerGameId(rs.getString("playerName"), rs.getString("gameId"));
		}

		if (dbConn != null) dbConn.disconnect(); 		
		
		return playerGameId;
	}

	public PlayerGameId select(DBConnector dbConn, String playerName, String gameId) throws SQLException{		
		PlayerGameId playerGameId = null;			
		
		String sql = "SELECT * FROM playerGameId WHERE playername = '" + playerName + "' and gameId = '" + gameId + "';";			
		ResultSet rs = dbConn.execSql(sql);			
		while (rs.next()){
			playerGameId = new PlayerGameId(rs.getString("playerName"), rs.getString("gameId"));
		}		
		
		return playerGameId;
	}	
	
	public void insert(PlayerGameId playerGameId) throws SQLException{		
		dbConn = DbConnectionManager.newConnection();		

		String sql = "INSERT INTO playerGameId (playerName, gameId) VALUES ('" + playerGameId.getPlayerName() + "', '" + playerGameId.getGameId() + "')";	
		dbConn.execSql(sql);			
	
		if (dbConn != null) dbConn.disconnect(); 		
	}	
	
	public void insert(DBConnector dbConn, PlayerGameId playerGameId) throws SQLException{				
		String sql = "INSERT INTO playerGameId (playerName, gameId) VALUES ('" + playerGameId.getPlayerName() + "', '" + playerGameId.getGameId() + "')";	
		dbConn.execSql(sql);						 		
	}	
	
	public void insert(ArrayList<PlayerGameId> playerGameIds) throws SQLException{		
		dbConn = DbConnectionManager.newConnection();		

		for(PlayerGameId playerGameId: playerGameIds){
			String sql = "INSERT INTO playerGameId (playerName, gameId) VALUES ('" + playerGameId.getPlayerName() + "', '" + playerGameId.getGameId() + "')";	
			dbConn.execSql(sql);			
		} 
	
		if (dbConn != null) dbConn.disconnect(); 		
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
			String sql = "DELETE FROM playerGameId WHERE playerName = '" + playerGameId.getPlayerName() + "' and gameId = '" + playerGameId.getGameId() + "'" ;
			dbConn.execSql(sql);		
		} finally { if (dbConn != null) dbConn.disconnect(); }
	}
	
	public void deleteAll() throws SQLException {
		dbConn = DbConnectionManager.newConnection();

		try{
			String sql = "DELETE FROM playerGameId;";
			dbConn.execSql(sql);		
		} finally { if (dbConn != null) dbConn.disconnect(); }
	}
	
}
