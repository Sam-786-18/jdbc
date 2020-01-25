package com.nt.jdbc;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CLOBRetrieve {
	private static final String CLOB_RETRIEVE="SELECT STNO,STNAME,STADD,STRESUME FROM STUDENTALL WHERE STNO=?"; 
	public static void main(String[] args) {

		Scanner sc=null;
		int no=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Reader reader=null;
		String name=null,addrs=null;
		Writer writer=null;
		char[] buffer=null;
		int charsRead=0;
		try{
		 sc=new Scanner(System.in);
		 if(sc!=null){
			System.out.println("Enter student no::"); 
			no=sc.nextInt();
		 }
		 
		//register JDBC driver
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 //establish the connection
		 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		 //create PrpearedStatement object
		if(con!=null)
		 ps=con.prepareStatement(CLOB_RETRIEVE);
		//set values to query params
		if(ps!=null){
			ps.setInt(1,no);
		}
		if(ps!=null){
		  rs=ps.executeQuery();
		}
		//process the ResultSet
		if(rs!=null){
			if(rs.next()){
			  no=rs.getInt(1);	
			  name=rs.getString(2);
			  addrs=rs.getString(3);
			  reader=rs.getCharacterStream(4);
			}//if
		}//if
		//create Dest file
		writer=new FileWriter("d://new_resume.txt");
		//write buffer based logic to read CLOB value through
		// reader and to write to Dest file using Writer.
		buffer=new char[1024];
		charsRead=0;
		while((charsRead=reader.read(buffer))!=-1){
			writer.write(buffer,0,charsRead);
		}//while
		System.out.println("CLOB Value is retrieved");
		System.out.println(no+"  "+name+" "+addrs);
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
				if (rs != null)
					rs.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
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
			try {
				if (writer != null)
					writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // finally
	}//main
}//class
