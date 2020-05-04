package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import database.dbconnect;

public class LoginSelect {
	
	dbconnect obj = new dbconnect();
	
	public int loginVal(String un, String pass) {
		
		try {
			Statement statement = null;
			Connection con = obj.connect();
				if (con == null) {
					System.out.println("error connecting database.......");
				}
				
				String query = "select Type from admin where Username='"+un+"' and Password='"+pass+"' ";
				statement = con.createStatement();
				ResultSet rs = statement.executeQuery(query);
				if (rs.next()) {
					System.out.println(rs.getString("Type"));
					if(rs.getString("Type").equals("1")) {
						return 1;
					}else if(rs.getString("Type").equals("2")) {
						return 2;
					}else if(rs.getString("Type").equals("3")) {
						return 3;	
					}else if(rs.getString("Type").equals("4")) {
						return 4;	
					}else if(rs.getString("Type").equals("5")) {
						return 5;	
					}
				}
							
		} catch (Exception e) {
				System.out.println("Error while logging........."+ e);
			}	
		
		return 0;
	}
}