package com;
import model.Customer;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Customers")
public class CustomerService
{
Customer itemObj = new Customer();
@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)
public String readCustomer()
{
return itemObj.readCustomer();
}
@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertCustomer(@FormParam("customerName") String customerName,
@FormParam("customerAcc_no") String customerAcc_no,
@FormParam("customerEmail") String customerEmail,
@FormParam("customerPhone") String customerPhone)
{
String output = itemObj.insertCustomer(customerName, customerAcc_no, customerEmail, customerPhone);
return output;
}
@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updateCustomer(String customerData)
{
//Convert the input string to a JSON object
JsonObject itemObject = new JsonParser().parse(customerData).getAsJsonObject();
//Read the values from the JSON object
String customerID = itemObject.get("customerID").getAsString();
String customerName = itemObject.get("customerName").getAsString();
String customerAcc_no = itemObject.get("customerAcc_no").getAsString();
String customerEmail = itemObject.get("customerEmail").getAsString();
String customerPhone = itemObject.get("customerPhone").getAsString();
String output = itemObj.updateCustomer(customerID, customerName, customerAcc_no, customerEmail, customerPhone);
return output;
}
@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deleteCustomer(String customerData)
{
//Convert the input string to an XML document
Document doc = Jsoup.parse(customerData, "", Parser.xmlParser());
//Read the value from the element <itemID>
String customerID = doc.select("customerID").text();
String output = itemObj.deleteCustomer(customerID);
return output;
}
}
