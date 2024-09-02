package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.protocol.Resultset;

public class DatabaseUtility {

	Connection conn;
	public void getConnection(String url,String username,String password)
	{
		try {
			Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			conn = DriverManager.getConnection(url, username, password);
		} catch(Exception e){
			
		}
	}
	public void getConnection()
	{
		try {
			Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			conn = DriverManager.getConnection("http://106.51.90.215:3333/projects", "root", "rooot");
		} catch(Exception e){
		}
	}
	
	public ResultSet executeSelectQuery(String query) throws Exception
	{
		ResultSet result= null;
		try {
			Statement stmt= conn.createStatement();
			result=stmt.executeQuery(query);
		} catch(Exception e) {
		}
		return result;
	}
	
	public int executeNonselectQuery(String query) throws Exception
	{
		int result=0;
		try {
			Statement stmt= conn.createStatement();
			result=stmt.executeUpdate(query);
		} catch(Exception e){
		}
		return result;
	}
	public void closeConnection()
	{
		try {
			conn.close();
		} catch(Exception e) {
		}
	}
}
