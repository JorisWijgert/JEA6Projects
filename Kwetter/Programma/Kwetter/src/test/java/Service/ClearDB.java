package Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Joris on 12-3-2017.
 */
public class ClearDB {

    public static void resetDB(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TestDB");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Relation").executeUpdate();
        entityManager.createQuery("DELETE FROM Kweet").executeUpdate();
        entityManager.createQuery("DELETE FROM User").executeUpdate();
        entityManager.createQuery("DELETE FROM Group").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
