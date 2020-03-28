<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@ page import="java.sql.*,javax.sql.DataSource,javax.naming.*"    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%Connection conn=null;
PreparedStatement pst=null;
ResultSet rs=null;


//创建容器对象
Context ct = new InitialContext();

//获取数据源
DataSource ds =(DataSource) ct.lookup("java:comp/env/testJNDI");

//创建连接
conn = ds.getConnection();


//获取操作数据库的命令对象
pst = conn.prepareStatement("select * from stuInfo");
rs = pst.executeQuery();

while(rs.next()) {
	System.out.println(rs.getObject(1));
	System.out.println(rs.getObject(2));
	System.out.println(rs.getObject(3));
	System.out.println(rs.getObject(4));
	System.out.println(rs.getObject(5));
}

//关闭
if(rs != null) {
	rs.close();
}
if(pst != null) {
	pst.close();
}
if(conn != null) {
	conn.close();
} %>
</body>
</html>