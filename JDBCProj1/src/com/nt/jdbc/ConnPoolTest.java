 package com.nt.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

public class ConnPoolTest {

	public static void main(String[] args) {
		Connection con=null;
		OracleConnectionPoolDataSource ds=null;
		Statement st=null;
		ResultSet rs=null;
		try{
		 //create DataSource object pointing jdbc con pool
		 ds=new OracleConnectionPoolDataSource();
		 if(ds!=null){
		   ds.setDriverType("thin");
		   ds.setUser("system");
		   ds.setPassword("manager");
		   ds.setURL("jdbc:oracle:thin:@localhost:1521:xe");
		 }
		 //get con object from jdbc con pool
		 if(ds!=null){
			 con=ds.getConnection();
		 }
		 //create other JDBc objects
		 if(con!=null){
			 st=con.createStatement();
		 }
		 //send and execute SQL Query in Db s/w
		 if(st!=null){
			 rs=st.executeQuery("select * from student");
		 }
		 //process the ReusltSet
		 if(rs!=null){
			 while(rs.next()){
				 System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
			 }
		 }//if
		 
		}//try
		catch(SQLException se){
			se.printStackTrace();
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
			   con.close(); //--->releases the con back to con pool
			}
			catch(SQLException se){
				se.printStackTrace();
			}
		}//finally
	}//main
}//class

