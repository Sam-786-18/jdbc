package com.nt.jdbc;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CLOBInsert {
  private static final String CLOB_INSERT="INSERT INTO STUDENTALL VALUES(?,?,?,?)";
	public static void main(String[] args) {
		//read inputs
		Scanner sc=null;
		int no=0;
		String sname=null,addrs=null,resumePath=null;
		Connection con=null;
		PreparedStatement ps=null;
		File file=null;
		long length=0;
		Reader reader=null;
		int result=0;
		try{
		 sc=new Scanner(System.in);
		 if(sc!=null){
		   System.out.println("Enter student no:::");
		   no=sc.nextInt();
		   System.out.println("Enter student name:::");
		   sname=sc.next();
		   System.out.println("Enter student address:::");
		   addrs=sc.next();
		   System.out.println("Enter student's resume path:::");
		   resumePath=sc.next();
		}//if
		 // Locate the File using java.io.File
		 file=new File(resumePath);
		 //get the length of the file
		 length=file.length();
		 //create FileReader
		 reader=new FileReader(file);
		 
		//register JDBC driver
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 //establish the connection
		 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		 //create PrpearedStatement object
		if(con!=null)
		 ps=con.prepareStatement(CLOB_INSERT);
		//set values to Query params
		if(ps!=null){
		  ps.setInt(1,no);
		  ps.setString(2, sname);
		  ps.setString(3,addrs);
		  ps.setCharacterStream(4, reader,length);
		}
		//execute the Query
		if(ps!=null){
			result=ps.executeUpdate();
		}
		//process the result
		if(result==0)
			System.out.println("Record not inserted");
		else
			System.out.println("Record inserted");
	}//try
    catch(ClassNotFoundException |SQLException |IOException e){
    	 e.printStackTrace();
    }
    catch(Exception e){
    	e.printStackTrace();
    }
	finally {
		// close jdbc objs
		try {
			if (ps != null)
				ps.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		try {
			if (con != null)
				con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		try {
			if (sc != null)
				sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (reader != null)
				reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // finally
  }//main
}//class
