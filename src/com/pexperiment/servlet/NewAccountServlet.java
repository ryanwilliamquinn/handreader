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
import com.pexperiment.db.dao.LoginDAO;;

public class NewAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NewAccountServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			doProcess(request, response);
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		String newPlayerName = request.getParameter("newPlayerName");
		String newPassword = request.getParameter("newPassword");
		LoginDAO ld = new LoginDAO();
		Login login = null;
		HttpSession session = request.getSession(); 
		
		String nextJSP;
		login = new Login(newPlayerName, newPassword);
		
		// need error checks i.e. empty login name
		try { 
			ld.insert(login); 
			session.setAttribute("login", login); 
			nextJSP = "/uploadHistory.jsp"; } 
		catch (SQLException e) { /* invalid new account */ nextJSP = "/index.jsp"; e.printStackTrace();  }		
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		try { dispatcher.forward(request,response); } 
		catch (ServletException e) { e.printStackTrace(); } 
		catch (IOException e) { e.printStackTrace(); }
				
	}

}
