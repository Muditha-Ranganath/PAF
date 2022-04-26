package com;

import model.Calculate;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Calculates")
public class CalculateService
{
Calculate itemObj = new Calculate();
@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)
public String readCalculate()
{
return itemObj.readCalculate();
}
@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertCalculate(@FormParam("calculationMonth") String calculationMonth,
@FormParam("calculationUnit") String calculationUnit,
@FormParam("calculationUnit_cost") String calculationUnit_cost,
@FormParam("calculationTotal") String calculationTotal)
{
String output = itemObj.insertCalculate(calculationMonth, calculationUnit, calculationUnit_cost, calculationTotal);
return output;
}
@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updateInquiry(String inquiryData)
{
//Convert the input string to a JSON object
JsonObject itemObject = new JsonParser().parse(inquiryData).getAsJsonObject();
//Read the values from the JSON object
String billID = itemObject.get("billID").getAsString();
String calculationMonth = itemObject.get("calculationMonth").getAsString();
String calculationUnit = itemObject.get("calculationUnit").getAsString();
String calculationUnit_cost = itemObject.get("calculationUnit_cost").getAsString();
String calculationTotal = itemObject.get("calculationTotal").getAsString();
String output = itemObj.updateCalculate(billID, calculationMonth, calculationUnit, calculationUnit_cost, calculationTotal);
return output;
}
@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deleteInquiry(String calculateData)
{
//Convert the input string to an XML document
Document doc = Jsoup.parse(calculateData, "", Parser.xmlParser());
//Read the value from the element <itemID>
String billID = doc.select("billID").text();
String output = itemObj.deleteCalculate(billID);
return output;
}
}
