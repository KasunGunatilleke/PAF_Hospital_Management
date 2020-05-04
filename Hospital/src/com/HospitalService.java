package com;
import model.Hospital;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Hospital")

public class HospitalService {
    Hospital h1 = new Hospital();
    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)

    public String readHospital() {
        return h1.readHospital();
    }
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String insertHospital(
        @FormParam("hosName") String hosName,
        @FormParam("hosTelNo") String hosTelNo,
        @FormParam("hosAddress") String hosAddress,
        @FormParam("hosEmail") String hosEmail
    ) {
        String output = h1.insertHospital(hosName, hosTelNo, hosAddress, hosEmail);
        return output;
    }
    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String updateHopital(String dData) {

        JsonObject h2 = new JsonParser().parse(dData).getAsJsonObject();
        String hosID = h2.get("hosID").getAsString();
        String hosName = h2.get("hosName").getAsString();
        String hosTelNoe = h2.get("hosTelNo").getAsString();
        String hosAddress = h2.get("hosAddress").getAsString();
        String hosEmail = h2.get("hosEmail").getAsString();


        String output = h1.updateHopital(hosID, hosName, hosTelNo, hosAddress, hosEmail);
        return output;
    }
    @DELETE
    @Path("/")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteHospital(String dData) {

        Document doc = Jsoup.parse(dData, "", Parser.xmlParser());


        String hosID = doc.select("hosID").text();
        String output = h1.deleteHospital(hosID);
        return output;
    }

}