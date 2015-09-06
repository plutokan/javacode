package com.cisco.rekan.jdbc;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query2 {
	
	final static int TOPVALUE = 5000; 
	final static int STEP = 50;

	final static String dbDriver = "oracle.jdbc.driver.OracleDriver";
	final static String connStr = "jdbc:oracle:oci8:@GWDB_PRODUCTION";
	final static String username = "gw";
	final static String password = "pass";
	
	final static String sqlStr = "SELECT * FROM EMCATTENDEENETLATENCY "
		+ "where LASTLOGTIME between to_date('### 00:00:00', 'yyyymmdd hh24:mi:ss') "
		+ "and to_date('### 23:59:59', 'yyyymmdd hh24:mi:ss')";
	
	final static String OUTPUTFILE = "AttendeeNetLatency.###.csv";
	final static String SPLITCHAR = ",";
	
	public static void main(String[] args) {
		if (args.length == 1) {
			String sDate = args[0];
			int[] result = new int[TOPVALUE/STEP+1];
			for (int i=0; i<result.length; i++) {
				result[i] = 0;
			}
			
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;

			try {
				Class.forName(dbDriver).newInstance();
				conn = DriverManager.getConnection(connStr, username, password);
	            System.out.println("Opening db connection");
				stmt = conn.createStatement();
			} catch (java.lang.ClassNotFoundException e) {
				System.err.println("ClassNotFoundException: " + e.getMessage());
			} catch (SQLException e) {
				System.err.println("SQLExeption: " + e.getMessage());
			} catch (IllegalAccessException e3) {
				System.err.println("IllegalAccessException: " + e3.toString());
			} catch (InstantiationException e4) {
				System.err.println("InstantiationException: " + e4.toString());
			}

			// SQL> desc emcattendeenetlatency;
			//  Name                                      Null?    Type
			//  ----------------------------------------- -------- ----------------------------
			//  SITEID                                    NOT NULL NUMBER(10)
			//  CONFID                                    NOT NULL NUMBER(10)
			//  HOSTID                                    NOT NULL NUMBER(10)
			//  LASTLOGTIME                               NOT NULL DATE
			//  NETLATENCY                                         NUMBER(10)
			//  LASTMODIFIEDTIME                                   DATE
			int i=0;
			try{
				rs = stmt.executeQuery(sqlStr.replaceAll("###", sDate));
				while (rs.next()) {
					int temp = rs.getInt("NETLATENCY");
					if (temp/STEP < result.length-1) {
						result[temp/STEP]++;
					} else {
						result[result.length-1]++;
					}
					if ((i%10000) == 0) {
						System.out.println("Already handle " + i + " records!");
					}
					i++;
				}
			} catch (SQLException e) {
				System.err.println("SQLException: "+e.getMessage());
			}
			System.out.println("Total query attendee number: " + i);
			
			try {
				stmt.close();
				conn.close();
				System.out.println("Close db connection");
			} catch(SQLException e) {
				System.err.println("SQLException: "+e.getMessage());
			}
			
			try {
				FileWriter fw = new FileWriter(OUTPUTFILE.replaceAll("###", sDate));
				BufferedWriter bw = new BufferedWriter(fw);
				i=0;
				for (int j=0; j<result.length; j++) {
					//System.out.println(j + "," + result[j]);
					bw.write(j*STEP + SPLITCHAR);
				}
				bw.newLine();
				for (int j=0; j<result.length; j++) {
					bw.write(result[j] + SPLITCHAR);
					i += result[j];
				}
				bw.newLine();
				System.out.println("Total count attendee number: " + i);
				bw.close();
				fw.close();
			} catch (IOException e) {
				System.err.println(e);
			}
		} else {
			System.out.println("Invalid parameters, Please refer this:");
			System.out.println("java Query2 20070501");
		}
	}
	
}
