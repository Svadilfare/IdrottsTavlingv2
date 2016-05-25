/**
 * Created by SimonSchwieler on 2016-04-07.
 */
import java.util.ArrayList;

public class Team {

    private String name = "";
    private ArrayList<Participant> participants = new ArrayList<Participant>();

    public Team(String n) {
        this.name = n;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<Participant> participants) {
        this.participants = participants;
    }




}
