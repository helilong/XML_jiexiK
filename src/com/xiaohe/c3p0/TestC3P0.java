package com.xiaohe.c3p0;



import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class TestC3P0 {
	private static ComboPooledDataSource ds=null;
	//在静态代码块中创建数据库连接池
	
	static{
//		
//		//通过代码创建c3p0数据库连接池
//		ds =new ComboPooledDataSource();
//		try {
//			ds.setDriverClass("com.mysql.jdbc.Dirver");
//			ds.setJdbcUrl("");
//		} catch (PropertyVetoException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		//通过读取c3p0的xml配置文件 创建数据源，c3p0的xml配置文件 必须放在src 下
		//ds=new ComboPooledDataSource();//默认配置
		
		try {
			
			ds= new ComboPooledDataSource("MySQL");
			
		} catch (Exception e) {
			throw new ExceptionInInitializerError();
		}
		
		
	}
	public static Connection getConnection() throws SQLException{
		
		//从数据源中获取数据库连接
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
