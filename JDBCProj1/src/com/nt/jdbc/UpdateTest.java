package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest {

	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		String newName=null,newAddrs=null;
		Connection con=null;
		Statement st=null;
		String query=null;
		int result=0;
		try{
		 sc=new Scanner(System.in);
		 if(sc!=null){
		  System.out.println("Enter existing number::");
		  no=sc.nextInt(); //gives 101
		  System.out.println("Enter new name::");
		  sc.nextLine();
		  newName=sc.nextLine(); //gives new raja
		  System.out.println("Enter new Address::");
		  newAddrs=sc.nextLine();// gives  new hyd
		 }//if
		 //Convert input values as required for the SQL Query
		 newName="'"+newName+"'"; //gives 'new raja'
		 newAddrs="'"+newAddrs+"'"; //gives 'new hyd'
		 
		 //register driver
		 Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		//establish the connection
		 con=DriverManager.getConnection("jdbc:odbc:oradsn","system","manager");
		//create Statement object
		 if(con!=null)
		  st=con.createStatement();
		 
		 //prepare SQL Query
		 //update student set sname='new raja',sadd='new hyd' where sno=101
		 query="update student set sname="+newName+",sadd="+newAddrs+" where sno="+no;
		 System.out.println(query);
		 
		 //send ande execute SQL Query in Db s/w
		 if(st!=null)
			 result=st.executeUpdate(query);
		 // process the result
		 if(result==0)
			 System.out.println("Record not found to update");
		 else
		   System.out.println(result+"no.of record(s) are updated");
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
