package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsFxTest5 {
 private static final	String CALL_FX="{?=call FX_GET_EMP_DETAILS(?,?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		CallableStatement cs=null;
		int no=0;
		try{
		 //read inputs
		  sc=new Scanner(System.in);
		  if(sc!=null){
			  System.out.println("Enter Employee no:");
			  no=sc.nextInt();
		  }//if
			// register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			//create CallableStatement object
			if(con!=null)
				cs=con.prepareCall(CALL_FX);
			//register OUT,RETURN Params with JDBC types
			if(cs!=null){
			     cs.registerOutParameter(1,Types.INTEGER); //return param
		         cs.registerOutParameter(3,Types.VARCHAR); //out param
		         cs.registerOutParameter(4,Types.VARCHAR); //out param
			}
			//set values to IN param
			if(cs!=null)
			  cs.setInt(2,no);
			//call PL/SQL function
			if(cs!=null)
				cs.execute();
			//gather results from OUT,return params
			if(cs!=null){
				System.out.println("Emp Salary::"+cs.getInt(1));
				System.out.println("Emp Name:::"+cs.getString(3));
				System.out.println("Emp Desg :::"+cs.getString(4));
			}//if
		}//try
		catch(SQLException se){
			System.out.println("Record not Found");
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf){
			cnf.printStackTrace();
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
