package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class SavePointTest {

	public static void main(String[] args) {
	  Connection con=null;	
	  Statement st=null;
	  int result1=0,result2=0;
	  Savepoint sp=null;
		try{
		//register jdbc driver
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 //establish the connection
		 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		 //create Statement object
		 if(con!=null)
		   st=con.createStatement();
		 //Begin Transaction
		 if(con!=null)
			 con.setAutoCommit(false);
		 //execute query
		 if(st!=null){
		  result1=st.executeUpdate("insert into student values(008,'bond','every where')");
		 //set Save point
		  sp=con.setSavepoint("p1");
		   result2=st.executeUpdate("update student set sadd='goa1' where sno=2221");
		    //rollback upto SavePoint
		  con.rollback(sp);
		 
		 //commit Tx 
		 con.commit();
		 }
		 
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
			
		}

	}

}
