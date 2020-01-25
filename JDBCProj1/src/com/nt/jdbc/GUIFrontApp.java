package com.nt.jdbc;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUIFrontApp extends JFrame implements ActionListener{
 private static final String INSERT_STUDENT_QUERY="INSERT INTO STUDENT VALUES(?,?,?)";
  private JLabel lno,lname,ladd,lres;
  private JTextField tno,tname,tadd;
  private JButton bInsert;
  private Connection con;
  private PreparedStatement ps; 
  
  
	public GUIFrontApp(){
		System.out.println("GUIFrontApp:0-param constructor");
		//super class methods can be invoked in sub class directly
		setTitle("GUI FrontEndApp"); 
		setBackground(Color.GRAY); 
		setLayout(new FlowLayout());
		setSize(200,200);
		//add comps
		lno=new JLabel("Student number::");
		add(lno);
		tno=new JTextField(10);
		add(tno);
		lname=new JLabel("Student name::");
		add(lname);
		tname=new JTextField(10);
		add(tname);
		ladd=new JLabel("Student Adress::");
		add(ladd);
		tadd=new JTextField(10);
		add(tadd);
		bInsert=new JButton("insert");
		add(bInsert);
		//register ActionEvent Listener on Button component.
		bInsert.addActionListener(this);
		lres=new JLabel();
		lres.setForeground(Color.RED);
		add(lres);
		//registr WindowListener for the frame
		addWindowListener(new MyAdapter());
		
		//Close frame window when (x) button is clicked
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//set Visible
		setVisible(true);
		makeConnection();
	}//constructor
	

	private void makeConnection(){
		System.out.println("makeConnection() method");
		try{
		  //create jdbc connection
		  Class.forName("oracle.jdbc.driver.OracleDriver");
		  //establish the connection
		  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
		 //create PreparedStatement object
		ps=con.prepareStatement(INSERT_STUDENT_QUERY);
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
	}//makeConnection()
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("GUIFrontApp:actionPerformed(-)");
		int no=0;
		String name=null,addrs=null;
		int result=0;
		try{
		 //read form data/text boxes data
		 no=Integer.parseInt(tno.getText());
		 name=tname.getText();
		 addrs=tadd.getText();
		 //set values to Query params
		 ps.setInt(1,no);
		 ps.setString(2,name);
		 ps.setString(3,addrs);
		 //execute the Query
		 result=ps.executeUpdate();
		 if(result==0)
			 lres.setText("student registration failed");
		 else
			 lres.setText("Student Registration done");
		}//try
		catch(SQLException se){
			 lres.setText("student registration failed");
			se.printStackTrace();
		}
	}//actionPerformed(-)
	
	public static void main(String[] args) {
		System.out.println("main(-)");
		new GUIFrontApp();
	}//method
	
	private  class MyAdapter extends WindowAdapter{
		@Override
		public void windowClosing(WindowEvent e) {
			System.out.println("windowClosisng(-)");
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
		}
	}

}//class
