package JSONObjects;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Joris on 9-3-2017.
 */
public class KweetJSON {
    @XmlElement public int id;
    @XmlElement public String message;
    @XmlElement public int userId;
}
