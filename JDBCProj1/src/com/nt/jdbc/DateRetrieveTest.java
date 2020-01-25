package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class DateRetrieveTest {
	private static final String DATE_SELECT_QUERY = "SELECT * FROM PERSON_TAB";

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int id = 0;
		String name = null;
		java.sql.Date sqdob = null, sqdoj = null, sqdom = null;
		java.util.Date udob = null, udoj = null, udom = null;
		String sdob = null, sdoj = null, sdom = null;
		SimpleDateFormat sdf = null;
		boolean flag = false;
		try {
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
				ps = con.prepareStatement(DATE_SELECT_QUERY);
			// execute Query
			if (ps != null)
				rs = ps.executeQuery();
			// process the ResultSet
			if (rs != null) {
				while (rs.next()) {
					flag = true;
					id = rs.getInt(1);
					name = rs.getString(2);
					sqdob = rs.getDate(3);
					sqdoj = rs.getDate(4);
					sqdom = rs.getDate(5);
					// convert java.sql.Date class objects to java.util.Date
					// class objects
					udob = (java.util.Date) sqdob;
					udoj = (java.util.Date) sqdoj;
					udom = (java.util.Date) sqdom;
					// Convert java.util.Date class objects ot String date
					// values
					sdf = new SimpleDateFormat("MMM-dd-yyyy");
					sdob = sdf.format(udob);
					sdoj = sdf.format(udoj);
					sdom = sdf.format(udom);
					System.out.println(id + "  " + name + "  " + sdob + "  " + sdoj + " " + sdom);
				} // while
			} // if
			if (flag == false)
				System.out.println("Records not found");
		} // try
		catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
				if (con!= null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // finally
	}// main
}// class
