package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/* App To get emp details who is highest salary
 * version: 1.0
 * author :Team-J
 * data  : 02/16/2017
 */

public class SelectTest4 {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		String query=null;
		ResultSet rs=null;
		boolean flag=false;
		try{
		 //register jdbc driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:odbc:oradsn","System", "manager");
			//create Statement 
			if(con!=null)
				st=con.createStatement();
			//prepare Query
			// select empno,ename,job,sal from emp where sal=(select max(sal) from emp)
			query="select empno,ename,job,sal from emp where sal=(select max(sal) from emp)";
            //send and execute SQL Query in Db s/w
			if(st!=null)
				rs=st.executeQuery(query);
			//send and execute SQL Query in Db s/w
			if(rs!=null){
			 while(rs.next()){
				 flag=true;
				 System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+" "+rs.getInt(4));
			 }//while
			 if(flag==false)
				 System.out.println("Records not found");
			}//if
			System.out.println("connection obj class name:"+con.getClass());
			System.out.println("Statement  obj class name:"+st.getClass());
			System.out.println("ResultSet  obj class name:"+rs.getClass());
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
		}//finally
	}//main
}//class
