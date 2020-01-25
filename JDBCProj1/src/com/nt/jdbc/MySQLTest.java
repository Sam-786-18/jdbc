package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLTest {

	public static void main(String[] args) {
	  Connection con=null;	
	  Statement st=null;
	  ResultSet rs=null;
		try{
		//register JDBC driver
		//Class.forName("com.mysql.jdbc.Driver");
		Class.forName("org.gjt.mm.mysql.Driver");
		
		//establish the connection
		//con=DriverManager.getConnection("jdbc:mysql:///ntaj48db", "root","root");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ntaj48db1", "root","root");
		//create Statement
		if(con!=null)
		  st=con.createStatement();
		//send and execute SQL Query
		if(st!=null)
		 rs=st.executeQuery("select * from product");
		//process the ResultSet
		if(rs!=null){
		 while(rs.next()){
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+" "+rs.getFloat(3));
		  }//while
		}//if
	}//try
	catch(ClassNotFoundException  | SQLException e ){
		e.printStackTrace();
	}
    catch(Exception e){
    	e.printStackTrace();
    }
		finally{
			//close jdbc objs
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
				if(con!=null)
					con.close();
				}
				catch(SQLException se){
					se.printStackTrace();
				}
		}//finally
	}//main
}//class
