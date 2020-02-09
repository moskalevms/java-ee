package ru.moskalevms.rest;

import ru.moskalevms.persist.Product;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/products")
public interface ProductServiceRs {

    @PUT
    @Path("/new")
    @Produces(MediaType.APPLICATION_JSON)
    void insert(Product product);

    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    void update(Product product);

    @DELETE
    @Path("/{id}/id")
    void delete(@PathParam("id") long id);

    @GET
    @Path("/{id}/id")
    @Produces(MediaType.APPLICATION_JSON)
    Product findById(@PathParam("id") long id);

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    List<Product> findAll();
}
