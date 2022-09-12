package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.EmployeeFacade;
import utils.EMF_Creator;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("employee")
public class EmployeeResource
{
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final EmployeeFacade FACADE =  EmployeeFacade.getInstance(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

   /* @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response postExample(String input){
        RenameMeDTO rmdto = GSON.fromJson(input, RenameMeDTO.class);
        System.out.println(rmdto);
        return Response.ok().entity(rmdto).build();
    }*/

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllEmployees()
    {
        return Response.ok().entity(GSON.toJson(FACADE.getAll())).build();

    }

    @GET
    @Path("/highestpaid")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getHighestPaid()
    {
        return Response.ok().entity(GSON.toJson(FACADE.getEployeeWithHighestSalary())).build();
    }

    @GET
    @Path("/byId/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getById(@PathParam("id") int id)
    {

        return Response.ok().entity(GSON.toJson(FACADE.getById(id))).build();
    }

    @GET
    @Path("/byName/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getByName(@PathParam("name") String name)
    {
        return Response.ok().entity(GSON.toJson(FACADE.getByName(name))).build();
    }
}
