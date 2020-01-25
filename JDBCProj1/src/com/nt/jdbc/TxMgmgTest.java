/*SQL> select * from tx_account;

     ACCNO HOLDER                  BALANCE
---------- -------------------- ----------
      1001 raja                       4050
      1002 ravi                      11600
*/
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TxMgmgTest {

	public static void main(String[] args) {
		Scanner sc=null;
		int srcNo=0;
		int destNo=0;
		int amount=0;
		Connection con=null;
		Statement st=null;
		int result[]=null;
		boolean flag=false;
		
		try{
		 //read inputs
		 sc=new Scanner(System.in);
		 if(sc!=null){
		   System.out.println("Enter Source Account number:");
		   srcNo=sc.nextInt();
		   System.out.println("Enter Destination Account number:");
		   destNo=sc.nextInt();
		   System.out.println("Enter amount to transfer::");
		   amount=sc.nextInt();
		 }//if
	
		// register JDBC driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// establish the connection
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
		 //Begin Transaction
		if(con!=null)
			con.setAutoCommit(false);
        //create Statement object
	    if(con!=null)
	     st=con.createStatement();
	    //add withdraw,deposite queries to batch
	    if(st!=null){
	     //withdraw operation
	     st.addBatch("update tx_account set balance=balance-"+amount+" where accno="+srcNo);
	     //deposite operation
	     st.addBatch("update tx_account set balance=balance+"+amount+" where accno="+destNo);	
	    }//if
	    //executebatch
	    if(st!=null){
	    	result=st.executeBatch();
	    }
	    
	    //perform Transaction management
	    if(result!=null){
	      for(int i=0;i<result.length;++i){
	    	  if(result[i]==0){
                  flag=true;
                  break;
	    	  }
	      }//for
	    }//if
	    
	    if(flag==true){
	    	con.rollback();
	    	System.out.println("Transaction is rolledback(Money not transfered)");
	    }
	    else{
	    	con.commit();
	    	System.out.println("Transaction is committed(Money Transfered)");
	    }
			
		}//try
		catch(SQLException  se){
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf){
			cnf.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
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
			} catch (Exception se) {
				se.printStackTrace();
			}
		} // finally
	}//main
}//class
