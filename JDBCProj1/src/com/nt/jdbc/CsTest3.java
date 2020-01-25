package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsTest3 {
 private static final String CALL_AUTH="{call PRO_AUTH(?,?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
		String user=null,pass=null;
		Connection con=null;
		CallableStatement cs=null;
		String result=null;
		try{
			//read inputs
		 sc=new Scanner(System.in);
		 if(sc!=null){
			System.out.println("Enter username:");
			user=sc.nextLine(); //gives raja
			System.out.println("Enter password::");
			pass=sc.nextLine(); //gives hyd
		 }//if
			// register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			//create CallableStatement object
			if(con!=null)
			 cs=con.prepareCall(CALL_AUTH);
			//register OUT params with JDBC types
			if(cs!=null)
				cs.registerOutParameter(3,Types.VARCHAR);
			//set values to IN params
			if(cs!=null){
				cs.setString(1,user);
				cs.setString(2,pass);
			}
			//call PL/SQL Procedure
			if(cs!=null)
				cs.execute();
			//gather result from Out params
			if(cs!=null)
				result=cs.getString(3);
			
			System.out.println(result);
		 
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
				if (cs != null)
					cs.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (con != null)
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
		} // finally
	}//main
}//class
