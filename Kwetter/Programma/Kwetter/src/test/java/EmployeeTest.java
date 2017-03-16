import org.junit.Assert;
import org.junit.Test;

import javax.persistence.*;

/**
 * Created by Joris on 23-2-2017.
 */
public class EmployeeTest {

    //@Test
    public void FirstTest(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EmployeeUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Employee employee = new Employee();
        employee.setName("Empl1");
        employee.setSalary(1000000);

        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
        System.out.println("Program completed...");
    }

    //@Test
    public void SecondTest(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EmployeeUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.createNamedQuery("employee.all").getResultList();
    }

    @Test
    public void SuccessTest(){
        Assert.assertEquals(true, true);
    }

}