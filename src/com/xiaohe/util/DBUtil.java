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
	}


	/**
	* �����ӳ�
	*/
	public void getConn(){
	try {
	conn=ds.getConnection();
	} catch (Exception e) {
	e.printStackTrace();
	}
	}

	/**
	* ��ѯ����
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
	* ��ɾ��
	* @param sql
	* @param obj
	* @return
	*/
	public int getUpdate(String sql,Object[] obj){
	int count=0;
	//�����ݿ����Ӷ���
	this.getConn();
	try {
	//ִ��sql���
	pst=conn.prepareStatement(sql);
	//�ж��Ƿ��в���������
	if(obj!=null){
	for(int i=0;i<obj.length;i++){
	//��ֵ
	pst.setObject(i+1,obj[i]);//����i+1����Ϊ����ֵ��ʱ���Ǵӵ�һλ��ʼ��
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
	* �ر����Ӷ���
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
