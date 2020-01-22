/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.BikeDTO;
import entities.Bike;
import facades.BikeFacade;
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
@Path("bike")
public class BikeResource {
        
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/exam",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final BikeFacade FACADE =  BikeFacade.getBikeFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    // Create a Bike
    @Path("create")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String createBike(BikeDTO bikeDTO){
        FACADE.addBike(bikeDTO);
        return "{\"msg\":\"Bike created\"}";
    }
    
    // Edit a Bike
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public BikeDTO editBike(String bike){
        Bike b = GSON.fromJson(bike, Bike.class);
        BikeDTO bDTO = new BikeDTO(b);
        return FACADE.editBike(bDTO);
    }
    
    // Delete a Bike
    @DELETE
    @Path("/delete/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public BikeDTO deleteBike(@PathParam("id") Long id){
        return FACADE.deleteBike(id);
    }
    
    
    
    
    
    // Find a Bike
    @Path("id/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public BikeDTO findBike(@PathParam("id") Long id) {
        BikeDTO b = FACADE.getBike(id);
        return b;
    }
    
    // Get all Bikes
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<BikeDTO> getAllBikes() {
        return FACADE.getAllBikes().getAll();
    } 
    
    // Count Bikes
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getBikeCount() {
        long count = FACADE.getBikeCount();
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }      
    
    
    
}
