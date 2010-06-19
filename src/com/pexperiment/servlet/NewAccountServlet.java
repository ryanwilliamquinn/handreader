package com.pexperiment.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.pexperiment.db.dao.LoginDAO;
import com.pexperiment.model.Login;


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
		
		String nextJSP  = "/index.jsp";
		login = new Login(newPlayerName, newPassword);
		
		// need error checks i.e. empty login name
		if (StringUtils.isNotBlank(newPlayerName) && StringUtils.isNotBlank(newPassword)) {		
			try { 
				ld.insert(login); 
				session.setAttribute("login", login); 
				nextJSP = "/uploadHistory.jsp"; } 
			catch (SQLException e) { /* invalid new account */  
				request.setAttribute("errorMsg", "invalid login name or password");
				e.printStackTrace();  }		
		}
		else { request.setAttribute("errorMsg", "empty login name or password"); } 
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		try { dispatcher.forward(request,response); } 
		catch (ServletException e) { e.printStackTrace(); } 
		catch (IOException e) { e.printStackTrace(); }
				
	}

}
