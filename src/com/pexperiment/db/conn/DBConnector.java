package com.pexperiment.db.conn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import org.apache.log4j.Logger;
import org.postgresql.ds.PGSimpleDataSource;


public class DBConnector {

	private static Logger log = Logger.getLogger(DBConnector.class);
	
		private String dbHost;
		private int dbPortNumber;
		private String dbName;
		private String dbUserName;
		private String dbPassword;
		private Connection conn = null;
		private ResultSet dbRs = null;
		private Statement dbSqlStatement = null;

		
		//constructor
		public DBConnector(String dbHost, int dbPortNumber, String dbName, String dbUserName, String dbPassword){
			this.dbHost = dbHost;
			this.dbPortNumber = dbPortNumber;
			this.dbName = dbName;
			this.dbUserName = dbUserName;
			this.dbPassword = dbPassword;
		}

		//a method to reset the connection and sql fields
		public void reset(){
			conn = null;
			dbRs = null;
			dbSqlStatement = null;
		}
		
		//connect method
		public void connect() throws SQLException{
			
			//resets connection object and sql statements/resultsets/updatecounts
			reset();
			log.info("in connect method");
			//new pgsimpledatasource object
			PGSimpleDataSource dbDs = new PGSimpleDataSource();
			
			//initializing dbDs
			dbDs.setServerName(dbHost);
			dbDs.setPortNumber(dbPortNumber);
			dbDs.setDatabaseName(dbName);
			dbDs.setUser(dbUserName);
			dbDs.setPassword(dbPassword);
			log.info(dbDs.getServerName() + " " + dbDs.getPortNumber() + " " + dbDs.getDatabaseName() + " " + dbDs.getUser() + " " + dbDs.getPassword());
			conn = dbDs.getConnection();
			log.info("All systems go for connection with DBConnector");
		}
		
		//validates our connection
		public boolean validateConnection() throws SQLException{
			return (conn != null && !conn.isClosed());
		}
		
		//execSql method that returns a resultSet, and if it is an update, sets updateCount
		public ResultSet execSql(String sql) throws SQLException {
			
			validateConnection();
			log.info("validated connection DBConnector");
			
			//ensures dbRs and dbSqlStatement are null before we start using them
			if (dbRs != null){
				dbRs.close();
			}
			
			if (dbSqlStatement != null){
				dbSqlStatement.close();
			}
			
			dbSqlStatement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			//executes sql command, sets resultset if it gets one back
			if(dbSqlStatement.execute(sql)){
				dbRs = dbSqlStatement.getResultSet();
			}
	
			//return the resultset			
			return dbRs;
		}
		
		//disconnect the connection immediately, rather than waiting for automatic disconnect
		public void disconnect() throws SQLException{

			if(conn != null && !conn.isClosed()){
				conn.close();
			}
			
		}
		
		
	}
