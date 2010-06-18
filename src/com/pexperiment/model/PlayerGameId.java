package com.pexperiment.model;

public class PlayerGameId {
	private String playerName;	
	private String gameId;

	public PlayerGameId(String playerName, String gameId){
		this.playerName = playerName;
		this.gameId = gameId;
	}
	
	public String getPlayerName() { return playerName; }
	public String getGameId() { return gameId; }
}
