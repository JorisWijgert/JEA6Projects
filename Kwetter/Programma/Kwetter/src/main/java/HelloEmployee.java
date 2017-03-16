import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by Joris on 23-2-2017.
 */

@Path("/helloemployee")
public class HelloEmployee {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("application/json")
    public List<Employee> getClichedMessage() {
        // Return some cliched textual content
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EmployeeUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createNamedQuery("employee.all").getResultList();
    }
}
