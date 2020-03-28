package com.xiaohe.dbcp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;





public class TestDBCT {
public static void main(String[] args) throws SQLException {
	//读取资源文件（只需要资源）
	ResourceBundle rb=ResourceBundle.getBundle("dbcp");
	String username=rb.getString("username");
	String password=rb.getString("password");
	String maxActive=rb.getString("maxActive");
	String maxIdle=rb.getString("maxIdle");
	String maxWait=rb.getString("maxWait");
	String url=rb.getString("url");
	String driverClassName=rb.getString("driverClassName");
	
	
	
	//基础数据源
	BasicDataSource bds=new BasicDataSource();
	bds.setUsername(username);
	bds.setPassword(password);
	bds.setMaxActive(Integer.parseInt(maxActive));
	bds.setMaxIdle(Integer.parseInt(maxIdle));
	bds.setMaxWait(Long.parseLong(maxWait));
	bds.setUrl(url);
	bds.setDriverClassName(driverClassName);
	
	
	
	
	//数据源
	
	DataSource ds=(DataSource)bds;
	
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	
	conn=ds.getConnection();
	pst=conn.prepareStatement("select * from userinfo");
	rs=pst.executeQuery();
	while(rs.next()){
		System.out.println(rs.getObject(1)+"\t"+rs.getObject(2)+"\t"+rs.getObject(3));
	}
	if(rs!=null){
		rs.close();
		
	}
	if(pst!=null){
		
		pst.close();
	}
	if(conn!=null){
		conn.close();
	}
	}
}
