package com.nt.jdbc;


import java.sql.SQLException;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.Predicate;

import oracle.jdbc.rowset.OracleFilteredRowSet;


class Filter1 implements Predicate {
    private String colName;

    public Filter1(String colName) {
        this.colName = colName;
        }

    public boolean evaluate(RowSet rs) {
    	System.out.println("evaluate");
        try {
            CachedRowSet crs = (CachedRowSet) rs;
            String colVal = crs.getString(colName);
            if (colVal!= null && (colVal.charAt(0) == 'A' || colVal.charAt(0) == 'a')) {
            return true;
             } else {
            return false;
             }
           } catch (Exception e) {
                return false;
          }
       }//emethod

	@Override
	public boolean evaluate(Object value, int column) throws SQLException {
		return false;
	}

	@Override
	public boolean evaluate(Object value, String columnName)
			throws SQLException {
		return false;
	}
}//Filter1 class

public class FilteredRowSetTest {
   public static void main(String[] args)  {
	   FilteredRowSet  frs=null;
  	   try{
      //create Filtered Rowset
    	 frs = new OracleFilteredRowSet();
        frs.setUsername("system");
        frs.setPassword("manager");
        frs.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        frs.setCommand("select * from emp");
        //Add filter
        frs.setFilter(new Filter1("ename"));
           frs.execute();
           
        while (frs.next()) {
        	System.out.println(frs.getInt(1)+"  "+frs.getString(2)+"  "+frs.getString(3));
             }
  	   }//try
  	   catch(SQLException se){
  		   se.printStackTrace();
  	   }
  	   catch(Exception e){
  		   e.printStackTrace();
  	   }
   }//main
}//class
