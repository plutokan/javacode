/*
 * Logon 64.68.119.55
 * cd /home/pluto
 * javac Query3.java
 * java -cp $CLASSPATH:/opt/gwapp/lib/classes12.jar Query3
 * Then there will generate a new file "CITYS.csv".
 */
package com.cisco.rekan.jdbc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query3 {
	
	final static String dbDriver = "oracle.jdbc.driver.OracleDriver";
	final static String connStr = "jdbc:oracle:oci8:@GWDB_PRODUCTION";
	final static String username = "wmap";
	final static String password = "pass";
	
	final static String sqlStr = "SELECT A.COUNTRYCODE, A.COUNTRYNAME, B.REGIONNAME, C.CITYNAME "
                               + "FROM WMCOUNTRY A, WMREGION B, WMCITY C "
                               + "WHERE A.COUNTRYID=C.COUNTRYID AND B.REGIONID=C.REGIONID "
                               + "ORDER BY A.COUNTRYCODE, B.REGIONNAME";
	
	final static String OUTPUTFILE = "CITYS.csv";
	final static String SPLITCHAR = ",";
	
	public static void main(String[] args) {
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

		try{
			FileWriter fw = new FileWriter(OUTPUTFILE);
			BufferedWriter bw = new BufferedWriter(fw);
			rs = stmt.executeQuery(sqlStr);
			while (rs.next()) {
				String COUNTRYCODE = rs.getString("COUNTRYCODE");
				bw.write(COUNTRYCODE + SPLITCHAR);
				String COUNTRYNAME = rs.getString("COUNTRYNAME");
				bw.write(COUNTRYNAME + SPLITCHAR);
				String REGIONNAME = rs.getString("REGIONNAME");
				bw.write(REGIONNAME + SPLITCHAR);
				String CITYNAME = rs.getString("CITYNAME");
				bw.write(CITYNAME);
				bw.newLine();
			}
			bw.close();
			fw.close();
		} catch (IOException e) {
			System.err.println(e);
		} catch (SQLException e) {
			System.err.println("SQLException: "+e.getMessage());
		}
		
		try {
			stmt.close();
			conn.close();
			System.out.println("Close db connection");
		} catch(SQLException e) {
			System.err.println("SQLException: "+e.getMessage());
		}
		
	}
}