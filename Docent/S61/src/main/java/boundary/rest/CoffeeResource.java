/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.rest;

import domain.Coffee;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import service.CoffeeService;

/**
 *
 * @author 878550
 */
@Stateless
@Path("coffee")
public class CoffeeResource {
    @Inject
    CoffeeService cs;
    
    @GET
    public List<Coffee> allCoffee(){
        return cs.allCoffees();
    }
    
    
}
