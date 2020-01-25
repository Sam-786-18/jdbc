package com.nt.jdbc;

import java.io.FileWriter;
import java.io.StringWriter;
import java.sql.SQLException;

import javax.sql.rowset.WebRowSet;

import oracle.jdbc.rowset.OracleWebRowSet;

public class WebRowSetTest {

	public static void main(String[] args) {
		WebRowSet wrowset=null;
		try{
		  wrowset=new OracleWebRowSet();
		  //set urls
		  wrowset.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		  wrowset.setUsername("system");
		  wrowset.setPassword("manager");
		  wrowset.setCommand("select * from student");
		  //execute RowSet
		  wrowset.execute();
		  //Write the DB table records to xml file
		  wrowset.writeXml(new FileWriter("d:/student.xml"));
		  System.out.println("check d:/student.xml file");
		  
		  StringWriter sw=new StringWriter();
		  wrowset.writeXml(sw);
		  System.out.println(sw);
		  
		  
		}//try
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}//main
}//class
