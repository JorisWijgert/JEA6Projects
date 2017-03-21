package DAO;

import Domain.Group;
import Domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Joris on 7-3-2017.
 */

@Stateless
public class UserDAO {

    @PersistenceContext
    EntityManager em;

    public void create(User user) {
        for (Group group : (List<Group>) em.createNamedQuery("group.all").getResultList()) {
            if (group.getGroupName().equals(user.getGroup().getGroupName())) {
                em.persist(user);
                return;
            }
        }
        em.persist(user.getGroup());
        em.persist(user);

    }

    public void update(User user) {
        em.merge(user);
    }

    public User get(int id) {
        try {
            Query query = em.createNamedQuery("user.getById");
            query.setParameter("userid", id);
            return (User) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public User get(String name) {
        try {
            Query query = em.createNamedQuery("user.getByName");
            query.setParameter("username", name);
            return (User) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public List<User> getAll() {
        return em.createNamedQuery("user.all").getResultList();
    }
}
