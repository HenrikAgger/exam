/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.MemberDTO;
import facades.MemberFacade;
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
@Path("member")
public class MemberResource {
        
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/exam",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final MemberFacade FACADE =  MemberFacade.getMemberFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    // Create a Member
    @Path("create")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String createMember(MemberDTO memberDTO){
        FACADE.addMember(memberDTO);
        return "{\"msg\":\"Member created\"}";
    }
    
    // Find a Member
    @Path("id/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public MemberDTO findMember(@PathParam("id") Long id) {
        MemberDTO m = FACADE.getMember(id);
        return m;
    }
    
    // Get all Members
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<MemberDTO> getAllMembers() {
        return FACADE.getAllMembers().getAll();
    }     
    
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMemberCount() {
        long count = FACADE.getMemberCount();
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }    
 
}
