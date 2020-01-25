package com.nt.jdbc;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PhotoRetrieve {
	private static final String GET_EMP_BY_NO = "SELECT ENO,ENAME,ESALARY,EPHOTO FROM EMPALL WHERE ENO=?";

	public static void main(String[] args) {
		Scanner sc = null;
		int no = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String name = null;
		int salary = 0;
		InputStream is = null;
		OutputStream os = null;
		byte[] buffer = null;
		int bytesRead = 0;

		try {
			// read inputs
			sc = new Scanner(System.in);
			if (sc != null) {
				System.out.println("Enter emp number");
				no = sc.nextInt();
			}
		/*	// register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");*/
			
			
			// register jdbc driver
			Class.forName("com.mysql.jdbc.Driver");
			// establish the connection
			con = DriverManager.getConnection("jdbc:mysql:///ntaj48db1", "root", "root");

			
			

			// create PreparedStatement object
			if (con != null)
				ps = con.prepareStatement(GET_EMP_BY_NO);
			// set parameter vlaue
			if (ps != null)
				ps.setInt(1, no);
			// execute the Query
			if (ps != null)
				rs = ps.executeQuery();
			if (rs != null) {
				if (rs.next()) {
					no = rs.getInt(1);
					name = rs.getString(2);
					salary = rs.getInt(3);
					is = rs.getBinaryStream(4);
				} // if
			} // if
				// create outputStream pointing to empty Dest file
			os = new FileOutputStream("d:\\new_alia.jpg");
			// write buffer based logic to place retrived BLOB value to Dest
			// file
			if (is != null && os != null) {
				buffer = new byte[4096];
				bytesRead = 0;
				while ((bytesRead = is.read(buffer)) != -1) {
					os.write(buffer, 0, bytesRead);
				} // while
			} // if
			System.out.println(no+"  "+name+" "+salary);
			System.out.println("Photo retrieved");
		} // try
		catch (SQLException | IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
				if (is != null)
					is.close();
			} catch (IOException se) {
				se.printStackTrace();
			}
			try {
				if (os != null)
					os.close();
			} catch (IOException se) {
				se.printStackTrace();
			}
			
			try {
				if (sc != null)
					sc.close();
			} catch (Exception se) {
				se.printStackTrace();
			}
			
		} // finally

	}//main
}//class
