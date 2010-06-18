package com.pexperiment.loader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

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
        for(String hand : hands){
        	String gameId = StringUtils.substringBetween(hand, "Game #", ":");
        	PlayerGameId pgi = new PlayerGameId(p.getPlayerName(), gameId);
        	PlayerGameIdDAO pgiDAO = new PlayerGameIdDAO();
        	try {
				pgiDAO.insert(pgi);
			} catch (SQLException e) {
				log.error("error inserting game id");
				e.printStackTrace();
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
    }
}
