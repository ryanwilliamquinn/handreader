package com.pexperiment.model;

public class Login {
	private String playerName;
	private String password;
	
	public Login(String playerName, String password){
		this.playerName = playerName;
		this.password = password;
	}
	
	public String getPlayerName(){ return playerName; }
	public String getPassword(){ return password; }
	
}
