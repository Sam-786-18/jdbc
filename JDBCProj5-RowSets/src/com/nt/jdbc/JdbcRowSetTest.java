package com.nt.jdbc;

import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;

import oracle.jdbc.rowset.OracleJDBCRowSet;

public class JdbcRowSetTest {

	public static void main(String[] args) {
		JdbcRowSet jrowset=null;
		try{
		//create JDBC RowsSet
		jrowset=new OracleJDBCRowSet();
		if(jrowset!=null){
			jrowset.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			jrowset.setUsername("system");
			jrowset.setPassword("manager");
			jrowset.setCommand("select * from student");
		}
		//execute the Query
		if(jrowset!=null){
			jrowset.execute();
		}
		int cnt=0;
		//process the RowSet
		while(jrowset.next()){
 			System.out.println(jrowset.getInt(1)+"  "+jrowset.getString(2)+" "+jrowset.getString(3));
		}
			
 	}//try
	catch(SQLException se){
		se.printStackTrace();
	}
    catch(Exception e){
    	e.printStackTrace();
    }
	finally{
	  try{
		 if(jrowset!=null){
			 jrowset.close();
		 }
	  }
	  catch(Exception e){
		  e.printStackTrace();
	  }
		
	}//finally
  }//main
}//class
