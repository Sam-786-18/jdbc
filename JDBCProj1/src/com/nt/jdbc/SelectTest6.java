package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest6 {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		String query=null;
		int no=0;
		ResultSet rs=null;
		try{
		 //read inputs
		sc=new Scanner(System.in);
		if(sc!=null){
		System.out.println("Enter student no:");
		no=sc.nextInt();
		}
		//register jdbc driver
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		//establish the connection
		con=DriverManager.getConnection("jdbc:odbc:oradsn","system","manager");
		//create sTatement object
		if(con!=null)
			st=con.createStatement();
		
		//prepare SQL Query
		// select * from student where sno=101;
		query="select * from student where sno="+no;
		System.out.println(query);
		
		//send and execute SQL Query in Db s/w
		if(st!=null)
			rs=st.executeQuery(query);
		//process the ResultSet
		 if(rs.next()){
			 System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
		 }//if
		 else{
			System.out.println("Record not found");
		 }
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
