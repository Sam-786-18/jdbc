package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleToMysqlTest {
 private static final String INSERT_QUERY="INSERT INTO STUDENT VALUES(?,?,?)";
 private static final String SELECT_QUERY="SELECT * FROM STUDENT";
	public static void main(String[] args) {
		Connection oraCon=null;
		Connection mysqlCon=null;
		Statement st=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int no=0;
		String name=null;
		String addrs=null;
		int count=0;
		try{
		 //register jdbc drivers
		  Class.forName("oracle.jdbc.driver.OracleDriver");
		  Class.forName("com.mysql.jdbc.Driver");
		 //Establish the connections
		  oraCon=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		  mysqlCon=DriverManager.getConnection("jdbc:mysql:///ntaj48db1","root","root");
		 //create Statement object
		  if(oraCon!=null)
			  st=oraCon.createStatement();
		  //create PreparedStatement object
		  if(mysqlCon!=null)
			  ps=mysqlCon.prepareStatement(INSERT_QUERY);
		  //get Records from oracle Db table
		  if(st!=null)
			  rs=st.executeQuery(SELECT_QUERY);
		  //copy records from oracle Db table to mysql DB table
		  while(rs.next()){
			  //get Each record
			  no=rs.getInt(1);
			  name=rs.getString(2);
			  addrs=rs.getString(3);
			  //set each record values to insert Query of mysql
			  ps.setInt(1,no);
			  ps.setString(2,name);
			  ps.setString(3,addrs);
			  //execute the query
			  count=ps.executeUpdate();
			  if(count==1)
				  System.out.println("record copied");
		    }//while
		  System.out.println("All records are copied");
		  }//try
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf){
			cnf.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			
			try{
				if(rs!=null)
					rs.close();
				}
				catch(SQLException se){
					se.printStackTrace();
			}
			
			try{
				if(st!=null)
					st.close();
				}
				catch(SQLException se){
					se.printStackTrace();
			}
			
			try{
				if(ps!=null)
					ps.close();
				}
				catch(SQLException se){
					se.printStackTrace();
			}
			
			try{
				if(oraCon!=null)
					oraCon.close();
				}
				catch(SQLException se){
					se.printStackTrace();
				}
			try{
				if(mysqlCon!=null)
					mysqlCon.close();
				}
				catch(SQLException se){
					se.printStackTrace();
				}
		}//finally	

	}//main
}//class
