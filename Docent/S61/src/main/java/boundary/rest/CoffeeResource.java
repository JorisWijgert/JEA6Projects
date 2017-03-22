/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.rest;

import domain.Coffee;
import java.net.URI;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import service.CoffeeService;

/**
 *
 * @author 878550
 */
@Stateless
@Path("coffees")
public class CoffeeResource {

    @Inject
    CoffeeService cs;

    @Context
    UriInfo uriInfo;

    @GET
    public List<Coffee> allCoffee() {
        return cs.allCoffees();
    }

    @GET
    @Path("{id}")
    public Coffee getCoffee(@PathParam("id") int id) {
        return cs.allCoffees().get(id-1); // todo from dao
    }

    @POST
    public Response addCoffee(Coffee coffee) {
        cs.save(coffee);
        URI uri = null;
        if (coffee != null) {
            uri = uriInfo.getAbsolutePathBuilder().
                    path(coffee.getId().toString()).
                    build();
        }
        return Response.created(uri).build();
    }

}
