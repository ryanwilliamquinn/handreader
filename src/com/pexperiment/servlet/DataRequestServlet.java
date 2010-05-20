package com.pexperiment.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pexperiment.db.dao.PlayerDAO;
import com.pexperiment.model.Player;

/**
 * Servlet implementation class DataRequestServlet
 */
public class DataRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String name = request.getParameter("pName");
		Player player = new Player();
		try {
			PlayerDAO pd = new PlayerDAO();
			player = pd.select(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.setContentType("text/html");
		PrintWriter pw = new PrintWriter (response.getOutputStream());
		pw.print(player.getTotalHands());
		pw.print(player.getVpipHands());
		pw.close();
	}
}
