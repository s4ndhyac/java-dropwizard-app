package com.test.dropwizard.resources;

import com.test.dropwizard.core.HelloWorld;
import com.test.dropwizard.db.HelloWorldDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("hello")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {

    private HelloWorldDAO helloWorldDAO;

    public HelloWorldResource(HelloWorldDAO helloWorldDAO)
    {
        this.helloWorldDAO = helloWorldDAO;
    }

    @GET
    @UnitOfWork
    public HelloWorld getHelloWorld(@QueryParam("id") Long id)
    {
        return helloWorldDAO.findById(id);
    }

    @POST
    @UnitOfWork
    public HelloWorld createHelloWorld(HelloWorld helloWorld)
    {
        return helloWorldDAO.create(helloWorld);
    }

    @GET
    @UnitOfWork
    @Path("all")
    public List<HelloWorld> findAll()
    {
        return helloWorldDAO.findAll();
    }

}
