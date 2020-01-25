package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ParameterMetaDataApp {

	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		ParameterMetaData pmd=null;
		int count=0;
		try{
			//register driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
			//create PreparedStatement obj
			if(con!=null)
			  ps=con.prepareStatement("INSERT INTO STUDENT VALUES(?,?,?)");
			//get ParameterMeatData
			if(ps!=null)
			  pmd=ps.getParameterMetaData();
			//get param count
			count=pmd.getParameterCount();
			//display detaisl about parameters
			for(int i=1;i<=count;++i){
				System.out.println("parameter number"+i);
				System.out.println("parameter mode"+pmd.getParameterMode(i));
				System.out.println("parameter type"+pmd.getParameterTypeName(i));
				System.out.println("parameter scale"+pmd.getScale(i));
				System.out.println("parmaeter precision"+pmd.getPrecision(i));
			}//for
		}//try
		catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
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
	
		} // finally
	}//main
}//class
