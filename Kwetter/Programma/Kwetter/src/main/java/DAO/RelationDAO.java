package DAO;

import Domain.Relation;
import Domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Joris on 7-3-2017.
 */

@Stateless
public class RelationDAO {

    @PersistenceContext
    EntityManager em;

    public void follow(Relation relation) {
        em.persist(relation);
    }

    public List<Relation> getFollowers(User user) {
        Query query = em.createNamedQuery("relation.followers");
        query.setParameter("user", user);
        return query.getResultList();
    }

    public List<Relation> getFollowing(User user){
        Query query = em.createNamedQuery("relation.following");
        query.setParameter("user", user);
        return query.getResultList();
    }
}
