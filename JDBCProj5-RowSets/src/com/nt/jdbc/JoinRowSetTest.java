package com.nt.jdbc;

import java.sql.SQLException;

import oracle.jdbc.rowset.OracleCachedRowSet;
import oracle.jdbc.rowset.OracleJoinRowSet;

public class JoinRowSetTest {
  public static void main(String[] args) {
	     OracleCachedRowSet crs1=null;
		 OracleCachedRowSet crs2=null;
		 OracleJoinRowSet joinRS=null;
		 try{
	     //cached RowSet1
	       crs1 = new OracleCachedRowSet();
	      crs1.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
	      crs1.setUsername("system");
	      crs1.setPassword("manager");
	      crs1.setCommand("select empno,ename,deptno from emp");
	      crs1.setMatchColumn(3);
	      crs1.execute();
	      
	     //Cached RowSet2
	       crs2 = new OracleCachedRowSet();
	      crs2.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
	      crs2.setUsername("system");
	      crs2.setPassword("manager");
	      crs2.setCommand("select deptno,dname,loc from dept");
	      crs2.setMatchColumn(1);
	      crs2.execute();
	     
	      //JoinRowSet
	      joinRS=new OracleJoinRowSet();
	      joinRS.addRowSet(crs2);
	      joinRS.addRowSet(crs1);
	   
	      //Process JoinRowSet      
	   while (joinRS.next()) {
		  System.out.println(joinRS.getString(1)+"  "+joinRS.getString(2)+"  "+joinRS.getString(3)+"  "+joinRS.getString(4)+"  "+joinRS.getString(5));
	      }
		 }//try
		 catch(SQLException se ){
			 se.printStackTrace();
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
   }
}
