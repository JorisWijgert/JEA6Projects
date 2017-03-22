package Service;

import DAO.DAOManager;
import DAO.KweetDAO;
import DAO.RelationDAO;
import DAO.UserDAO;
import Domain.Relation;
import Domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Joris on 4-3-2017.
 */
@Stateless
public class UserService {

    @Inject
    UserDAO userDAO;

    @Inject
    RelationDAO relationDAO;
    /**
     * Creates a user of Kwetter in the system.
     *
     * @param user the user that will be created
     * @throws NullPointerException     when username or password are null
     * @throws IllegalArgumentException when the user already exists
     */
    public void createUser(User user) throws NullPointerException, IllegalArgumentException {
        if (user.getName() == null || user.getName().trim().isEmpty())
            throw new NullPointerException("Username can't be null!");
        if (user.getPassword() == null || user.getPassword().trim().isEmpty())
            throw new NullPointerException("Password can't be null!");
        if (userDAO.get(user.getName()) != null || userDAO.get(user.getId()) != null)
            throw new IllegalArgumentException("User already exists!");

        userDAO.create(user);
    }

    /**
     * Updates a user of Kwetter in the system.
     *
     * @param user the updated user
     * @throws NullPointerException     when user doesn't exist
     * @throws IllegalArgumentException when the username already exist or the group name was different
     */
    public void updateUser(User user) throws NullPointerException, IllegalArgumentException {
        User userToUpdate = userDAO.get(user.getId());
        if (userToUpdate == null)
            throw new NullPointerException("Can't find the requested user to update.");

        if (!userToUpdate.getName().equals(user.getName())) {
            if (userDAO.get(user.getName()) != null)
                throw new IllegalArgumentException("Username already exists!");
        }

        if (!userToUpdate.getGroup().getGroupName().equals(user.getGroup().getGroupName()))
            throw new IllegalArgumentException("There was an attempt to change the group name!");

        userDAO.update(user);
    }

    /**
     * Searches for a user with the given name
     * Returns null if no user can be found
     *
     * @param name the name of the requested user
     * @return the found user based on the given name
     */
    public User getUser(String name) {
        return userDAO.get(name);
    }

    /**
     * Searches for a user with the given id
     * Returns null if no user can be found
     *
     * @param id the id of the requested user
     * @return the found user based on the given id
     */
    public User getUser(int id) {
        return userDAO.get(id);
    }

    /**
     * Makes a relation where one user follows another
     *
     * @param follower  the id of the user that is the follower
     * @param following the id of the user that gets followed
     * @throws Exception when the user follows itself, when a user can't be found or when the user already follows the user
     */
    public void follow(int follower, int following) throws Exception {
        User followerUser = userDAO.get(follower);
        if (followerUser == null)
            throw new NullPointerException("Can't find the user that is the follower!");

        User followingUser = userDAO.get(following);
        if (followingUser == null)
            throw new NullPointerException("Can't find the user that is the following!");

        for( Relation relation: relationDAO.getFollowing(followerUser))
            if (relation.getFollowing().getId() == followingUser.getId())
                throw new IllegalArgumentException("Follower already follows the following.");

        Relation relation = new Relation(followerUser, followingUser);
        relationDAO.follow(relation);
    }

    /**
     * Returns a list of users that follow the given user
     *
     * @param following the id of the user that is being followed
     * @return the list of followers
     */
    public List<User> getFollowers(int following) {
        User followingUser = userDAO.get(following);
        ArrayList<User> followers = new ArrayList<>();

        if (followingUser == null)
            throw new NullPointerException("Following can't be found.");

        for (Relation relation : relationDAO.getFollowers(followingUser))
            followers.add(relation.getFollower());

        return Collections.unmodifiableList(followers);
    }

    /**
     * Returns a list of users that the given user follows
     *
     * @param follower the id of the user that follows
     * @return the list of following users
     */
    public List<User> getFollowing(int follower) {
        User followerUser = userDAO.get(follower);
        ArrayList<User> following = new ArrayList<>();

        if (followerUser == null)
            throw new NullPointerException("Follower can't be found.");

        for (Relation relation : relationDAO.getFollowing(followerUser))
            following.add(relation.getFollowing());

        return Collections.unmodifiableList(following);
    }
}
