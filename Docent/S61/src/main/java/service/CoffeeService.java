/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.CoffeeDAO;
import domain.Coffee;

import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import util.CoffeeEvent;

/**
 *
 * @author 878550
 */
@Stateless
public class CoffeeService {

    @Inject
    Event<CoffeeEvent> ev;

    @Inject
    CoffeeDAO cd;

    public List<Coffee> allCoffees() {
        CoffeeEvent coffeeEvent = new CoffeeEvent();
        ev.fire(coffeeEvent);
        return cd.allCoffees();
    }

    public void save(Coffee c) {
        cd.save(c);
    }

}
