/**
 * Created by SimonSchwieler on 2016-04-07.
 */

//rodi0231_sisc7379_arho2993
public class Result {


    private double result;
    private Participant participant;
    private Event event;

    public Result(double result) {

        this.result = result;
    }
    public Result (double result, Participant participant, Event event){
        this.result = result;
        this.participant = participant;
        this.event = event;

    }

    public void setResultat(double newResult) {
        result = newResult;

    }

    public double getResultat(){
        return result;
    }

    public String toString(){
        return result + "";
    }

    public String getNameOfEventAchievedIn() {
        return event.getEventName();

    }
}


