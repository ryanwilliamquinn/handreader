package com.pexperiment.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.pexperiment.db.dao.PlayerDAO;
import com.pexperiment.db.dao.PlayerGameIdDAO;
import com.pexperiment.model.Login;

public class ClearDatabaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ClearDatabaseServlet.class);
       
    public ClearDatabaseServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			doProcess(request, response);
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try{
			PlayerDAO pd = new PlayerDAO();
			PlayerGameIdDAO pgid = new PlayerGameIdDAO();
			pd.deleteAll();
			pgid.deleteAll();
		} catch (SQLException e) { 
			log.error(e); e.printStackTrace(); 
		}	
			
		String nextJSP = "/uploadHistory.jsp"; 		 

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		try { dispatcher.forward(request,response); } 
		catch (ServletException e) { e.printStackTrace();} 
		catch (IOException e) { e.printStackTrace(); }
				
	}

}
