/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.jsf;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import domain.Coffee;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.CoffeeService;

/**
 *
 * @author 878550
 */
@Named
@SessionScoped
public class CoffeeBean implements Serializable {

    @Inject
    private CoffeeService coffeeService;

    private String welcome = "hallo koffieleuten";
    private List<Coffee> coffeeList = new ArrayList<>();

    public CoffeeBean() {
    }

    public String getWelcome() {
        return welcome;
    }

    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }

    public List<Coffee> getCoffeeList() {
        return coffeeList;
    }

    public void setCoffeeList(List<Coffee> coffeeList) {
        this.coffeeList = coffeeList;
    }

    public String fillList(){
        this.coffeeList.addAll(coffeeService.allCoffees());
        return null;
    } 
    public String clearList(){
        this.coffeeList = new ArrayList<>();
        return null;
    }
    
    
}
