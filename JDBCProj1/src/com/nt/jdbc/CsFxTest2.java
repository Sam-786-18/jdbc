package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

public class CsFxTest2 {
 private static final String CALL_VIEW_DELETE_STUDENT="{?=call  FX_VIEW_DELETE_STUDENT_DETAILS(?,?)}";  
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		int count=0;
		try{
		 //read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter student no:");
				no=sc.nextInt();
			}//if
			
			// register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			//create CallableStatment object
			if(con!=null)
				cs=con.prepareCall(CALL_VIEW_DELETE_STUDENT);
			//register OUT,RETURN params with JDBC types
			if(cs!=null){
			  cs.registerOutParameter(1,OracleTypes.CURSOR);
			  cs.registerOutParameter(3,Types.INTEGER);
			}
			//set values to IN params
			if(cs!=null)
				cs.setInt(2,no);
			//call PL/SQL function
			if(cs!=null)
				cs.execute();
			//gather results from OUT,RETURN Params
			if(cs!=null){
				rs=(ResultSet)cs.getObject(1);
				if(rs!=null){
					if(rs.next()){
						System.out.println("Student Details::");
						System.out.println(rs.getInt(1)+"  "+rs.getString(2)+" "+rs.getString(3));
					}//if
				}//if
			}//if
			if(cs!=null){
			 count=cs.getInt(3);	
			  if(count==0)
				  System.out.println("Record not deleted");
			  else
				  System.out.println("Record deleted");
			}//if
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
	finally {
		// close jdbc objs
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
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
