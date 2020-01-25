package com.nt.jdbc;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ScrollFrameTest extends JFrame implements ActionListener {
  private static final String GET_STUDENTS="SELECT SNO,SNAME,SADD FROM STUDENT";
  private JButton bFirst,bLast,bNext,bPrevious;
  private JLabel lsno,lsname,lsadd;
  private JTextField tsno,tsname,tsadd;
  private Connection con;
  private PreparedStatement ps;
  private ResultSet rs;
  
  
  public ScrollFrameTest() {
	  //general settings
	  setTitle("ScrollFrame App");
	  setSize(300,300);
	  setBackground(Color.GRAY);
	  setLayout(new FlowLayout());
	  //add comps
	  lsno=new JLabel("student number");
	  add(lsno);
	  tsno=new JTextField(10);
	  add(tsno);
	  
	  lsname=new JLabel("student name:");
	  add(lsname);
	  tsname=new JTextField(10);
	  add(tsname);
	  
	  lsadd=new JLabel("student address:");
	  add(lsadd);
	  tsadd=new JTextField(10);
	  add(tsadd);
	  
	  bFirst=new JButton("first");
	  bFirst.addActionListener(this);
	  add(bFirst);
	  
	  bNext=new JButton("next");
	  bNext.addActionListener(this);
	  add(bNext);
	  
	  bPrevious=new JButton("previous");
	  bPrevious.addActionListener(this);
	  add(bPrevious);

	  bLast=new JButton("Last");
	  bLast.addActionListener(this);
	  add(bLast);
	  
	  setVisible(true);
	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  
	  makeConnection();
	  addWindowListener(new MyAdapter());
	
   }
  
     private void makeConnection(){
    	 try{
    	  //register jdbc driver
    		Class.forName("oracle.jdbc.driver.OracleDriver");
    	  //establish the connection
    		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
    	  //create PreparedStatement object
    		ps=con.prepareStatement(GET_STUDENTS,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
    	  //create ScrollbleResultSet object
    		rs=ps.executeQuery();
    	 }//try
    	 catch(SQLException |ClassNotFoundException e){
    		 e.printStackTrace();
    	 }
     }//makeConnection()
  
  
	public static void main(String[] args) {
		System.out.println("main(-) method");
	   new ScrollFrameTest();

	}

	//for Event handling
	@Override
	public void actionPerformed(ActionEvent ae) {
	   boolean flag=false;
		System.out.println("actionPerformed");
	   try{
	   
	   if(ae.getSource()==bFirst){
		   System.out.println("First Button");
		   rs.first();
		   flag=true;
	   }
	   else if(ae.getSource()==bLast){
		   System.out.println("Last button");
		   rs.last();
		   flag=true;
	   }
	   else if(ae.getSource()==bNext){
		   System.out.println("next button");
		   if(!rs.isLast()){
			   rs.next();
			   flag=true;
		   }
	   }
	   else if(ae.getSource()==bPrevious){
		   System.out.println("previous button");
		   if(!rs.isFirst()){
			   rs.previous();
			   flag=true;
		   }
	   }//else
	   
	   //read the record values from ResultSet and set them text boxes
	   if(flag==true){ // if(flag)
		   tsno.setText(rs.getString(1));
		   tsname.setText(rs.getString(2));
		   tsadd.setText(rs.getString(3));
	   }//if
	  }//try
	  catch(SQLException e){
		  e.printStackTrace();
	  }
	}//actionPerformed(-)
	private  class MyAdapter extends WindowAdapter{
		@Override
		public void windowClosing(WindowEvent e) {
			System.out.println("window Closing(...)");
			//close jdbc objs
			try{
			 if(rs!=null)
				rs.close();
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
				 if(con!=null)
					con.close();
				}
			catch(SQLException se){
				se.printStackTrace();
			}
		}//windowClosing(-)
	}//MyAdadpter
}//class
