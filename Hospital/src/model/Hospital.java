package model;

import java.sql.Connection;
import database.dbconnect;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Hospital {
    //A common method to connect to the DB
    dbconnect obj = new dbconnect();

    public String insertHospital(String hosName, String hosTelNo, String hosAddress, String hosEmail) {

        String output = "";
        try {
            Connection con = obj.connect();
            if (con == null) {
                return "Error while connecting to the database for inserting.";
            }
            // create a prepared statement
            String query = " insert into hospital(`hosName`,`hosTelNo`,`hosAddress`,`hosEmail`)" +
                " values (?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setString(1, hosName);
            preparedStmt.setString(2, hosTelNo);
            preparedStmt.setString(3, hosAddress);
            preparedStmt.setString(4, hosEmail);
            preparedStmt.execute();
            con.close();
            output = "Inserted successfully";
            System.out.println("Inserted successfully.......................................");
        } catch (Exception e) {
            output = "Error while inserting the Hospitals.";
            System.out.println("Error while inserting the Hospitals........." + e);
            System.err.println(e.getMessage());
        }
        return output;
    }
    //
    public String readHospital() {
        String output = "";
        try {
            Connection con = obj.connect();
            if (con == null) {
                return "Error while connecting to the database for reading.";
            }
            // Prepare the html table to be displayed
            output = "<table border=\"1\">" +
                "<th>Hospital Name</th" +
                "><th>Contatct No</th>" +
                "<th>Address</th>" +
                "<th>E-mail</th>" +
                "<th>Update</th>" +
                "<th>Remove</th></tr>";
            String query = "select * from hospital";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            // iterate through the rows in the result set
            while (rs.next()) {
                String hosID = Integer.toString(rs.getInt("hosID"));
                String hosName = rs.getString("hosName");
                String hosTelNo = rs.getString("hosTelNo");
                String hosAddress = rs.getString("hosAddress");
                String hosEmail = rs.getString("hosEmail");


                // Add into the html table
                output += "<tr><td><input id=\"hidHospitalIDUpdate\"name=\"hidHospitalIDUpdate\"type=\"hidden\" value=\"" + hosID + "\">" +
                    hosName + "</td>";
                output += "<td>" + hosTelNo + "</td>";
                output += "<td>" + hosAddress + "</td>";
                output += "<td>" + hosEmail + "</td>";

                // buttons
                output += "<td><input name=\"btnUpdate\" type=\"submit\"value=\"Update\" class=\"btn btn-warning btnUpdate\"></td>" +
                    "<td><form method=\"post\" action=\"Hospital_Insert.jsp\">" +
                    "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">" +
                    "<input name=\"hidHospitalIDDelete\" type=\"hidden\" value=\"" + hosID + "\">" + "</form></td></tr>";
                //1233
            }
            con.close();
            // Complete the html table
            output += "</table>";
        } catch (Exception e) {
            output = "Error while reading the Hospitals.";
            System.err.println(e.getMessage());
        }
        return output;
    }

    public String updateHopital(String ID, String hName, String contactNo, String address, String email) {
        System.out.println("Update method...............................................................................");
        String output = "";
        try {
            Connection con = obj.connect();
            if (con == null) {
                return "Error while connecting to the database for updating.";
            }
            //update
            // create a prepared statement
            String query = "UPDATE hospital SET hosName=?,hosTelNo=?,hosAddress=?,hosEmail=? WHERE hosID=?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            // binding values
            preparedStmt.setString(1, hName);
            preparedStmt.setString(2, contactNo);
            preparedStmt.setString(3, address);
            preparedStmt.setString(4, email);
            preparedStmt.setInt(5, Integer.parseInt(ID));
            // execute the statement
            preparedStmt.execute();
            con.close();
            output = "Updated successfully";
        } catch (Exception e) {
            output = "Error while updating the Hospital.";
            System.err.println(e.getMessage());
        }
        return output;
    }

    public String deleteHospital(String hosID) {
        String output = "";
        try {
            Connection con = obj.connect();
            if (con == null) {
                return "Error while connecting to the database for deleting.";
            }
            // create a prepared statement
            String query = "delete from hospital where hosID=?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            // binding values
            preparedStmt.setInt(1, Integer.parseInt(hosID));
            // execute the statement
            preparedStmt.execute();
            con.close();
            output = " Hospital Deleted successfully";
        } catch (Exception e) {
            output = "Error while deleting the Hospital.";
            System.err.println(e.getMessage());
        }
        return output;
    }


}