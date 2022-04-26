package model;
import java.sql.*;
public class Inquiry
{ //A common method to connect to the DB
private Connection connect()
{
Connection con = null;
try
{
Class.forName("com.mysql.cj.jdbc.Driver");
//Provide the correct details: DBServer/DBName, username, password
con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test1", "root", "");
}
catch (Exception e)
{e.printStackTrace();}
return con;
}
public String insertInquiry(String type, String header, String body, String date)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for inserting."; }
// create a prepared statement
String query = " insert into inquiries(`inquiryID`,`inquiryType`,`inquiryHeader`,`inquiryBody`,`inquiryDate`)"
+ " values (?, ?, ?, ?, ?)";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setInt(1, 1);
preparedStmt.setString(2, type);
preparedStmt.setString(3, header);
preparedStmt.setString(4, body);;
preparedStmt.setString(5, date);
// execute the statement
preparedStmt.execute();
con.close();
output = "Inserted successfully";
}
catch (Exception e)
{
output = "Error while inserting the inquiry.";
System.err.println(e.getMessage());
}
return output;
}
public String readInquiry()
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for reading."; }
// Prepare the html table to be displayed
output = "<table border='1'><tr><th>Inquiry Type</th><th>Inquiry Header</th>" +
"<th>Inquiry Body</th>" +
"<th>Inquiry Date</th>" +
"<th>Update</th><th>Remove</th></tr>";
String query = "select * from inquiries";
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
while (rs.next())
{
String inquiryID = Integer.toString(rs.getInt("inquiryID"));
String inquiryType = rs.getString("inquiryType");
String inquiryHeader = rs.getString("inquiryHeader");
String inquiryBody = rs.getString("inquiryBody");
String inquiryDate = rs.getString("inquiryDate");
// Add into the html table
output += "<tr><td>" + inquiryType + "</td>";
output += "<td>" + inquiryHeader + "</td>";
output += "<td>" + inquiryBody + "</td>";
output += "<td>" + inquiryDate + "</td>";
// buttons
output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
+ "<td><form method='post' action='inquiries.jsp'>"
+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
+ "<input name='inquiryID' type='hidden' value='" + inquiryID
+ "'>" + "</form></td></tr>";
}
con.close();
// Complete the html table
output += "</table>";
}
catch (Exception e)
{
output = "Error while reading the inquiries.";
System.err.println(e.getMessage());
}
return output;
}
public String updateInquiry(String ID, String type, String header, String body, String date)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for updating."; }
// create a prepared statement
String query = "UPDATE inquiries SET inquiryType=?,inquiryHeader=?,inquiryBody=?,inquiryDate=?WHERE inquiryID=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setString(1, type);
preparedStmt.setString(2, header);
preparedStmt.setString(3, body);
preparedStmt.setString(4, date);
preparedStmt.setInt(5, Integer.parseInt(ID));
// execute the statement
preparedStmt.execute();
con.close();
output = "Updated successfully";
}
catch (Exception e)
{
output = "Error while updating the inquiry.";
System.err.println(e.getMessage());
}
return output;
}
public String deleteInquiry(String inquiryID)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for deleting."; }
// create a prepared statement
String query = "delete from inquiries where inquiryID=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setInt(1, Integer.parseInt(inquiryID));
// execute the statement
preparedStmt.execute();
con.close();
output = "Deleted successfully";
}
catch (Exception e)
{
output = "Error while deleting the inquiry.";
System.err.println(e.getMessage());
}
return output;
}
}
