/*
 * java -Djava.util.logging.config.file=logging.properties
 */

package com.cisco.rekan.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

public class CheckGwconference {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Build the Timer and run
		CheckGwconferenceTask cgt = new CheckGwconferenceTask();
		
		Timer timer = new Timer("CheckGwconference");
		//timer.schedule(cgt, 0, 10*60*1000);
		timer.schedule(cgt, 0, 60*1000);
	}

}

class CheckGwconferenceTask extends TimerTask {

	final String CONFIG_FILE_ = "./src/com/webex/pluto/test/jdbc/CheckGwconference.conf";
	
	Properties prop = new Properties();
	
	private Connection conn;
	
	private Statement stmt;
	
	private ResultSet rset;
	
	private Logger logger = Logger.getLogger("CheckGwconferenceTask");
	
	private boolean init() {
		// 1. Get the parameter
		//System.out.println(new java.io.File(".").getAbsolutePath());
		InputStream is;
		try {
			is = new FileInputStream(CONFIG_FILE_);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		try {
			prop.load(is);
			//prop.storeToXML(System.out, "Pluto Test");    // print the conf items
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		// 2. Get the connection
		try {
			Class.forName(prop.getProperty("gwdb.drivers")).newInstance();
			conn = DriverManager.getConnection(prop.getProperty("gwdb.url"),
					prop.getProperty("gwdb.username"), prop.getProperty("gwdb.password"));
			stmt = conn.createStatement();
            logger.info("Opening db connection and create the statement success!");
		} catch (java.lang.ClassNotFoundException e) {
			System.err.println("ClassNotFoundException: " + e.getMessage());
			return false;
		} catch (SQLException e) {
			System.err.println("SQLExeption: " + e.getMessage());
			return false;
		} catch (IllegalAccessException e3) {
			System.err.println("IllegalAccessException: " + e3.toString());
			return false;
		} catch (InstantiationException e4) {
			System.err.println("InstantiationException: " + e4.toString());
			return false;
		}
		
		return true;
		
	}
	
	private void destroy() {
		try {
			if (rset!=null) {	
				rset.close();
				rset=null;
			}
			if (stmt!=null) {
				stmt.close();
				stmt=null;
			}
			if (conn!=null) {
				conn.close();
				conn=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void finalize() throws Throwable
	{
		//System.out.println("CheckGwconferenceTask is finalize now.");
		this.destroy();
	}
	
	@Override
	public void run() {
		System.out.println("CheckGwconferenceTask is running now.");
		if (! init()) {
			return;
		}
		
		// select * from confid_tmp
		//String sqlStr = "select * from confid_tmp";
		String sqlStr = prop.getProperty("sql1");
		sqlStr = sqlStr.replace("?", "*");
		sqlStr = sqlStr.replace("?", "confid_tmp");
		List<Conf> confIDList = new LinkedList<Conf>();
		try{
			rset = stmt.executeQuery(sqlStr);
			while (rset.next()) {
				long siteID = rset.getLong("SITEID");
				long confID = rset.getLong("CONFID");
				confIDList.add(new Conf(siteID, confID));
			}
		} catch (SQLException e) {
			System.err.println("SQLException: "+e.getMessage());
		}
		System.out.println(confIDList);

		this.destroy();
	}
	
}

class Conf {
	public Conf(long siteID, long confID) {
		this.siteID = siteID;
		this.confID = confID;
	}
	
	private long siteID;
	private long confID;
	public long getConfID() {
		return confID;
	}
	public void setConfID(long confID) {
		this.confID = confID;
	}
	public long getSiteID() {
		return siteID;
	}
	public void setSiteID(long siteID) {
		this.siteID = siteID;
	}
	
	@Override
	public String toString() {
		return "Conf Info: siteID[" + siteID + "]confID[" + confID + "]";
	}
}