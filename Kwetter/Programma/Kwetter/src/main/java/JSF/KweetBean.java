package JSF;

import Domain.Kweet;
import Domain.User;
import Service.KweetService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joris on 25-3-2017.
 */
@Named("kweetBean")
@RequestScoped
public class KweetBean implements Serializable {
    @Inject
    KweetService kweetService;

    public KweetBean(){
    }

    public List<Kweet> getLatestKweets(User chosenUser)
    {
        return kweetService.latestKweets(chosenUser.getId(), 10);
    }
}
