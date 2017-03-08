/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.concurrent.atomic.AtomicLong;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;

/**
 *
 * @author 878550
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@Stateless
public class CoffeeMonitor {

    AtomicLong COUNTER = new AtomicLong();

    public Long getCOUNTER() {
        return COUNTER.longValue();
    }

    public void onNewMonitoringEvent(@Observes CoffeeEvent ev) {
        COUNTER.incrementAndGet();

    }
}
