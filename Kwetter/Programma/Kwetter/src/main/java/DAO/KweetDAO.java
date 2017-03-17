package DAO;

import Domain.Kweet;
import Domain.User;
import com.sun.media.jfxmedia.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Joris on 7-3-2017.
 */

@Stateless
public class KweetDAO {

    @PersistenceContext
    EntityManager em;

    public void create(Kweet kweet) {
        em.persist(kweet);
    }

    public void like(Kweet kweet, User user) throws Exception {
        kweet.addLiker(user);
        em.merge(kweet);
    }

    public List<Kweet> timeline(User user) {
        Query query = em.createNamedQuery("kweet.timeline");
        query.setParameter("user", user);
        return query.getResultList();
    }

    public List<Kweet> latest(User user, int amount) {
        Query query = em.createNamedQuery("kweet.latest");
        query.setParameter("user", user);
        query.setMaxResults(amount);
        return query.getResultList();
    }

    public List<Kweet> search(String keyword) {
        Query query = em.createNamedQuery("kweet.search");
        query.setParameter("keyword", keyword);
        return query.getResultList();
    }

    public List<Kweet> getAll() {
        return em.createNamedQuery("kweet.all").getResultList();
    }

    public Kweet get(int kweetId) {
        try {
            Query query = em.createNamedQuery("kweet.getById");
            query.setParameter("kweetId", kweetId);
            return (Kweet) query.getSingleResult();
        } catch (NoResultException ex){
            return null;
        }
    }

    public void delete(Kweet kweet) {
        em.remove(kweet);
    }
}
