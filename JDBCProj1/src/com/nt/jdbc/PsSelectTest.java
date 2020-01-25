package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/* App To get emp details who is highest salary
 * version: 1.0
 * author :Team-J
 * data  : 02/16/2017
 */

public class PsSelectTest {
 private static final String EMP_MAX_SAL_DETAILS="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL=(SELECT MAX(SAL) FROM EMP)";
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		String query=null;
		ResultSet rs=null;
		boolean flag=false;
		try{
		 //register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","System", "manager");
			//create PreparedStatement 
			if(con!=null)
				ps=con.prepareStatement(EMP_MAX_SAL_DETAILS);

			//send and execute SQL Query in Db s/w
			if(ps!=null)
				rs=ps.executeQuery();
			
			//send and execute SQL Query in Db s/w
			if(rs!=null){
			 while(rs.next()){
				 flag=true;
				 System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+" "+rs.getInt(4));
			 }//while
			 if(flag==false)
				 System.out.println("Records not found");
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
