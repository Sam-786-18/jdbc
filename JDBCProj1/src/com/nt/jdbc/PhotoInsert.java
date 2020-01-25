package com.nt.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/*SQL> desc empall;
Name                                      Null?    Type
----------------------------------------- -------- ----------------------------
ENO                                       NOT NULL NUMBER(5)
ENAME                                              VARCHAR2(20)
ESALARY                                            NUMBER(9,2)
EPHOTO                                             BLOB
*/
public class PhotoInsert {
	private static final String EMP_INSERT_QUERY = "INSERT INTO EMPALL VALUES(?,?,?,?)";

	public static void main(String[] args) {
		Scanner sc = null;
		int no = 0;
		String name = null;
		int salary = 0;
		String photoPath = null;
		Connection con = null;
		PreparedStatement ps = null;
		File file = null;
		InputStream is = null;
		int result = 0;
		try {
			sc = new Scanner(System.in);
			if (sc != null) {
				System.out.println("Enter emp no:");
				no = sc.nextInt();
				System.out.println("Enter emp name:");
				name = sc.next();
				System.out.println("Enter emp salary:");
				salary = sc.nextInt();
				System.out.println("Enter photo Path:");
				photoPath = sc.next();
			} // if

			// register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			
/*			//register jdbc driver
			Class.forName("com.mysql.jdbc.Driver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:mysql:///ntaj48db1","root","root");
*/			
			// create PreparedStatement object
			if (con != null)
				ps = con.prepareStatement(EMP_INSERT_QUERY);
			// create Output Stream pointing to file
			file = new File(photoPath);
			is = new FileInputStream(file);
			// set values to query params
			if (ps != null) {
				ps.setInt(1, no);
				ps.setString(2, name);
				ps.setInt(3, salary);
				ps.setBinaryStream(4, is, file.length());
			} // if
			// execute the Query
			if (ps != null)
				result = ps.executeUpdate();
			// procss the results
			if (result == 0)
				System.out.println("Record not inserte");
			else
				System.out.println("Record inserted");
		} // try
		catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
				if (is != null)
					is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				if (sc != null)
					sc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // finally
	}// main
}// class
