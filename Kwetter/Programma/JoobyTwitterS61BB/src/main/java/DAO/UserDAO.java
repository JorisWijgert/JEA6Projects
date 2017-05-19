package DAO;

import Domain.User;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Joris on 19-5-2017.
 */
public class UserDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public UserDAO () {
        emf = Persistence.createEntityManagerFactory("Twitter");
        em = emf.createEntityManager();
    }

    public User getUser(String username) {
        try {
            Query query= em.createNamedQuery("user.getByName");
            query.setParameter("username", username);
            return (User) query.getSingleResult();
        } catch (NoResultException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<User> getUsers(){
        return em.createNamedQuery("user.all").getResultList();
    }

    public void updateUser(User user){
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    public void createUser(User user){
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }
}
