package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.ActorDTO;
import dtos.EmployeeDTO;
import errorhandling.EmployeeNotFoundException;
import facades.ActorFacade;
import facades.EmployeeFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("actor")
public class ActorResource
{
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final ActorFacade FACADE =  ActorFacade.getInstance(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }


    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createActor(String jsonInput){
        //System.out.println(employeeDTO);
        return Response.ok().entity(GSON.toJson(FACADE.create(GSON.fromJson(jsonInput, ActorDTO.class)))).build();
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllActors()
    {
        return Response.ok().entity(GSON.toJson(FACADE.getAll())).build();

    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getById(@PathParam("id") Integer id)
    {

        return Response.ok().entity(GSON.toJson(FACADE.getById(id))).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response deleteById(@PathParam("id") Integer id)
    {
        FACADE.delete(id);
        return Response.ok("Actor with id: " + id + "has been deleted").build();
    }

    @GET
    @Path("/testexception")
    @Produces({MediaType.APPLICATION_JSON})
    public Response throwException() throws Exception {
        throw  new Exception("my exception");

    }
}
