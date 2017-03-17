package Service;

import DAO.DAOManager;
import DAO.KweetDAO;
import DAO.UserDAO;
import Domain.Kweet;
import Domain.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.Null;
import java.util.List;

/**
 * Created by Joris on 4-3-2017.
 */
@Stateless
public class KweetService {

    @Inject
    KweetDAO kweetDAO;

    @Inject
    UserDAO userDAO;

    /**
     * Creates a new kweet in the system.
     *
     * @param kweet to be created.
     */
    public void createKweet(Kweet kweet) {
        kweetDAO.create(kweet);
    }

    /**
     * Makes the given user like the given kweet.
     *
     * @param kweetId the kweet to be liked
     * @param userId  the id of the user that likes the kweet
     * @throws Exception            when the user already likes the kweet
     * @throws NullPointerException when the user or kweet doesn't exist
     */
    public void likeKweet(int kweetId, int userId) throws Exception {
        Kweet kweet = kweetDAO.get(kweetId);

        User liker = userDAO.get(userId);

        if (liker == null)
            throw new NullPointerException("User can't be found.");

        if (kweet == null)
            throw new NullPointerException("Kweet can't be found.");

        kweet.addLiker(liker);
    }

    /**
     * Gives a list of kweets of the given user itself
     * and of the users that the given user follows
     *
     * @param userId the id of the user where the timeline
     *               will be generated for
     * @return the generated timeline based on a list of kweets
     * @throws NullPointerException when the user doesn't exist
     */
    public List<Kweet> getTimeline(int userId) {
        User user = userDAO.get(userId);

        if (user == null)
            throw new NullPointerException("User doesn't exist!");

        return kweetDAO.timeline(user);
    }

    /**
     * Gives a list of the latest kweets of the given user
     * The list is limited to the amount
     * If the amount is smaller or equal to 0, the list is unlimited
     *
     * @param userId the id of the user where the latest kweets are requested for
     * @param amount the amount of kweets that are requested
     * @return the latest amount kweets of the given user
     * @throws NullPointerException when the user doesn't exist
     */
    public List<Kweet> latestKweets(int userId, int amount) {
        User user = userDAO.get(userId);

        if (user == null)
            throw new NullPointerException("User doesn't exist!");

        return kweetDAO.latest(user, amount);
    }

    /**
     * Gives a list of kweets that match the given keyword
     *
     * @param keyword the word that must be in the message of the kweets
     * @return the kweets where the message match the keyword
     */
    public List<Kweet> searchKweet(String keyword) {
        return kweetDAO.search(keyword);
    }
}
