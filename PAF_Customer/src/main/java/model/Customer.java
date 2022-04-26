package model;
import java.sql.*;
public class Customer
{ //A common method to connect to the DB
private Connection connect()
{
Connection con = null;
try
{
Class.forName("com.mysql.cj.jdbc.Driver");
//Provide the correct details: DBServer/DBName, username, password
con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test2", "root", "");
}
catch (Exception e)
{e.printStackTrace();}
return con;
}
public String insertCustomer(String name, String Acc_no, String email, String phone)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for inserting."; }
// create a prepared statement
String query = " insert into customers(`customerID`,`customerName`,`customerAcc_no`,`customerEmail`,`customerPhone`)"
+ " values (?, ?, ?, ?, ?)";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setInt(1, 1);
preparedStmt.setString(2, name);
preparedStmt.setString(3, Acc_no);
preparedStmt.setString(4, email);;
preparedStmt.setString(5, phone);
// execute the statement
preparedStmt.execute();
con.close();
output = "Inserted successfully";
}
catch (Exception e)
{
output = "Error while inserting the customer.";
System.err.println(e.getMessage());
}
return output;
}
public String readCustomer()
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for reading."; }
// Prepare the html table to be displayed
output = "<table border='1'><tr><th>Customer Name</th><th>Acc no</th>" +
"<th>Customer Email</th>" +
"<th>Customer Phone</th>" +
"<th>Update</th><th>Remove</th></tr>";
String query = "select * from customers";
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
while (rs.next())
{
String customerID = Integer.toString(rs.getInt("customerID"));
String customerName = rs.getString("customerName");
String customerAcc_no = rs.getString("customerAcc_no");
String customerEmail = rs.getString("customerEmail");
String customerPhone = rs.getString("customerPhone");
// Add into the html table
output += "<tr><td>" + customerName + "</td>";
output += "<td>" + customerAcc_no + "</td>";
output += "<td>" + customerEmail + "</td>";
output += "<td>" + customerPhone + "</td>";
// buttons
output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
+ "<td><form method='post' action='customers.jsp'>"
+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
+ "<input name='customerID' type='hidden' value='" + customerID
+ "'>" + "</form></td></tr>";
}
con.close();
// Complete the html table
output += "</table>";
}
catch (Exception e)
{
output = "Error while reading the customers.";
System.err.println(e.getMessage());
}
return output;
}
public String updateCustomer(String ID, String name, String customerAcc_no, String email, String phone)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for updating."; }
// create a prepared statement
String query = "UPDATE customers SET customerName=?,customerAcc_no=?,customerEmail=?,customerPhone=?WHERE customerID=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setString(1, name);
preparedStmt.setString(2, customerAcc_no);
preparedStmt.setString(3, email);
preparedStmt.setString(4, phone);
preparedStmt.setInt(5, Integer.parseInt(ID));
// execute the statement
preparedStmt.execute();
con.close();
output = "Updated successfully";
}
catch (Exception e)
{
output = "Error while updating the customer.";
System.err.println(e.getMessage());
}
return output;
}
public String deleteCustomer(String customerID)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for deleting."; }
// create a prepared statement
String query = "delete from customers where customerID=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setInt(1, Integer.parseInt(customerID));
// execute the statement
preparedStmt.execute();
con.close();
output = "Deleted successfully";
}
catch (Exception e)
{
output = "Error while deleting the customer.";
System.err.println(e.getMessage());
}
return output;
}
}

