package com;

import model.Inquiry;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Inquiries")
public class InquiryService
{
Inquiry itemObj = new Inquiry();
@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)
public String readItems()
{
return itemObj.readInquiry();
}
@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertItem(@FormParam("inquiryType") String inquiryType,
@FormParam("inquiryHeader") String inquiryHeader,
@FormParam("inquiryBody") String inquiryBody,
@FormParam("inquiryDate") String inquiryDate)
{
String output = itemObj.insertInquiry(inquiryType, inquiryHeader, inquiryBody, inquiryDate);
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
String inquiryID = itemObject.get("inquiryID").getAsString();
String inquiryType = itemObject.get("inquiryType").getAsString();
String inquiryHeader = itemObject.get("inquiryHeader").getAsString();
String inquiryBody = itemObject.get("inquiryBody").getAsString();
String inquiryDate = itemObject.get("inquiryDate").getAsString();
String output = itemObj.updateInquiry(inquiryID, inquiryType, inquiryHeader, inquiryBody, inquiryDate);
return output;
}
@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deleteInquiry(String inquiryData)
{
//Convert the input string to an XML document
Document doc = Jsoup.parse(inquiryData, "", Parser.xmlParser());
//Read the value from the element <itemID>
String inquiryID = doc.select("inquiryID").text();
String output = itemObj.deleteInquiry(inquiryID);
return output;
}
}