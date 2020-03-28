package com.xiaohe.util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.alibaba.druid.pool.DruidDataSource;
public class DBUtil {
	private DruidDataSource ds = null;

	Connection conn=null;
	ResultSet rs=null;
	PreparedStatement pst=null;

	public DBUtil(){
	ResourceBundle rb=ResourceBundle.getBundle("prop");
	String username=rb.getString("username");
	String password = rb.getString("password");
	String maxActive = rb.getString("maxActive");
	String maxWait = rb.getString("maxWait");
	String maxIdle = rb.getString("maxIdle");
	String url = rb.getString("url");
	String driverClassName = rb.getString("driverClassName");

	//创建数据源对象
	DruidDataSource dbs = new DruidDataSource();
	dbs.setUsername(username);
	dbs.setPassword(password);
	dbs.setMaxActive(Integer.parseInt(maxActive));
	dbs.setMaxWait(Integer.parseInt(maxWait));
	dbs.setMinIdle(Integer.parseInt(maxIdle));
	dbs.setUrl(url);
	dbs.setDriverClassName(driverClassName);
	ds=(DruidDataSource)dbs;
	}


	/**
	* 打开连接池
	*/
	public void getConn(){
	try {
	conn=ds.getConnection();
	} catch (Exception e) {
	e.printStackTrace();
	}
	}

	/**
	* 查询数据
	* @param sql
	* @param params
	* @return
	*/
	public ResultSet execQuery(String sql,Object[] params){
	getConn();
	try {
	pst = conn.prepareStatement(sql);
	if(params!=null){
	for(int i=0;i<params.length;i++){
	pst.setObject(i+1, params[i]);
	}
	}
	rs = pst.executeQuery();
	} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	return rs;
	}




	/**
	* 增删改
	* @param sql
	* @param obj
	* @return
	*/
	public int getUpdate(String sql,Object[] obj){
	int count=0;
	//打开数据库链接对象
	this.getConn();
	try {
	//执行sql语句
	pst=conn.prepareStatement(sql);
	//判断是否有参数传过来
	if(obj!=null){
	for(int i=0;i<obj.length;i++){
	//赋值
	pst.setObject(i+1,obj[i]);//这里i+1是因为，赋值的时候是从第一位开始的
	}
	}
	count=pst.executeUpdate();
	} catch (Exception e) {
	// TODO: handle exception
	e.printStackTrace();
	}finally{
	this.getClose(null);
	}
	return count;
	}


	/**
	* 关闭连接对象
	*/
	public void getClose(ResultSet rs){
	try {
	if(conn!=null){
	conn.close();
	}
	if(rs!=null){
	rs.close();
	}
	if(pst!=null){
	pst.close();
	}
	} catch (Exception e) {
	// TODO: handle exception
	e.printStackTrace();
	}
	}
}
