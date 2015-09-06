package com.cisco.rekan.jsp;
import java.sql.*;

public class Exer04Bean{
	String dbDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
	String connStr = "jdbc:odbc:component";
	Connection conn = null;
	ResultSet rs = null;

	public Exer04Bean(){
		try{
			Class.forName(dbDriver);
		}
		catch(java.lang.ClassNotFoundException e){
			System.err.println("Exer04Bean()" + e.getMessage());
		}
	}
	
	public ResultSet executeQuery(String sqlStr){
		rs=null;
		try{
			conn = DriverManager.getConnection(connStr);
			Statement stmt=conn.createStatement();
			rs=stmt.executeQuery(sqlStr);
			return rs;
		}
		catch(SQLException ex){
			System.err.println("executeQuery:"+ex.getMessage());
		}
		return null;
	}
}