/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.StorageDTO;
import facades.StorageFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 *
 * @author Henrik
 */
@Path("storage")
public class StorageResource {
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/exam",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final StorageFacade FACADE = StorageFacade.getStorageFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    // Create a Storage
    @Path("create")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String createStorage(StorageDTO storageDTO){
        FACADE.addStorage(storageDTO);
        return "{\"msg\":\"Storage created\"}";
    }
        
    // Find a Storage
    @Path("id/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public StorageDTO findStorage(@PathParam("id") Long id) {
        StorageDTO r = FACADE.getStorage(id);
        return r;
    }

    // Get all Storage
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<StorageDTO> getAllStorages() {
        return FACADE.getAllStorages().getAll();
    } 
    
    // Count Storages
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getStorageCount() {
        long count = FACADE.getStorageCount();
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }    
 
}
