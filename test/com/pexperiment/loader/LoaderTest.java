package com.pexperiment.loader;

import java.sql.SQLException;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import com.pexperiment.db.dao.LoginDAO;
import com.pexperiment.db.dao.PlayerDAO;
import com.pexperiment.db.dao.PlayerGameIdDAO;
import com.pexperiment.model.Login;
import com.pexperiment.model.Player;


public class LoaderTest extends TestCase {	
	
	private Loader loader;
	private Player player;
	private Logger log;
	private LoginDAO ldao;
	private Login login;
	
	
	protected void setUp(){
		
		loader = new Loader();
		player = new Player();
		player.setPlayerName("test");
		login = new Login(player.getPlayerName(), "password");
		ldao = new LoginDAO();
		try {
			if(ldao.select(player.getPlayerName()) == null) {
				ldao.insert(login);
				}
		} catch (SQLException e) {
			log.error("error in setup for LoaderTest");
			e.printStackTrace();
		}
	}
	
	
	public void testLoadHands(){
		loader.loadHands(player, "test/handHistory.txt");
		assertEquals(player.getTotalHands(), 5);
		assertEquals(player.getPfrHands(), 1);
		assertEquals(player.getVpipHands(), 2);
	}
	
	
	protected void tearDown(){
		try{
			PlayerDAO pdao = new PlayerDAO();
			PlayerGameIdDAO pgdao = new PlayerGameIdDAO();
			LoginDAO ldao = new LoginDAO();
			pgdao.delete(player.getPlayerName());
			pdao.delete(player.getPlayerName());
			ldao.delete(login);
			
		} catch (SQLException e) {
			log.error("error deleting player in LoaderTest");
		} finally {
			loader = null;
			player = null;
			login = null;
			ldao = null;
		}
	}
	

	
}
