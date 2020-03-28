package com.xiaohe.druid;

import java.util.ResourceBundle;

import com.alibaba.druid.pool.DruidDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class TestDRUID {
	public static void main(String[] args) {
		DruidDataSource ds = null;
		Connection conn=null;
		ResultSet rs=null;
		PreparedStatement pst=null;
		//��ȡ��Դ�ļ���ֻ��Ҫ��Դ��
		ResourceBundle rb=ResourceBundle.getBundle("dbcp");
		String username=rb.getString("username");
		String password=rb.getString("password");
		String maxActive=rb.getString("maxActive");
		String maxIdle=rb.getString("maxIdle");
		String maxWait=rb.getString("maxWait");
		String url=rb.getString("url");
		String driverClassName=rb.getString("driverClassName");
		
		//��������Դ����
		DruidDataSource dbs = new DruidDataSource();
		dbs.setUsername(username);
		dbs.setPassword(password);
		dbs.setMaxActive(Integer.parseInt(maxActive));
		dbs.setMaxWait(Integer.parseInt(maxWait));
		dbs.setMinIdle(Integer.parseInt(maxIdle));
		dbs.setUrl(url);
		dbs.setDriverClassName(driverClassName);
		ds=(DruidDataSource)dbs;
		/**
		* �����ӳ�
		*/
		try {
			conn=ds.getConnection();
			pst = conn.prepareStatement("select * from userinfo");
			rs = pst.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getObject(1)+"\t"+rs.getObject(2)+"\t"+rs.getObject(3));
			}
			
			
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
}
