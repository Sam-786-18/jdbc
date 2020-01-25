package com.nt.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropsTest {

	public static void main(String[] args) {
		InputStream is=null;
		Properties props=null;
		try{
		 //locate proeprties file
		 is=new FileInputStream("src/com/nt/commons/myfile.properties");
		 //load properties file content to java.util.Properties class object
		 props=new Properties();
		 props.load(is);
		 // properties file data
		 System.out.println(props);
		 System.out.println("name key value:::"+(String)props.get("name"));
		 System.out.println("age key value:::"+props.getProperty("age"));
		}//try
		catch(IOException ioe){
			ioe.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}//main
}//class
