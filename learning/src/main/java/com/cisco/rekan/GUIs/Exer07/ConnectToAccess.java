package com.cisco.rekan.GUIs.Exer07;

import java.sql.*;

public class ConnectToAccess{
	private String dbDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
	private String connStr = "jdbc:odbc:AddressBook";
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	public ConnectToAccess(){
		try {
			Class.forName(dbDriver);
            System.out.println("Opening db connection");
			conn = DriverManager.getConnection(connStr,"","");
			stmt = conn.createStatement();
		}
		catch(java.lang.ClassNotFoundException e){
			System.err.println("ClassNotFoundException: " + e.getMessage());
		}
		catch(SQLException e) {
			System.err.println("SQLExeption: " + e.getMessage());
		}
	}
	
	public void close() {
		try {
			stmt.close();
			conn.close();
			System.out.println("Close db connection");
		} catch(SQLException e) {
			System.err.println("SQLException: "+e.getMessage());
		}
	}
	
	public ResultSet selectQuery(){
		String sqlStr = "SELECT * FROM TblAddressBook";
	//	String sqlStr = "select * from aaa";
		rs = null;
		try{
			rs = stmt.executeQuery(sqlStr);
		}
		catch(SQLException e){
			System.err.println("SQLException: "+e.getMessage());
		}
		return rs;
	}
	
	public void deleteQuery(String deleteCode){
		try{
			stmt.executeUpdate("delete from TblAddressBook Where ���='"+ deleteCode +"'");
		}
		catch(SQLException e){
			System.err.println("SQLException: "+e.getMessage());
		}
	}
	
/*	
	public void addQuery(String waresCode){
		try{
			conn = DriverManager.getConnection(connStr);
			Statement stmt=conn.createStatement();
			stmt.executeUpdate("update wares set �����=�����+1 where ����='"+waresCode+"'");
		}
		catch(SQLException ex){
			System.err.println("SQLException: "+ex.getMessage());
		}
	}	
*/
}