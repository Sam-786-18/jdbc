package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest {

	public static void main(String[] args) {
		int no = 0;
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		String query = null;
		int count = 0;

		try {
			// read inputs
			sc = new Scanner(System.in);
			if (sc != null) {
				System.out.println("Enter student no:");
				no = sc.nextInt();
			} // if

			// register jdbc driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			// establish the connection
			con = DriverManager.getConnection("jdbc:odbc:oradsn", "system", "manager");
			// create sTatement object
			if (con != null)
				st = con.createStatement();
			// prepare Query
			// delete from student where sno=102;
			query = "delete from student where sno=" + no;
			// send and execute SQL Query in DB s/w
			if (st != null)
				count = st.executeUpdate(query);
			// process the Result
			if (count != 0)
				System.out.println(count + " no.of records deleted");
			else
				System.out.println("Record not found to delete");
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
				if (st != null)
					st.close();
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
