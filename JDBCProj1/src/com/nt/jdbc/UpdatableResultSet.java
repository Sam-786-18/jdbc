package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdatableResultSet {
	public static void main(String[] args) {
		 Connection con=null;	
		 Statement st=null;
		 ResultSet rs=null;
		
			try{
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			 //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
			//con=DriverManager.getConnection("jdbc:odbc:oradsn","system","manager");
		    //create Statment object
			if(con!=null)
				st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						               ResultSet.CONCUR_UPDATABLE);
			//create ScrollableResultSet object
			if(st!=null)
				rs=st.executeQuery("select sno,sname,sadd from student");
			//read operation
			if(rs!=null){
				System.out.println("read opeation");
				while(rs.next()){
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
				}
			}
			//insert operation
		/*	if(rs!=null){
				rs.moveToInsertRow();
				rs.updateInt(1,456);
				rs.updateString(2,"ramesh");
				rs.updateString(3,"vizag");
				rs.insertRow();
				System.out.println("Recrod inserted");
			}*/
		   //update opeartion
		/*	if(rs!=null){
				rs.absolute(3);
				rs.updateString(3,"vizag");
				rs.updateRow();
				System.out.println("Record updated");
			}*/
		  //delete operation
			if(rs!=null){
			 rs.absolute(3);
			 rs.deleteRow();
			 System.out.println("Record deleted");
			}
			
	}//try
			catch(SQLException se){
				se.printStackTrace();
			}
		/*	catch(ClassNotFoundException cnf){
				cnf.printStackTrace();
			}*/
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
