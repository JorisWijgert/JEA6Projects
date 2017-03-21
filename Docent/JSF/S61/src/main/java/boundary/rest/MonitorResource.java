/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import util.CoffeeMonitor;

/**
 *
 * @author 878550
 */
@Path("statistics")
@Stateless
public class MonitorResource {
    
    @Inject
    CoffeeMonitor monitor;
    
    @GET
    public JsonObject statistics(){
        return Json.createObjectBuilder().
                add("total-msg-count", monitor.getCOUNTER()).
                build();
    }
}
