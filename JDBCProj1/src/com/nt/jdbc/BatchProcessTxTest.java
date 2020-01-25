package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchProcessTxTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		int result[]=null;
		boolean flag=false;
		
		try{
		// register JDBC driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// establish the connection
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
		//create Statement object
		if(con!=null)
			st=con.createStatement();
		//begin Transaction
		if(con!=null)
			con.setAutoCommit(false);
		//prepare batch
		if(st!=null){
			st.addBatch("insert into student values(1206,'raja','hyd')");
			st.addBatch("update student set sadd='china3' where sno<=-50");
			st.addBatch("delete from student where sno>=2000");
		}//if
		//execute the batch
		if(st!=null){
		  result=st.executeBatch();	
		}
		//perform Tx mgmt
		for(int i=0;i<result.length;++i){
			if(result[i]==0){
				flag=true;
				break;
			}
		}
		if(flag==true){
			con.rollback();
			System.out.println("Tx rolledback");
		}
		else{
			con.commit();
			System.out.println("Tx committed");
		}
		
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
