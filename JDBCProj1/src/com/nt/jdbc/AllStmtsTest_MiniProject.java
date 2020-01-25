/*create or replace procedure  FIND_PASS_FAIL(m1 in number,m2 in number,m3 in number,
                                                                                                                                                result out varchar) as
begin
   if(m1<35 or m2<35 or m3<35)then
       result:='fail';
   else
     result:='pass';
  end if;
end;
/
*/
package com.nt.jdbc;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AllStmtsTest_MiniProject extends JFrame implements ActionListener {
	private static final String SNO_QUERY="SELECT SNO FROM ALL_STUDENT";
	private static final String DETAILS_STUDENT_QUERY="SELECT * FROM ALL_STUDENT WHERE SNO=?";
	private static final String CALL_FIND_PASS_FAIL="{ call FIND_PASS_FAIL(?,?,?,?)}";
	
	private JLabel lno,lname,lm1,lm2,lm3,lresult;
	private JTextField tname,tm1,tm2,tm3,tresult;
	private JComboBox tno;
	private JButton bDetails,bResult;
	private Connection con;
	private Statement st;
	private PreparedStatement ps;
	private CallableStatement cs;
	private ResultSet rs1,rs2;
	
	public AllStmtsTest_MiniProject() {
		System.out.println("Constuctor..");
	   setTitle("MiniProject");
	   setSize(400,500);
	   setBackground(Color.GRAY);
	   setLayout(new FlowLayout());
	   //add components
	   lno=new JLabel("student number::");
	   add(lno);
	   
	   tno=new JComboBox();
	   add(tno);
	   
	   bDetails=new JButton("Details");
	   bDetails.addActionListener(this);
	   add(bDetails);
	   
	   lname=new JLabel("student name");
	   add(lname);
	   tname=new JTextField(10);
	   tname.setEditable(false);
	   add(tname);
	   
	   lm1=new JLabel("Marsk1 ");
	   add(lm1);
	   tm1=new JTextField(10);
	   tm1.setEditable(false);
	   add(tm1);
	   
	   lm2=new JLabel("Marsk2 ");
	   add(lm2);
	   tm2=new JTextField(10);
	   tm2.setEditable(false);
	   add(tm2);
	   
	   lm3=new JLabel("Marsk3 ");
	   add(lm3);
	   tm3=new JTextField(10);
	   tm3.setEditable(false);
	   add(tm3);
	   
	   bResult=new JButton("result");
	   bResult.addActionListener(this);
	   add(bResult);
	   
	   
	   tresult=new JTextField(10);
	   tresult.setEditable(false);
	   add(tresult);
	   setVisible(true);
	   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   myInit();
	   addWindowListener(new MyAdapter());
	}//constructor
	
	private  void myInit(){
		System.out.println("myInit()");
		try{
		 //register Driver
		  Class.forName("oracle.jdbc.driver.OracleDriver");
		  //establish the connection
		  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
          //create Statement object
		  st=con.createStatement();
		  //perform load on statup operation (put numbers into choice box)
		  rs1=st.executeQuery(SNO_QUERY);
		  while(rs1.next()){
			  tno.addItem(rs1.getInt(1));
		  }
		  //create PrperedStatement object
		  ps=con.prepareStatement(DETAILS_STUDENT_QUERY);
		  //create CallableStatement object
		  cs=con.prepareCall(CALL_FIND_PASS_FAIL);
		  cs.registerOutParameter(4,Types.VARCHAR);
		}//try
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf){
			cnf.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}//myInit

	public static void main(String[] args) {
		System.out.println("main(-)");
		new AllStmtsTest_MiniProject();
	}//main
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		int no=0;
		int m1=0,m2=0,m3=0;
	  System.out.println("actionPerformed(-)");
	  if(ae.getSource()==bDetails){
		  try{
		  System.out.println("Details Button");
		  //get select student no from choice box
		  no=(int) tno.getSelectedItem();
		  //set value to Query param
		  ps.setInt(1,no);
		  //execute the query
		  rs2=ps.executeQuery();
		  //set record values to text boxes
		  if(rs2.next()){
			  tname.setText(rs2.getString(2));
			  tm1.setText(rs2.getString(3));
			  tm2.setText(rs2.getString(4));
			  tm3.setText(rs2.getString(5));
		    }//if
		  }//try
		  catch(SQLException se){
			  se.printStackTrace();
		  }//catch
	  }//if
	  else{
		  System.out.println("result Button");
		  try{
           //read text box values
			m1=Integer.parseInt(tm1.getText());
			m2=Integer.parseInt(tm2.getText());
			m3=Integer.parseInt(tm3.getText());
		    //set values to IN params of procedure
			cs.setInt(1,m1);
			cs.setInt(2,m2);
			cs.setInt(3, m3);
			//call Procedure
			cs.execute();
			//gather reuslt from OUT param and set to Text Box
			tresult.setText(cs.getString(4));
		  }//try
		  catch(SQLException se){
			  se.printStackTrace();
		  }
		  catch(Exception e){
			  e.printStackTrace();
		  }
	  }//else
		
	}//actionPerfomed(-)
	private class MyAdapter extends WindowAdapter{
		
		@Override
		public void windowClosing(WindowEvent e) {
			try{
				if(rs2!=null)
					rs2.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			
			try{
				if(rs1!=null)
					rs1.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			
			try{
				if(cs!=null)
					cs.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			
			try{
				if(ps!=null)
					ps.close();
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
		}
		
	}
	
}//class
