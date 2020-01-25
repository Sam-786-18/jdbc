package com.nt.jdbc;

import java.awt.EventQueue;
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

public class ScrollFrameGUI {
   private static final String GET_STUDENTS="SELECT SNO,SNAME,SADD FROM STUDENT";
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScrollFrameGUI window = new ScrollFrameGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void makeConnection(){
		try{
		  //register driver
		  Class.forName("oracle.jdbc.driver.OracleDriver");
		  //establish the connection
		  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
          //create PreparedStatement object
		  ps=con.prepareStatement(GET_STUDENTS,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		  //create Scrollable ResultSet object
		  rs=ps.executeQuery();
		}//try
		catch(SQLException |ClassNotFoundException cnf){
			cnf.printStackTrace();
		}
	}//makeConnection()


	public ScrollFrameGUI() {
		initialize();
		makeConnection();
	}

	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		frame.addWindowListener(new MyAdapter());
		
		JLabel lblNewLabel = new JLabel("sno");
		lblNewLabel.setBounds(29, 23, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(85, 20, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("sname");
		lblNewLabel_1.setBounds(29, 69, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(85, 66, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("sadd");
		lblNewLabel_2.setBounds(29, 123, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(85, 120, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("First");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
                   //move the cursor				 
					rs.first();
				  //read values from ResultSet and set to text boxes
				   textField.setText(rs.getString(1));
				   textField_1.setText(rs.getString(2));
				   textField_2.setText(rs.getString(3));
				 
				}
				catch(SQLException se){
					se.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(10, 178, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("next");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 try{
				if(!rs.isLast()){
				 rs.next();
				}
				 //read values from ResultSet and set to text boxes
				   textField.setText(rs.getString(1));
				   textField_1.setText(rs.getString(2));
				   textField_2.setText(rs.getString(3));
				
			}//try
			 catch(SQLException se){
				 se.printStackTrace();
			 }
		 }
		}
		);
		btnNewButton_1.setBounds(104, 178, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("previous");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try{
						if(!rs.isFirst()){
						 rs.previous();
						}
						 //read values from ResultSet and set to text boxes
						   textField.setText(rs.getString(1));
						   textField_1.setText(rs.getString(2));
						   textField_2.setText(rs.getString(3));
						
					}//try
					 catch(SQLException se){
						 se.printStackTrace();
					 }
				
			}
		});
		btnNewButton_2.setBounds(10, 227, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnLast = new JButton("last");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try{
						
						 rs.last();
						 //read values from ResultSet and set to text boxes
						   textField.setText(rs.getString(1));
						   textField_1.setText(rs.getString(2));
						   textField_2.setText(rs.getString(3));
						
					}//try
					 catch(SQLException se){
						 se.printStackTrace();
					 }
				
			}
		});
		btnLast.setBounds(104, 227, 89, 23);
		frame.getContentPane().add(btnLast);
	}
	
	private class MyAdapter extends WindowAdapter{
		
		@Override
		public void windowClosing(WindowEvent e) {
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
		}
		
	}
}
