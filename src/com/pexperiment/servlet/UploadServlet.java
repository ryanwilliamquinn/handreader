package com.pexperiment.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.pexperiment.db.conn.DBConnector;
import com.pexperiment.db.conn.DbConnectionManager;
import com.pexperiment.db.dao.PlayerDAO;
import com.pexperiment.loader.Loader;
import com.pexperiment.model.Player;

/**
 * Servlet implementation class UploadServlet
 */
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(PlayerNameServlet.class);
	private Player player = new Player();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws FileUploadException, IOException {
		// Check that we have a file upload request
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		// Create a factory for disk-based file items
		FileItemFactory factory = new DiskFileItemFactory();

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Parse the request
		List /* FileItem */ items = upload.parseRequest(request);
		
		Iterator iter = items.iterator();
		while (iter.hasNext()) {
		    FileItem item = (FileItem) iter.next();

		    if (item.isFormField()) {
		        processFormField(item);
		    } else {
		        processUploadedFile(item);
		    }
		}

			
		try {
			PlayerDAO pd = new PlayerDAO();
			pd.insert(player);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String nextJSP = "/handResults.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		try {
			dispatcher.forward(request,response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void processFormField(FileItem item){
			log.info(item.getString());
			player.setPlayerName(item.getString());
	}
	
	private void processUploadedFile(FileItem item){
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
		        File uploadedFile = new File("/files/", fileName);
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
