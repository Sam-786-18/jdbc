package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectNonSelectTest {

	public static void main(String[] args) {
		Scanner sc=null;
		String query=null;
		Connection con=null;
		Statement st=null;
		boolean flag=false;
		ResultSet rs=null;
		int result=0;
		try{
		 //read inputs
		 sc=new Scanner(System.in);
		 if(sc!=null){
		  System.out.println("Enter SQL Query");
		  query=sc.nextLine();
		 }//if
		 
		 //register driver
		 Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		//establish the connection
		 con=DriverManager.getConnection("jdbc:odbc:oradsn","system","manager");
		//create Statement object
		 if(con!=null)
		  st=con.createStatement();
		 
		 //send and execute Query
		 if(st!=null)
			flag=st.execute(query);
		 if(st!=null){
			 if(flag==true){
				 System.out.println("Select Query Executed");
				 rs=st.getResultSet();
				if(rs!=null){
					while(rs.next()){
					 System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
					}//while
				}//if
			 }//if
			 else{
				 System.out.println("Non-Select Query is executed");
				result=st.getUpdateCount();
				System.out.println("no.of records that are effected::"+result);
			 }//else
		 }//if
		}//try
		catch(SQLException se){
			System.out.println("Record not inserted");
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
				if(con!=null)
					con.close();
				}
				catch(SQLException se){
					se.printStackTrace();
				}
			try{
				if(sc!=null)
					sc.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
		}//finally
	}//main
}//class
