package com.pexperiment.loader;

import junit.framework.TestCase;

import com.pexperiment.model.Player;


public class LoaderTest extends TestCase {	
	
	private Loader loader;
	private Player player;
	
	protected void setUp(){
		
		loader = new Loader();
		player = new Player();
		player.setPlayerName("oatsforxmas");		
	}
	
	protected void tearDown(){
		loader = null;
		player = null;
	}
	
	
	public void testLoadHands(){
		loader.loadHands(player, "test/handHistory.txt");
		assertEquals(player.getTotalHands(), 5);
		assertEquals(player.getPfrHands(), 1);
		assertEquals(player.getVpipHands(), 2);
	}
	
}
