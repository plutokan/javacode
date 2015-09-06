<!--这个网页是用来显示操作上次作业中的access数据库的结果，在这里是显示sp表格的全部内容。-->

<%@page 
import="java.sql.*"  
import ="java.util.*"  
import ="java.io.*"  
import="java.text.*"  
contentType="text/html; charset=gb2312"  
buffer="10kb"  
%>

<%! 
int cols;  
String odbcQuery;  
Connection odbcconn;  
Statement odbcstmt;  
ResultSet odbcrs;  
ResultSetMetaData odbcrsMeta;
%>  

<html>
<head><title>My Exer04</title></head>
<body>
<h3>练习4:从数据源component中取出sp表格的内容</h3>
<table border="4">

<tr>

<%  
try{  
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");  
}
catch (ClassNotFoundException e)  
{ 	out.println("驱动程序不存在\n");
}   

try{  
	odbcconn = DriverManager.getConnection("jdbc:odbc:component");  
	odbcstmt = odbcconn.createStatement();       
	odbcQuery="Select * From sp";  
	odbcrs=odbcstmt.executeQuery(odbcQuery);  
	odbcrsMeta=odbcrs.getMetaData();

	cols = odbcrsMeta.getColumnCount();
	// 先打印表头区
	for (int j=1;j<cols+1;j++){
%>
		<th><%=odbcrsMeta.getColumnName(j)%></th>
	<% } %>

</tr>

<%
	// 接下来打印出数据区
	while(odbcrs.next()){	
%>
		<tr>
	<%	for (int i=1;i<=cols;i++){
			try{			
	%> 
				<td><%=odbcrs.getString(i)%></td>
	<%		}
			catch(NullPointerException e){
			}
		}				
	%>
		</tr>
<%	
	}
	odbcrs.close();
	odbcstmt.close();
	odbcconn.close();
}
catch (SQLException e){	
	out.print (e);  
}
catch(java.lang.Exception ex){
	ex.printStackTrace();
}

%> 

</table>
</body>
</html>
