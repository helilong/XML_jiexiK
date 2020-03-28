package com.xiaohe.c3p0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
public static void main(String[] args) {
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet  rs=null;
	try {
		conn=TestC3P0.getConnection();
		
		pst=conn.prepareStatement("select * from stuinfo");
		rs=pst.executeQuery();
		while(rs.next()){
			
			System.out.println(rs.getObject(1)+"\t"+rs.getObject(2)+"\t"+rs.getObject(3));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		TestC3P0.close(conn);
	}
			
	
}
}
