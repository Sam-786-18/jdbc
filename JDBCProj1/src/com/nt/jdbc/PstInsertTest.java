package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PstInsertTest {
	private static final String INSERT_STUDENT_QUERY = "INSERT INTO STUDENT VALUES(?,?,?)";

	public static void main(String[] args) {
		Scanner sc = null;
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		int no = 0;
		String name = null;
		String addrs = null;
		int result = 0;
		try {
			// read inputs
			sc = new Scanner(System.in);
			if (sc != null) {
				System.out.println("Enter Student Count :");
				count = sc.nextInt(); // gives 3
			} // if

			// register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			// create PreparedStatement object
			if (con != null)
				ps = con.prepareStatement(INSERT_STUDENT_QUERY);

			// read multiple student details
			for (int i = 1; i <= count; ++i) {
				// read each student details
				System.out.println("Enter " + i + " student details");
				System.out.println("Enter student no::");
				no = sc.nextInt();
				System.out.println("Enter student name::");
				name = sc.next();
				System.out.println("Enter Student address::");
				addrs = sc.next();
				// set each student details to query params
				ps.setInt(1, no);
				ps.setString(2, name);
				ps.setString(3, addrs);
				// execute the Query
				result = ps.executeUpdate();
				// process the result
				if (result == 0)
					System.out.println("Record not inserted");
				else
					System.out.println("Record inserted");
			} // for
		} // try
		catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close jdbc objects
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}

			try {
				if (sc != null) {
					sc.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}//finally
	}// main
}// class
