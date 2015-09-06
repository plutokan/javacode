<!--这个网页是用来显示操作上次作业中的access数据库的结果，在这里是显示sp表格的全部内容。-->

<%@page 
import="java.sql.*"
contentType="text/html; charset=gb2312"  
buffer="10kb"  
%>

<html>
<head><title>My Exer04</title></head>
<body>
<h3>练习4:从数据源component中取出sp表格的内容</h3>

<jsp:useBean id="dmb1" class="test.Exer04Bean" />


<%
ResultSet rs=dmb1.executeQuery("select * from sp");
ResultSetMetaData rsmd=rs.getMetaData();
out.println(rsmd.getColumnName(1)+"\t"+rsmd.getColumnName(2)+"\t"
				+rsmd.getColumnName(3)+"\t"+rsmd.getColumnName(4)+"<br>");
while(rs.next()){
	out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"
		+rs.getString(3)+"\t"+rs.getString(4)+"<br>");
}
%>

</body>
</html>