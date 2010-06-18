package com.pexperiment.model;


public class Player {
	private String name;
	private int totalHands=0;
	private int vpipHands=0;
	private int pfrHands=0;
	
	public int getPfrHands() {
		return pfrHands;
	}

	public void setPfrHands(int pfrHands) {
		this.pfrHands = pfrHands;
	}
	
	public void incrementPfrHands(){
		pfrHands++;
	}

	public Player(){};
	
	public Player(String name, int hands, int vpipHands, int pfrHands){
		this.name = name;
		this.totalHands = hands;
		this.vpipHands = vpipHands;
		this.pfrHands = pfrHands;
	}
	
	public Player(String name) {
		this.name = name;
	}

	public String getPlayerName(){
		return name;
	}
	
	public void setPlayerName(String name){
		this.name = name;
	}
	
	public int getTotalHands(){
		return totalHands;
	}
	
	public void setTotalHands(int totalHands){
		this.totalHands = totalHands;
	}

	
	public int getVpipHands(){
		return vpipHands;
	}
	
	public void setVpipHands(int vpipHands){
		this.vpipHands = vpipHands;
	}
	
	
	public void incrementTotalHands(){
		totalHands++;
	}
	
	public void incrementVpipHands(){
		vpipHands++;
	}	
}
