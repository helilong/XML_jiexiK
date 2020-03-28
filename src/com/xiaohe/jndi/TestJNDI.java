package com.xiaohe.jndi;

import java.sql.*;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
public class TestJNDI {
	public static void main(String[] args) throws NamingException, SQLException {
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		
		//������������
		Context ct = new InitialContext();
		
		//��ȡ����Դ
		DataSource ds =(DataSource) ct.lookup("java:comp/env/textJNDI");
		
		//��������
		conn = ds.getConnection();
		
		
		//��ȡ�������ݿ���������
		pst = conn.prepareStatement("select * from stuInfo");
		rs = pst.executeQuery();
		
		while(rs.next()) {
			System.out.println(rs.getObject(1));
			System.out.println(rs.getObject(2));
			System.out.println(rs.getObject(3));
			System.out.println(rs.getObject(4));
			System.out.println(rs.getObject(5));
		}
		
		//�ر�
		if(rs != null) {
			rs.close();
		}
		if(pst != null) {
			pst.close();
		}
		if(conn != null) {
			conn.close();
		}
	}
}
