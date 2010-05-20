package com.pexperiment.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

//import org.apache.log4j.Logger;
import org.apache.log4j.Logger;

import com.pexperiment.db.conn.DBConnector;
import com.pexperiment.db.conn.DbConnectionManager;
import com.pexperiment.model.Player;

public class PlayerDAO {

	//initialize logger
	private static Logger log = Logger.getLogger(PlayerDAO.class);
	
	
	static DBConnector dbConn;
	
	public PlayerDAO() throws SQLException{
	}
	
	//select, insert, delete, update
	
	//i'm having trouble figuring out how to work with LocalDate objects.  Not sure how to get them out of db and into the object, since they are stored in 
	//a different format in the database than in the LocalDate object...I just left them out of this select method, but I know that isn't the right answer.
	
	public Player select(String id) throws SQLException{
		
		Player player = null;
		
		
		dbConn = DbConnectionManager.newConnection();
		
		try{
			String sql = "SELECT * FROM player WHERE playername = '" + id + "'";
			
			ResultSet rs = dbConn.execSql(sql);
			
			if (rs != null) {
				while (rs.next()){
					player = new Player(rs.getInt("totalhands"), rs.getInt("vpiphands"), rs.getInt("pfrhands"));
				}
				
			}
			
		} finally {
			if (dbConn != null) dbConn.disconnect();
		}
		
		return player;
	}
	

	public void insert(Player player) throws SQLException{
		
		dbConn = DbConnectionManager.newConnection();
		
		try{
			String sql = "INSERT INTO player (playername, totalhands, vpiphands, pfrhands) " +
						 "VALUES ('" + player.getPlayerName() + "', '" + player.getTotalHands() + "', '" + player.getVpipHands() +"', '" + player.getPfrHands() + "')";
			
			dbConn.execSql(sql);
			
		} finally {
			if (dbConn != null) dbConn.disconnect();
		}
		
	}
	
	public void update(Player player) throws SQLException{
			
		//new database connection
		dbConn = DbConnectionManager.newConnection();

		try{
		String sql = "UPDATE player SET totalHands = '" + player.getTotalHands() + "', vpiphands = '" + player.getVpipHands()+ "' WHERE playername =" + player.getPlayerName(); 
		
		dbConn.execSql(sql);
		
		} finally {
			if (dbConn != null) dbConn.disconnect();
		}
	}
		

	public void delete(Player player) throws SQLException{
		
		//new database connection
		dbConn = DbConnectionManager.newConnection();

		try{
		String sql = "DELETE FROM player WHERE playername = " + player.getPlayerName();
		
		dbConn.execSql(sql);
		
		} finally {
			if (dbConn != null) dbConn.disconnect();
		}
	}

}