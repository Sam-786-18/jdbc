package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/*SQL> create table person_tab(pid number(5) primary key,
  pname varchar2(20),DOB date,DOJ date,DOM date);
 */

import java.util.Scanner;

public class DateInsertTest {
	private static final String DATE_INSERT_QUERY = "INSERT INTO PERSON_TAB VALUES(?,?,?,?,?)";

	public static void main(String[] args) {
		Scanner sc = null;
		int id = 0;
		String name = null, dob = null, doj = null, dom = null;
		SimpleDateFormat sdf1 = null, sdf2 = null;
		java.util.Date udob = null, udoj = null;
		java.sql.Date sqdob = null, sqdoj = null, sqdom = null;
		long ms = 0;
		Connection con = null;
		PreparedStatement ps = null;
		int count = 0;
		try {
			// read inputs
			sc = new Scanner(System.in);
			if (sc != null) {
				System.out.println("Enter Person Id:");
				id = sc.nextInt();

				System.out.println("Enter Person Name:");
				name = sc.next();

				System.out.println("Enter DOB(dd-MM-yyyy)");
				dob = sc.next();

				System.out.println("Enter DOJ(MM-dd-yyyy)");
				doj = sc.next();

				System.out.println("Enter DOM(yyyy-MM-dd)");
				dom = sc.next();
			} // if
				// Convert String date values to java.sql.Date class object
				// for DOB
			sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			udob = sdf1.parse(dob); // gives java.util.Date class object

			ms = udob.getTime();
			sqdob = new java.sql.Date(ms); // gives java.sql.Date class object
			// for DOJ
			sdf2 = new SimpleDateFormat("MM-dd-yyyy");
			udoj = sdf2.parse(doj); // gives java.util.Date class object

			ms = udoj.getTime();
			sqdoj = new java.sql.Date(ms); // gives java.sql.Date class object
			// for DOM
			sqdom = java.sql.Date.valueOf(dom);

		/*	// register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");*/
			
			//register jdbc driver
			Class.forName("com.mysql.jdbc.Driver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:mysql:///ntaj48db1", "root","root");
             			
			// create PreparedStatement object
			if (con != null)
				ps = con.prepareStatement(DATE_INSERT_QUERY);
			// set values to Query params
			if (ps != null) {
				ps.setInt(1, id);
				ps.setString(2, name);
				ps.setDate(3, sqdob);
				ps.setDate(4, sqdoj);
				ps.setDate(5, sqdom);
			}
			// execute the Query
			if (ps != null) {
				count = ps.executeUpdate();
			}
			// process the result
			if (count == 0)
				System.out.println("Record not inserted ");
			else
				System.out.println("Record inserted");
		} // try
		catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
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
				if (sc != null)
					sc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // finally

	}// main
}// class
