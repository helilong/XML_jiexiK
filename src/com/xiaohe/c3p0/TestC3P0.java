package com.xiaohe.c3p0;



import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class TestC3P0 {
	private static ComboPooledDataSource ds=null;
	//�ھ�̬������д������ݿ����ӳ�
	
	static{
//		
//		//ͨ�����봴��c3p0���ݿ����ӳ�
//		ds =new ComboPooledDataSource();
//		try {
//			ds.setDriverClass("com.mysql.jdbc.Dirver");
//			ds.setJdbcUrl("");
//		} catch (PropertyVetoException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		//ͨ����ȡc3p0��xml�����ļ� ��������Դ��c3p0��xml�����ļ� �������src ��
		//ds=new ComboPooledDataSource();//Ĭ������
		
		try {
			
			ds= new ComboPooledDataSource("MySQL");
			
		} catch (Exception e) {
			throw new ExceptionInInitializerError();
		}
		
		
	}
	public static Connection getConnection() throws SQLException{
		
		//������Դ�л�ȡ���ݿ�����
		return ds.getConnection();
	}
	
	public static void close( Connection conn){
		if(conn!=null){
			try {
				conn.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
