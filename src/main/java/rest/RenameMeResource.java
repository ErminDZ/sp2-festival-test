package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.BookingDTO;
import dtos.CarDTO;
import dtos.WashingAssistantDTO;
import utils.EMF_Creator;
import facades.FacadeExample;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Todo Remove or change relevant parts before ACTUAL use
@Path("xxx")
public class RenameMeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final FacadeExample FACADE =  FacadeExample.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {

        long count = FACADE.getCarCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }

    @Path("washass")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllWashingAssistants(){
        return Response.ok(GSON.toJson(FACADE.getAllWashingAssistants())).build();
    }

    @Path("bookings")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllBookings(){
        return Response.ok(GSON.toJson(FACADE.getAllBookings())).build();
    }

    //RolesAllowed not added for easier testing
    @Path("createbooking")
    //@RolesAllowed("admin")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createBoat(String Booking) {
        BookingDTO b = GSON.fromJson(Booking, BookingDTO.class);
        BookingDTO bo = FACADE.createBooking(b);
        return Response.ok(bo).build();
    }

    //RolesAllowed not added for easier testing
    @Path("assignWashAss/{id}")
    //@RolesAllowed("admin")
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response connectBoatWithHarbour(@PathParam("id") long id, String harbour) {
        WashingAssistantDTO w = GSON.fromJson(harbour, WashingAssistantDTO.class);
        BookingDTO bEdited = FACADE.assignWashAss(id, w.getId());
        return Response.ok(GSON.toJson(bEdited)).build();
    }

    //RolesAllowed not added for easier testing
    @Path("createwashingass")
    //@RolesAllowed("admin")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createwashingass(String WashingAssistant) {
        WashingAssistantDTO wa = GSON.fromJson(WashingAssistant, WashingAssistantDTO.class);
        WashingAssistantDTO wad = FACADE.createWashingAssistant(wa);
        return Response.ok(wad).build();
    }

    //RolesAllowed not added for easier testing
    @Path("editbooking/{id}")
    //@RolesAllowed("admin")
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response editBooking(@PathParam("id") long id, String booking) {
        BookingDTO b = GSON.fromJson(booking, BookingDTO.class);
        b.setId(id);
        BookingDTO bEdited = FACADE.editBooking(b);
        return Response.ok(bEdited).build();
    }

    //RolesAllowed not added for easier testing
    @Path("editcar/{id}")
    //@RolesAllowed("admin")
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response editCar(@PathParam("id") long id, String car) throws exceptions.CarNotFoundException {
        CarDTO c = GSON.fromJson(car, CarDTO.class);
        c.setId(id);
        CarDTO cEdited = FACADE.editCar(c);
        return Response.ok(cEdited).build();
    }

    @Path("deletebooking/{id}")
    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    public Response deleteBooking(@PathParam("id") long id) {
        return Response.ok(GSON.toJson(FACADE.deleteBooking(id))).build();
    }
}
