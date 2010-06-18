package com.pexperiment.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import com.pexperiment.model.Login;
import com.pexperiment.db.dao.LoginDAO;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			doProcess(request, response);
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		String playerName = request.getParameter("playerName");
		String password = request.getParameter("password");
		LoginDAO ld = new LoginDAO();
		Login login = null;
		String nextJSP;	
		
		HttpSession session = request.getSession(); 
		
		try { login = ld.select(playerName, password); } 
		catch (SQLException e) { /* invalid playerName */ e.printStackTrace(); }
		
		if (login == null){ /* invalid playerName */ nextJSP = "/index.jsp"; }
		else if (login.getPassword().contentEquals(password)) { 
			nextJSP = "/uploadHistory.jsp"; 
			session.setAttribute("login", login);
		} 
		else { /* invalid password */ nextJSP = "/index.jsp"; } 		 

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		try { dispatcher.forward(request,response); } 
		catch (ServletException e) { e.printStackTrace();} 
		catch (IOException e) { e.printStackTrace(); }
				
	}

}
