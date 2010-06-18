package com.pexperiment.loader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.pexperiment.db.conn.DBConnector;
import com.pexperiment.db.conn.DbConnectionManager;
import com.pexperiment.db.dao.PlayerGameIdDAO;
import com.pexperiment.model.Player;
import com.pexperiment.model.PlayerGameId;

public class Loader {

	private static Logger log = Logger.getLogger(Loader.class);
	private static Loader instance = null;
	private Player p;
	private StringBuffer contents = new StringBuffer();
    private BufferedReader reader = null;

	
	public static Loader getInstance(){
		if (instance != null) {
			return instance;
		} else {
			instance = new Loader();
			return instance;
		}
	}
	
	
	public void loadHands(Player player, String fileName){
		this.p = player;
		File file = new File(fileName);
        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            // repeat until all lines are read
            while ((text = reader.readLine()) != null) {
                contents.append(text).append(System.getProperty("line.separator"));
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
        	try {
        		if (reader != null){
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        incrementStats();
	}
	
    private void incrementStats() {
    	String[] hands = StringUtils.splitByWholeSeparator(contents.toString(),"PokerStars",0 );
    	PlayerGameIdDAO pgiDAO = new PlayerGameIdDAO();
    	DBConnector dbConn = null;
    	
    	try { 
    		dbConn = DbConnectionManager.newConnection(); 
    		if (dbConn == null) { 
    			log.error("error dbConn is null");
    			return; }
    	} catch (SQLException e) {     		
    		log.error("error getting new dbConn");
    		return; }
    	
        for(String hand : hands){
        	String gameId = StringUtils.substringBetween(hand, "Game #", ":");
        	PlayerGameId pgi = new PlayerGameId(p.getPlayerName(), gameId);
        	       	
        	try {
				 if (pgiDAO.select(dbConn, p.getPlayerName(), gameId) == null) { pgiDAO.insert(dbConn, pgi); }
				 else { continue; } // if this gameid is already in db, then skip processing this hand
			} catch (SQLException e) { 
				log.error("error selecting gameid");
				continue; 
			}
			
        	String shortenedContents = StringUtils.substringAfter(hand, "*** HOLE CARDS ***");
        	String action = StringUtils.substringBetween(shortenedContents, p.getPlayerName()+":", "\n");
        	p.incrementTotalHands();
        	if(!StringUtils.contains(action, "folds") && !StringUtils.contains(action, "doesn't show hand")){
        		p.incrementVpipHands();
        		if(StringUtils.contains(action, "raise")){
        			p.incrementPfrHands();
        		}
        	}
        }     	
                
        log.info(p.getTotalHands());
        log.info(p.getVpipHands());
        log.info(p.getPfrHands());
        
        try { 
        	if (dbConn != null) dbConn.disconnect(); 
		} catch (SQLException e) {     		
			log.error("error disconnecting dbConn");
		}       
    }
}
