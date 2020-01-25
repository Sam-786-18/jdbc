package com.nt.jdbc;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import oracle.jdbc.rowset.OracleCachedRowSet;

public class CachedRowSetTest {
	public static void main(String[] args) {
		CachedRowSet crowset=null;
		try{
		  crowset=new OracleCachedRowSet();
		  crowset.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		  crowset.setUsername("system");
		  crowset.setPassword("manager");
		  crowset.setCommand("select * from student");
		  //execute query
		  crowset.execute();
		  //process the RowSet
		  System.out.println("before Modification..");
		  while(crowset.next()){
			  System.out.println(crowset.getInt(1)+"  "+crowset.getString(2)+" "+crowset.getString(3));
		  }
		  
		  Thread.sleep(60000); //stop DB s/w here
		  crowset.absolute(3);
		  crowset.updateInt(1,5555);
		  crowset.updateString(2,"mukesh");
		  crowset.updateString(3,"mp");
		  crowset.updateRow();
		  System.out.println("Rowset updated in offline mode");
		  Thread.sleep(60000); //restart DB s/w here
		  crowset.acceptChanges();
		  while(crowset.next()){
			  System.out.println(crowset.getInt(1)+"  "+crowset.getString(2)+" "+crowset.getString(3));
		  }
		  System.out.println("after Modification..");
		  
		}//try
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}//method
}//class
