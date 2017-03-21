/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dao.CoffeeDAO;
import domain.Coffee;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author 878550
 */
@Singleton
@Startup
public class Init {
    
    @Inject
    CoffeeDAO cd;
    
    @PostConstruct
    public void init(){
        Coffee coffee = new Coffee( "black roast");   
        Coffee coffee2 = new Coffee( "brown roast");
        cd.save(coffee);
        cd.save(coffee2);
        
    }
    
}
