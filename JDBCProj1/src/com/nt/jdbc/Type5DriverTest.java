package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Type5DriverTest {

	public static void main(String[] args) {
	  Connection con=null;	
	  Statement st=null;
	  ResultSet rs=null;
		try{
		//register JDBC driver
		//Class.forName("com.ddtek.jdbc.oracle.OracleDriver");
		
		//establish the connection
		con=DriverManager.getConnection("jdbc:datadirect:oracle://localhost:1521;ServiceName=xe", "system","manager");
		//create Statement
		if(con!=null)
		  st=con.createStatement();
		//send and execute SQL Query
		if(st!=null)
		 rs=st.executeQuery("select * from student");
		//process the ResultSet
		if(rs!=null){
		 while(rs.next()){
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+" "+rs.getString(3));
		  }//while
		}//if
	}//try
	/*catch(ClassNotFoundException  | SQLException e ){
		e.printStackTrace();
	}*/
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
