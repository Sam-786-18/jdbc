package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ScrollableTest {
  private static final String GET_STUDENT="SELECT SNO,SNAME,SADD FROM STUDENT";
	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
        ResultSet rs=null;
        PreparedStatement ps=null;
		try{
			//register jdbc driver
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 //establish the connection
		 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
		 //create Statement object
		 if(con!=null)
		   //st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			//st=con.createStatement(1005, 1008);
			ps=con.prepareStatement(GET_STUDENT, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		 
/*		 //send and execute SQL Query in DB s/w
		 if(st!=null)
			 rs=st.executeQuery(GET_STUDENT);
*/
		 if(ps!=null)
			 rs=ps.executeQuery();
		 
		 if(rs!=null){
		 //process the ResultSet
		 System.out.println("Top----->Bottom");
		
		 while(rs.next()){
			 System.out.println(rs.getInt(1)+"  "+rs.getString(2)+" "+rs.getString(3));
		 }
		 System.out.println("Bottom---->Top");
		 rs.afterLast();
		 while(rs.previous()){
			 System.out.println(rs.getInt(1)+"  "+rs.getString(2)+" "+rs.getString(3));
		 }
		 
		 System.out.println("---------------------");
		 rs.first();
		 System.out.println("first :"+rs.getRow()+"-->"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
		 rs.last();
		 System.out.println("last:"+rs.getRow()+"---->"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
		 rs.relative(-3);
		 System.out.println(rs.getRow()+"---->"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
		 rs.relative(-10);
		 System.out.println(rs.getRow()+"---->"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
		 rs.relative(3);
		 System.out.println(rs.getRow()+"---->"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
		 rs.absolute(3);
		 System.out.println(rs.getRow()+"---->"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
		 rs.absolute(12);
		 System.out.println(rs.getRow()+"---->"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
		 rs.absolute(-3);
		 System.out.println(rs.getRow()+"---->"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
		 }//if
		 
		}//try
		catch(SQLException |ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			//close jdbc objs
			try{
			if(rs!=null)
				rs.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			
			try{
				if(st!=null)
					st.close();
				}
				catch(SQLException se){
					se.printStackTrace();
			}
			try{
				if(con!=null)
					con.close();
				}
				catch(SQLException se){
					se.printStackTrace();
				}
		}//finally
	}//main
}//class
