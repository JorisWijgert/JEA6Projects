package JSONObjects;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Joris on 9-3-2017.
 */
public class RoleJSON {
    @XmlElement public int userId;
    @XmlElement public String role;
}
