<!--�����ҳ��������ʾ�����ϴ���ҵ�е�access���ݿ�Ľ��������������ʾsp����ȫ�����ݡ�-->

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
<h3>��ϰ4:������Դcomponent��ȡ��sp��������</h3>
<table border="4">

<tr>

<%  
try{  
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");  
}
catch (ClassNotFoundException e)  
{ 	out.println("�������򲻴���\n");
}   

try{  
	odbcconn = DriverManager.getConnection("jdbc:odbc:component");  
	odbcstmt = odbcconn.createStatement();       
	odbcQuery="Select * From sp";  
	odbcrs=odbcstmt.executeQuery(odbcQuery);  
	odbcrsMeta=odbcrs.getMetaData();

	cols = odbcrsMeta.getColumnCount();
	// �ȴ�ӡ��ͷ��
	for (int j=1;j<cols+1;j++){
%>
		<th><%=odbcrsMeta.getColumnName(j)%></th>
	<% } %>

</tr>

<%
	// ��������ӡ��������
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
