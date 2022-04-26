package model;
import java.sql.*;
public class Calculate
{ //A common method to connect to the DB
private Connection connect()
{
Connection con = null;
try
{
Class.forName("com.mysql.cj.jdbc.Driver");
//Provide the correct details: DBServer/DBName, username, password
con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test3", "root", "");
}
catch (Exception e)
{e.printStackTrace();}
return con;
}
public String insertCalculate(String month, String unit, String unit_cost, String total)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for inserting."; }
// create a prepared statement
String query = " insert into calculates(`billID`,`calculationMonth`,`calculationUnit`,`calculationUnit_cost`,`calculationTotal`)"
+ " values (?, ?, ?, ?, ?)";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setInt(1, 1);
preparedStmt.setString(2, month);
preparedStmt.setString(3, unit);
preparedStmt.setString(4, unit_cost);;
preparedStmt.setString(5, total);
// execute the statement
preparedStmt.execute();
con.close();
output = "Inserted successfully";
}
catch (Exception e)
{
output = "Error while inserting the calculate.";
System.err.println(e.getMessage());
}
return output;
}
public String readCalculate()
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for reading."; }
// Prepare the html table to be displayed
output = "<table border='1'><tr><th>Month</th><th>Unit</th>" +
"<th>Unit Cost</th>" +
"<th>Total</th>" +
"<th>Update</th><th>Remove</th></tr>";
String query = "select * from calculates";
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
while (rs.next())
{
String billID = Integer.toString(rs.getInt("billID"));
String calculationMonth = rs.getString("calculationMonth");
String calculationUnit = rs.getString("calculationUnit");
String calculationUnit_cost = rs.getString("calculationUnit_cost");
String calculationTotal = rs.getString("calculationTotal");
// Add into the html table
output += "<tr><td>" + calculationMonth + "</td>";
output += "<td>" + calculationUnit + "</td>";
output += "<td>" + calculationUnit_cost + "</td>";
output += "<td>" + calculationTotal + "</td>";
// buttons
output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
+ "<td><form method='post' action='inquiries.jsp'>"
+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
+ "<input name='billID' type='hidden' value='" + billID
+ "'>" + "</form></td></tr>";
}
con.close();
// Complete the html table
output += "</table>";
}
catch (Exception e)
{
output = "Error while reading the calculations.";
System.err.println(e.getMessage());
}
return output;
}
public String updateCalculate(String ID, String month, String unit, String unit_cost, String total)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for updating."; }
// create a prepared statement
String query = "UPDATE calculations SET calculationMonth=?,calculationUnit=?,calculationUnit_cost=?,calculationTotal=?WHERE billID=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setString(1, month);
preparedStmt.setString(2, unit);
preparedStmt.setString(3, unit_cost);
preparedStmt.setString(4, total);
preparedStmt.setInt(5, Integer.parseInt(ID));
// execute the statement
preparedStmt.execute();
con.close();
output = "Updated successfully";
}
catch (Exception e)
{
output = "Error while updating the calculate.";
System.err.println(e.getMessage());
}
return output;
}
public String deleteCalculate(String billID)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for deleting."; }
// create a prepared statement
String query = "delete from calculations where billID=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setInt(1, Integer.parseInt(billID));
// execute the statement
preparedStmt.execute();
con.close();
output = "Deleted successfully";
}
catch (Exception e)
{
output = "Error while deleting the calculate.";
System.err.println(e.getMessage());
}
return output;
}
}

