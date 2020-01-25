package com.nt.jdbc;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;



public class CsTest1 {
   private  static final String CALL_FIRST_PRO1="{ call first_pro1(?,?)}";
   public static void main(String[] args) {
	Scanner sc=null;
	int no=0;
	Connection con=null;
	CallableStatement cs=null;
	int result=0;
	try{
	 sc=new Scanner(System.in);
	 if(sc!=null){
		 System.out.println("Enter number::");
		 no=sc.nextInt();
	 }//if
	 //register JDBC driver
	  Class.forName("oracle.jdbc.driver.OracleDriver");
	  //establish the connection
	  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
	  //create CallableStatement object
	  if(con!=null)
		  cs=con.prepareCall(CALL_FIRST_PRO1);
	  //register OUT params with JDBC types
	  if(cs!=null)
		  cs.registerOutParameter(2,Types.INTEGER);
	  //set values to IN params
	  if(cs!=null)
		  cs.setInt(1,no);
	  //call /Execute pl/sql procedure
	  if(cs!=null)
	    cs.execute();
	  //Gather results from OUT params
	  if(cs!=null){
		 result=cs.getInt(2); 
	  }
	  System.out.println("SQL Value::"+result);
 
    }//try
	catch(ClassNotFoundException cnf){
		cnf.printStackTrace();
	}
	catch(SQLException e){
   	 System.out.println("No Data found");
   }
   catch(Exception e){
   	e.printStackTrace();
   }
	finally {
		// close jdbc objs
		try {
			if (cs != null)
				cs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		try {
			if (con!= null)
				con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		try {
			if (sc != null)
				sc.close();
		} catch (Exception se) {
			se.printStackTrace();
		}
		
		}//finally
	}//main
}//class
