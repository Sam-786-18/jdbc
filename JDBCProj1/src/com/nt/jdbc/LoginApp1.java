package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginApp1 {
 private static final String AUTHENTICATE_QUERY="SELECT COUNT(*) FROM USERLIST WHERE UNAME=? AND PWD=?";  
	public static void main(String[] args) {
		Scanner sc=null;
		String user=null,pass=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int count=0;
		//read inputs
		try{
		 sc=new Scanner(System.in);
		 if(sc!=null){
			 System.out.println("Enter username::");
			 user=sc.nextLine(); //gives raja
			 System.out.println("Enter password::");
			 pass=sc.nextLine(); //gives rao
		 }//if
		 //register jdbc driver
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 //establish the connection
		 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		 //create PareparedStatement object
		 if(con!=null)
			ps=con.prepareStatement(AUTHENTICATE_QUERY);
		 //set values to Query params
		 if(ps!=null){
			 ps.setString(1,user);
			 ps.setString(2,pass);
		 }
		 //execute the Query
		 if(ps!=null){
			rs=ps.executeQuery();
		 }
		 //process the ResultSet
		 if(rs!=null){
			if(rs.next()){
			 count=rs.getInt(1);	
			}//while
		 }//if
		 if(count==0)
			 System.out.println("Invalid Credentials");
		 else
			 System.out.println("valid Credentials"); 
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
				if(ps!=null)
					ps.close();
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
