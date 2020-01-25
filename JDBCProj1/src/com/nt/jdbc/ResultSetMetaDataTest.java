package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetMetaDataTest {

	public static void main(String[] args) {
	  Connection con=null;	
	  Statement st=null;
	  ResultSet rs=null;
	  ResultSetMetaData rsmd=null;
	  int colCount=0;
		try{
		//register JDBC driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//establish the connection
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
		//create Statement
		if(con!=null)
		  st=con.createStatement();
		//send and execute SQL Query
		if(st!=null)
		 rs=st.executeQuery("select * from "+args[0]);
		//create ResultSetMetaData object
		if(rs!=null){
			rsmd=rs.getMetaData();
		}
		//print column names
		if(rsmd!=null){
		  colCount=rsmd.getColumnCount();
		  for(int i=1;i<=colCount;++i){
			  System.out.print(rsmd.getColumnLabel(i)+"  ");
		  }
		}
		System.out.println();
		//print col datatypes
		if(rsmd!=null){
		  for(int i=1;i<=colCount;++i){
			  System.out.print(rsmd.getColumnTypeName(i)+"  ");
		  }
		}
		
		System.out.println();
		//process the ResultSet
		if(rs!=null){
		 while(rs.next()){
			
			 for(int i=1;i<=colCount;++i){
				 System.out.print(rs.getString(i)+"   ");
			 }
			 
			 System.out.println();
			 
		  }//while
		}//if
		
		System.out.println("more info about each coulmn:");
		for(int i=1;i<=colCount;++i){
			System.out.println("Column number:"+i);
			System.out.println("Column name:"+rsmd.getColumnName(i));
			System.out.println("Column type:"+rsmd.getColumnTypeName(i));
			System.out.println("column size:"+rsmd.getColumnDisplaySize(i));
			System.out.println("Column is signed:"+rsmd.isSigned(i));
			System.out.println("column is writable :"+rsmd.isWritable(i));
			System.out.println("column is read Only:"+rsmd.isReadOnly(i));
			System.out.println("column is Nullable:"+rsmd.isNullable(i));
		}
	}//try
	catch(ClassNotFoundException  | SQLException e ){
		e.printStackTrace();
	}
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
