package com.pexperiment.model;

import com.pexperiment.loader.Loader;

public class Player {
	private String name;
	private int totalHands;
	private int vpipHands;
	private int pfrHands;
	
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
	
	public static void main(String[] args){
		Player p = new Player();
		p.setPlayerName("oatsforxmas");
		Loader l = Loader.getInstance();
		l.loadHands(p, "test.txt");
        System.out.println(p.getPlayerName());
        System.out.println(p.getTotalHands());
        System.out.println(p.getVpipHands());
        System.out.println(p.getPfrHands());
        float d = (float)p.getVpipHands()/(float)p.getTotalHands();
        float r = (float)p.getPfrHands()/(float)p.getTotalHands();
        System.out.println("vpip: %" + d*100);
        System.out.println("pfr: %" + r*100);
		
	}
	
	
	
}
