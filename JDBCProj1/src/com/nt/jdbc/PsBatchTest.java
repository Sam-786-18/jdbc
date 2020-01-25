package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PsBatchTest {

	public static void main(String[] args) {
		 Connection con=null;
		 PreparedStatement ps=null;
		 int result[]=null;
		 int sum=0;
		 try{
		//register jdbc driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//establish the connection
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
		//create PreparedStaement object 
		 if(con!=null)
			ps=con.prepareStatement("insert into student values(?,?,?)");
		 //add multiple sets of query param values to batch
		  if(ps!=null){
			 ps.setInt(1,2222);
			 ps.setString(2,"rajesh");
			 ps.setString(3,"hyd");
			 ps.addBatch(); //1st set
			 
			 ps.setInt(1,3333);
			 ps.setString(2,"raj");
			 ps.setString(3,"hyd1");
			 ps.addBatch(); //2nd set
			 
			 ps.setInt(1,4444);
			 ps.setString(2,"ravi");
			 ps.setString(3,"hyd2");
			 ps.addBatch(); //3rd set

		  }//if
		  //execute Batch
		  if(ps!=null){
			result=ps.executeBatch();  
		  }
		  
		  //process the reuslt
		  for(int i=0;i<result.length;++i){
			  sum=sum+result[i];
		  }
		  System.out.println("no.of records effected"+sum);
		 
		 }//try
		 catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// close jdbc objs
				try {
					if (ps != null)
						ps.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
				try {
					if (con != null)
						con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			} // finally
	}//main
}//class
