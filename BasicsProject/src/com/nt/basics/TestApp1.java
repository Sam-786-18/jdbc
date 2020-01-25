package com.nt.basics;

import java.text.SimpleDateFormat;

public class TestApp1 {

	public static void main(String[] args)throws Exception {
		
		//Converting String date value to java.util.Date class object
		String s1="25-12-1980"; //dd-MM-yyyy
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date ud=sdf.parse(s1);
		System.out.println("util date"+ud);
		//Converting java.util.Date class obj to java.sql.Date class object
		long ms=ud.getTime();
		java.sql.Date sqd=new java.sql.Date(ms);
		System.out.println("sql date"+sqd);
		
		//Converting string data value whose pattern is
		// yyyy-MM-dd directly to java.sql.Date class object.
		String s2="1901-10-23"; //yyyy-MM-dd
		java.sql.Date sqd1=java.sql.Date.valueOf(s2);
		System.out.println("sql date::"+sqd1);
		
		//Converting java.sql.Date class object to java.util.Date class object
		java.util.Date ud1=(java.util.Date)sqd1;
		System.out.println("util date"+ud1);
		
		//Converting java.util.Date class object to String date value
		SimpleDateFormat sdf1=new SimpleDateFormat("MMM-dd-yyyy");
		String s3=sdf1.format(ud1);
		System.out.println("String Date"+s3);
		
		
		
		
		
		
	}//main
}//class
