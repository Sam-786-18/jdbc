package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBMetaDataTest {

	public static void main(String[] args) {
	  Connection con=null;
	  DatabaseMetaData dbmd=null;
	  try{
		//register driver
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		//Establish the connection
		con=DriverManager.getConnection("jdbc:odbc:oradsn","system","manager");
		//create DatabaseMetaData object
		if(con!=null)
		  dbmd=con.getMetaData();
		System.out.println("DBMD object class::"+dbmd.getClass());
		System.out.println("-------------------------------------");
		System.out.println("DB s/w name:"+dbmd.getDatabaseProductName());
		System.out.println("DB s/w version:"+dbmd.getDatabaseProductVersion());
		System.out.println("jdbc driver name:"+dbmd.getDriverName());
		System.out.println("jdbc driver version:"+dbmd.getDriverVersion());
		//System.out.println("DB s/w major version:"+dbmd.getDatabaseMajorVersion());
		//System.out.println("DB s/w minjor version:"+dbmd.getDatabaseMinorVersion());
		System.out.println("SQL keywords::"+dbmd.getSQLKeywords());
		System.out.println("numberic functions::"+dbmd.getNumericFunctions());
		System.out.println("String functions::"+dbmd.getStringFunctions());
		System.out.println("system functions::"+dbmd.getSystemFunctions());
		System.out.println("Max connections::"+dbmd.getMaxConnections());
		System.out.println("Row size :"+dbmd.getMaxRowSize());
		System.out.println("supports PL/Sql procedures:"+dbmd.supportsStoredProcedures());
	  }//try
	  catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if (con!= null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // finally

	}//main
}//class
