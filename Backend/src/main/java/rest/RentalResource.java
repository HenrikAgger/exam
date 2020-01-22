/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.RentalDTO;
import entities.Rental;
import facades.RentalFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 *
 * @author Henrik
 */
@Path("rental")
public class RentalResource {
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/exam",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final RentalFacade FACADE =  RentalFacade.getRentalFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    // Create a Rental
    @Path("create")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String createRental(RentalDTO rentalDTO){
        FACADE.addRental(rentalDTO);
        return "{\"msg\":\"Rental created\"}";
    }
    
    // Edit a Rental
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public RentalDTO editRental(String rental){
        Rental r = GSON.fromJson(rental, Rental.class);
        RentalDTO rDTO = new RentalDTO(r);
        return FACADE.editRental(rDTO);
    }
    
    // Delete a Rental
    @DELETE
    @Path("/delete/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public RentalDTO deleteRental(@PathParam("id") Long id){
        return FACADE.deleteRental(id);
    }
    
    
    
    // Find a Rental
    @Path("id/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public RentalDTO findRental(@PathParam("id") Long id) {
        RentalDTO r = FACADE.getRental(id);
        return r;
    }
    
    // Get all Rentals
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<RentalDTO> getAllRentals() {
        return FACADE.getAllRentals().getAll();
    } 
    
    // No of Rentals
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRentalCount() {
        long count = FACADE.getRentalCount();
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }  
    
}
