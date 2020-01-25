package com.nt.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class FlexibleTest {

	public static void main(String[] args) {
     InputStream is=null;
     Properties props=null;
     Connection con=null;
     PreparedStatement ps=null;
     ResultSet rs=null;
		try{
		 // locate proeprties file
		 is=new FileInputStream("src/com/nt/commons/DB.properties");
  	    //Load data into Properties obj from properties file
		 props=new Properties();
		 props.load(is);
		 
		 //register driver
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 //establish the connection
		 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",props);
				                         
		 //create PreparedSTatement object
		 if(con!=null)
			 ps=con.prepareStatement("select * from student");
		 //execute the SQL query
		 if(ps!=null)
			rs=ps.executeQuery();
		 //process the ResultSet
		 if(rs!=null){
			 while(rs.next()){
				 System.out.println(rs.getInt(1)+"  "+rs.getString(2)+" "+rs.getString(3));
			 }
		 }
		}//try
		catch(SQLException| ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			// close jdbc objs
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception se) {
				se.printStackTrace();
			}
			try {
 			  if (is!= null)
				is.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}

		} // finally
	}//main
}//class
