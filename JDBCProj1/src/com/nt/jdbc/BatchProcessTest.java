package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchProcessTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		int result[]=null;
		int sum=0;
		
		try{
		// register JDBC driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// establish the connection
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
		//create Statement object
		if(con!=null)
			st=con.createStatement();
		//prepare batch
		if(st!=null){
			st.addBatch("insert into student values(1202,'raja','hyd')");
			st.addBatch("update student set sadd='china1' where sno>=10000");
			st.addBatch("delete from student where sno>=5000");
		}//if
		//execute the batch
		if(st!=null){
		  result=st.executeBatch();	
		}
		//process the result
		for(int i=0;i<result.length;++i){
			sum=sum+result[i];
		}
		System.out.println("no.of records effected"+sum);
		
		}//try
		catch(SQLException  se){
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
				if (st != null)
					st.close();
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
