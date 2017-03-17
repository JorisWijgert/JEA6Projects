package JSONObjects;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Joris on 9-3-2017.
 */
public class RelationJSON {
    @XmlElement public int followerId;
    @XmlElement public int followingId;
}
