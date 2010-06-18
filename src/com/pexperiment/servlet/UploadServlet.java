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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.pexperiment.db.dao.PlayerDAO;
import com.pexperiment.loader.Loader;
import com.pexperiment.model.Player;
import com.pexperiment.model.Login;
import com.pexperiment.db.dao.LoginDAO;

public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(UploadServlet.class);
       
    public UploadServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws FileUploadException, IOException {
		HttpSession session = request.getSession(); 
		Login login = (Login) session.getAttribute("login");
		String playerName = login.getPlayerName();
				
		// Check that we have a file upload request
		// boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		// Create a factory for disk-based file items
		FileItemFactory factory = new DiskFileItemFactory();

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		// Get player object
		String nextJSP = "/handResults.jsp";	
		PlayerDAO pd = null;
		Player player = null;
		try {	
			pd = new PlayerDAO();
			player = pd.select(playerName);
			if (player == null) {
				pd.insert(new Player(playerName));
				player = pd.select(playerName);
			}			
		} catch (SQLException e) { 
			log.error(e); e.printStackTrace(); 
			nextJSP = "/uploadHistory.jsp";
		}		
		
		// Parse the request
		List<FileItem> items = upload.parseRequest(request);		
		Iterator iter = items.iterator();
		while (iter.hasNext()) { // even though we have a single input field for files, I think this is getting multiple past files
		    FileItem item = (FileItem) iter.next();		    
		    processUploadedFile(item, player); 
		}
		
		// dispatch jsp
		try { request.setAttribute("player", pd.select(playerName)); }
		catch (SQLException e) { 
			log.error(e); e.printStackTrace();
			nextJSP = "/uploadHistory.jsp"; }
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		try { dispatcher.forward(request,response); } 
		catch (ServletException e) { e.printStackTrace(); } 
		catch (IOException e) { e.printStackTrace(); }
	}
	
	
	private void processFormField(FileItem item){ // unused function
			log.info(item.getString());		
	}
	
	private void processUploadedFile(FileItem item, Player player){
		// Process a file upload
		if (!item.isFormField()) {
		    String fieldName = item.getFieldName();
		    String fileName = item.getName();
		    String contentType = item.getContentType();
		    boolean isInMemory = item.isInMemory();
		    long sizeInBytes = item.getSize();
		    log.info(fieldName + " " + fileName + " " + contentType + " " + isInMemory + " " + sizeInBytes);

		 // Process a file upload
		    boolean writeToFile = true;
			if (writeToFile ) {
		        File uploadedFile = new File(fileName);
		        log.info("created new File object");
				log.info(uploadedFile.getAbsoluteFile());
				log.info(uploadedFile.getAbsolutePath());
		        try {
					item.write(uploadedFile);
				} catch (Exception e) {
					log.error(e);
					e.printStackTrace();
				}
		    }
			Loader loader = Loader.getInstance();
			loader.loadHands(player, fileName);		    
		}
	}
	
	
}
