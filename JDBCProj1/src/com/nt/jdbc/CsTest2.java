package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsTest2 {
	private static final String CALL_EMP_PROCEDURE = "{call GET_EMP_DETAILS_BY_NO(?,?,?,?)}";

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		CallableStatement cs = null;
		int no = 0;
		try {
			// read inputs
			sc = new Scanner(System.in);
			if (sc != null) {
				System.out.println("Enter employee no:");
				no = sc.nextInt();
			} // if
				// register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			// create CallableStatement object
			if (con != null)
				cs = con.prepareCall(CALL_EMP_PROCEDURE);
			// register OUT params with JDBC types
			if (cs != null) {
				cs.registerOutParameter(2, Types.VARCHAR);
				cs.registerOutParameter(3, Types.VARCHAR);
				cs.registerOutParameter(4, Types.INTEGER);
			} // if
				// set values to IN params
			if (cs != null) {
				cs.setInt(1, no);
			}
			// execute /call pl/sql procedure
			if (cs != null)
				cs.execute();
			// Gather results from OUT params
			if (cs != null) {
				System.out.println("Emp name:::" + cs.getString(2));
				System.out.println("Emp Job:::" + cs.getString(3));
				System.out.println("Emp Salary:::" + cs.getInt(4));
			} // if

		} // try
		catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		} catch (SQLException e) {
			System.out.println("No Data found");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close jdbc objs
			try {
				if (cs != null)
					cs.close();
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
			} catch (Exception se) {
				se.printStackTrace();
			}
		} // finally
	}// main
}// class
