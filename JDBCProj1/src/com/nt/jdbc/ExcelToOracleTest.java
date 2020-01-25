package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExcelToOracleTest {

	public static void main(String[] args) {
		Connection xlsCon=null,oraCon=null;
		Statement st=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int no=0;
		String name=null,addrs=null;
		try{
		 //register drivers
		  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		  Class.forName("oracle.jdbc.driver.OracleDriver");
		  //establish the connections
		  xlsCon=DriverManager.getConnection("jdbc:odbc:xlsdsn");
		  oraCon=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
          //create Statement objects
		  if(xlsCon!=null)
			 st=xlsCon.createStatement();
		  if(oraCon!=null)
			  ps=oraCon.prepareStatement("insert into student values(?,?,?)");
		  //get Excelsheet records
		  if(st!=null)
			  rs=st.executeQuery("select * from [Sheet1$]");
		  //copy excel sheet records to Oracle Db table
		  if(rs!=null &&ps!=null){
			  while(rs.next()){
				//get each record from excel sheet
			    no=rs.getInt(1);
				name=rs.getString(2);
				addrs=rs.getString(3);
				//set each record values to oracle Db table
				ps.setInt(1,no);
				ps.setString(2,name);
				ps.setString(3,addrs);
				//execute the Query
				ps.executeUpdate();
			  }//while
			  System.out.println("records are copied.....");
		   }//if
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
				if(rs!=null)
					rs.close();
				}
				catch(SQLException se){
					se.printStackTrace();
			}
			try{
				if(ps!=null)
					ps.close();
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
				if(oraCon!=null)
					oraCon.close();
				}
				catch(SQLException se){
					se.printStackTrace();
				}
			try{
				if(xlsCon!=null)
					xlsCon.close();
				}
				catch(SQLException se){
					se.printStackTrace();
				}
		}//finally	

	}

}
