/**
 * Created by SimonSchwieler on 2016-04-07.
 */
//rodi0231_sisc7379_arho2993

import java.util.ArrayList;
import java.util.ArrayList;
public class Event {

    private String eventName;
    private int attemptsAllowed;
    private boolean biggerBetter;

    public Event(String eventName, int attemptsAllowed, boolean biggerBetter) {
        this.eventName = eventName;
        this.attemptsAllowed = attemptsAllowed;
        this.biggerBetter = biggerBetter;
    }

    public void setEventName(String newEventName) {
        eventName = newEventName;
    }

    public void setAttemptsAllowed(int newAttemptsAllowed) {
        attemptsAllowed = newAttemptsAllowed;
    }

    public void setBiggerBetter(boolean newBiggerBetter) {
        biggerBetter = newBiggerBetter;
    }

    public String getEventName() {
        return eventName;
    }

    public int getAttemptsAllowed() {
        return attemptsAllowed;
    }

    public boolean getBiggerBetter() {
        return biggerBetter;
    }
}


