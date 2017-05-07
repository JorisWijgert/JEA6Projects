package JMSApplicatie;

/**
 * Created by Joris on 7-5-2017.
 */
public class Group {
    private String groupName;

    public Group(){

    }

    public Group(String groupName){
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
