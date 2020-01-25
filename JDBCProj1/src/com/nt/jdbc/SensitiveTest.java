package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SensitiveTest {

	public static void main(String[] args) {
	 Connection con=null;	
	 Statement st=null;
	 ResultSet rs=null;
	 int count=0;
	
		try{
		//register jdbc driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		 //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		//establish the connection
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
		 //con=DriverManager.getConnection("jdbc:odbc:oradsn","system","manager");
	    //create Statment object
		if(con!=null)
			st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		//create ScrollableResultSet object
		if(st!=null)
			rs=st.executeQuery("select sno,sname,sadd from student");
		//process the ResultSet
		if(rs!=null){
			while(rs.next()){
				rs.refreshRow();
				if(count==0){
					System.out.println("modify db table records");
					Thread.sleep(30000); //modify records from DB table
				}
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
				count++;
			}
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
