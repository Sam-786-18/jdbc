package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TextProcessTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try{
		 //register jdbc driver
		  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		  //establish the connection
		  con=DriverManager.getConnection("jdbc:odbc:txdsn");
		  //create Statement object
		  if(con!=null)
			  st=con.createStatement();
		  //send and execute SQL Query
		  if(st!=null)
			rs=st.executeQuery("select * from file1.csv");
		  //process the ResultSet
		  if(rs!=null){
			  while(rs.next()){
				  System.out.println(rs.getInt(1)+"  "+rs.getString(2)+" "+rs.getString(3));
			  }
		  }//if
		  
		  //for insert operation
		  PreparedStatement ps=con.prepareStatement("insert into file1.csv values(?,?,?)");
		  ps.setInt(1,1133);
		  ps.setString(2,"ramesh");
		  ps.setString(3,"hyd");
		  int result=ps.executeUpdate();
		  if(result==1)
			  System.out.println("record inserted");
		  else
			 System.out.println("record not inserted");
		  
		}//try
		catch(ClassNotFoundException cnf){
			cnf.printStackTrace();
		}
		catch(SQLException se){
			se.printStackTrace();
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
				if(con!=null)
					con.close();
			  }
			  catch(SQLException se){
				  se.printStackTrace();
			  }
		}//finally

	}//main
}//class
