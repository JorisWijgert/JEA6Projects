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
import javax.inject.Inject;

/**
 *
 * @author 878550
 */
@Stateless
public class CoffeeService {

    @Inject
    CoffeeDAO cd;

    public List<Coffee> allCoffees() {
        return cd.allCoffees();
    }

}
