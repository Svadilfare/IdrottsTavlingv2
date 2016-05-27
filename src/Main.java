/**
 * Created by SimonSchwieler on 2016-04-07.
 */
//rodi0231_sisc7379_arho2993

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Main {

    private Scanner keyboard = new Scanner(System.in);

    ArrayList<Event> eventArrayList = new ArrayList<Event>();
    ArrayList<Result> resultArrayList = new ArrayList<Result>();
    ArrayList<Participant> participantArrayList = new ArrayList<Participant>();

    public String readText(String prompt) {
        System.out.print(prompt);
        return keyboard.nextLine();
    }

    public int readNumber(String prompt) {
        System.out.print(prompt);
        int number = keyboard.nextInt();
        keyboard.nextLine();
        return number;
    }

    private String readCommand() {
        return readText("Command: ").toLowerCase();
    }

    private void initiate() {
        System.out.println("Welcome");

    }

    private void writeMenu() {
        System.out.println("Following commandos are possible:");
        System.out.println(" Add event");
        System.out.println(" Add participant");
        System.out.println(" Remove participant");
        System.out.println(" Add result");
        System.out.println(" Participant");
        System.out.println(" Teams");
        System.out.println(" Message (followed by your message)");
        System.out.println(" Reinitialize");
        System.out.println(" Exit");
    }

    private void manageCommand(String command) {




        if (command.matches("message.+")) {

            message(command);
        }

        else if (returnEvents().toLowerCase().indexOf(command.toLowerCase()) != -1){
            resultEvent(command);
        }


        else {

            switch (command) {
                case "add event":
                    addEvent();
                    break;
                case "add participant":
                    addParticipant();
                    break;
                case "remove participant":
                    removeParticipant();
                    break;
                case "add result":
                    addResult();
                    break;
                case "participant":
                    resultParticipant();
                    break;
                case "result event":
                    break;
                case "teams":
                    resultTeam();
                    break;
                case "message":
                    break;
                case "reinitialize":
                    reinitialize();
                    break;
                case "exit":
                    exit();
                    break;
                default:
                    System.out.println("Wrong command: " + command);
                    writeMenu();
            }
        }
    }

    private void addEvent() {

        System.out.print("Enter name of event: ");
        String eventName = "";
        while (eventName.isEmpty() || getEvent(eventName) != null) {
            eventName = keyboard.nextLine();
            eventName = eventName.trim();
            if (eventName.isEmpty()) {
                System.out.print("Names can't be empty, write something: ");
            } else if (getEvent(eventName) != null) {
                System.out.print("Duplicate event name, write something else: ");
            }

        }
        eventName = eventName.toLowerCase();
        char[] name = eventName.toCharArray();
        name[0] = ("" + (name[0])).toUpperCase().charAt(0);
        eventName = new String(name);

        System.out.print("Attempts allowed: ");
        int attemptsAllowed = keyboard.nextInt();

        while (attemptsAllowed <= 0){
            System.out.print("Number to small, write something else: ");
            attemptsAllowed = keyboard.nextInt();
        }

        keyboard.nextLine();

        System.out.print("Bigger better? (yes/no): ");


        boolean biggerBetter = false;
        boolean biggerBetterOptionDone = false;

        String biggerBetterString = keyboard.nextLine();

        while(!biggerBetterOptionDone) {


            if (biggerBetterString.equalsIgnoreCase("yes") || biggerBetterString.equalsIgnoreCase("y")) {
                biggerBetter = true;
                biggerBetterOptionDone = true;
            } else if (biggerBetterString.equalsIgnoreCase("no") || biggerBetterString.equalsIgnoreCase("n")) {
                biggerBetter = false;
                biggerBetterOptionDone = true;
            } else {
                System.out.print("Wrong insertion, write something else: ");
                biggerBetterString = keyboard.nextLine();
            }
        }

        eventName = eventName.trim().substring(0,1).toUpperCase() + eventName.substring(1).toLowerCase();


        Event e = new Event(eventName, attemptsAllowed, biggerBetter);
        eventArrayList.add(e);

        System.out.println(e.getEventName() + " has been added.");

    }

    private void addParticipant() {


        System.out.print("Enter participants first name: ");
        String firstName = keyboard.nextLine();

        firstName = firstName.trim();

        while (firstName.equals("")) {
            System.out.println("Names can't be empty!");

            System.out.print("Enter participants first name: ");
            firstName = keyboard.nextLine();
            firstName = firstName.trim();

        }

        System.out.print("Enter participants last name: ");
        String lastName = keyboard.nextLine();

        lastName = lastName.trim();

        while (lastName.equals("")) {
            System.out.println("Names can't be empty!");

            System.out.print("Enter participants last name: ");
            lastName = keyboard.nextLine();
            lastName = lastName.trim();

        }

        System.out.print("Enter participants team: ");
        String teamName = keyboard.nextLine();

        teamName = teamName.trim();


        while (teamName.equals("")) {
            System.out.println("Names can't be empty!");

            System.out.print("Enter participants team: ");
            teamName = keyboard.nextLine();

            teamName = teamName.trim();

        }

        firstName = firstName.trim().substring(0,1).toUpperCase() + firstName.substring(1).toLowerCase();
        lastName = lastName.trim().substring(0,1).toUpperCase() + lastName.substring(1).toLowerCase();
        teamName = teamName.trim().substring(0,1).toUpperCase() + teamName.substring(1).toLowerCase();
        int id = 99;

        Participant p = new Participant(firstName, lastName, teamName, id);
        participantArrayList.add(p);

        System.out.println(p.getFirstName() + " " + p.getLastName() + " from "
                + p.getTeamName() + " with number " + p.getId() + " has been added.");


    }

    public String returnEvents(){
        String events = "";
        for (Event e : eventArrayList){
            events += e.getEventName().toLowerCase();
        }
        return events;
    }

    private void removeParticipant() {

        System.out.print("Enter ID on participant you would like to remove: ");
        int enterID = keyboard.nextInt();
        keyboard.nextLine();

        boolean participantFound = false;

        for (int x = 0; x < participantArrayList.size(); x++) {

            if (participantArrayList.get(x).getId() == (enterID)) {

                System.out.println(
                        participantArrayList.get(x).getFirstName() + " " + participantArrayList.get(x).getLastName()
                                + " från " + participantArrayList.get(x).getTeamName() + " with number "
                                + participantArrayList.get(x).getId() + " removed");
                participantArrayList.remove(x);
                participantFound = true;

            }
        }

        if (!participantFound) {
            System.out.println("No participant with number " + enterID + " exists.");

        }
    }

    private void addResult() {

        System.out.print("Enter participants ID: ");
        int writtenId = keyboard.nextInt();
        boolean participantFound = false;


        for (int x = 0; x < participantArrayList.size(); x++) {
            if (participantArrayList.get(x).getId() == (writtenId)) {

                addResult2(participantArrayList.get(x));
                participantFound = true;
            }
        }
        if (!participantFound) {
            System.out.println("No participant with number " + writtenId + " exists.");
            keyboard.nextLine();

        }
    }


    public void addResult2(Participant p) {


        System.out.print("Enter event: ");
        keyboard.nextLine();
        String enterEventName = keyboard.nextLine();
        boolean eventFound = false;

        Map<Participant, Event> eventList = new HashMap<>();

        for (int d = 0; d < eventArrayList.size(); d++) {

            if (eventArrayList.get(d).getEventName().equalsIgnoreCase(enterEventName)) {

                Event e = eventArrayList.get(d);
                eventList.put(p, e);

                if (p.getAmountOfAttempts(e) < e.getAttemptsAllowed()) {

                    System.out.print("Result for " + p.getFirstName() + " " + p.getLastName() + " from " + p.getTeamName()
                            + " in the event " + e.getEventName() + ": ");

                    double result = keyboard.nextDouble();

                    while (result < 0) {
                        System.out.print("To low value entered, write something else: ");
                        result = keyboard.nextDouble();
                    }

                    Result r = new Result(result, p, e);
                    p.setResultToList(e, r);

                    System.out.println("Result " + result + " in " + e.getEventName() + " has been registred." + r);
                    keyboard.nextLine();
//                    System.out.print(r.getNameOfEventAchievedIn());
                }
                else {
                    System.out.print("To many tries!");
                }
            } else {
                System.out.println("No event called " + enterEventName + " exists.");
            }
        }
    }

    private void resultParticipant() {

        System.out.println("Resultlist for participants.");

        System.out.print("Type in the ID number: ");

        Participant p = getParticipant(keyboard.nextInt());


        try {

            String allResults = "";
            for (Event  e : eventArrayList) {

                allResults += "Results for " + p + " in ";

                ArrayList<Double> resultsByEvent = p.getResultsByEvent(e);
                String resultString = "";
                if(!resultsByEvent.isEmpty()){
                    for (Double r : resultsByEvent){
                        resultString += r;
                        if(resultsByEvent.indexOf(r) != resultsByEvent.size() -1){
                            resultString += ", ";
                        }
                    }
                    allResults += e.getEventName() + ": " + resultString + "\n";
                }
            }
            if (allResults.length() == 0){
                System.out.print("No results found");
            }
            else{
                System.out.print(allResults);
            }
        }
        catch(NullPointerException e){
            System.out.println("No participant with that ID");

        }
        keyboard.nextLine();
    }


    static class TopListPosition {
        public final String name;
        public final double score;
        public final Event event;

        public TopListPosition(String name, double score, Event event) {
            this.name = name;
            this.score = score;
            this.event = event;
        }
    }

    private void resultEvent(String command) {
        System.out.println("Results for " + command + ":");

        String event = command;
        final Event e = getEvent(event);

        List<TopListPosition> topList = new ArrayList<TopListPosition>();
        for (Participant participant : this.participantArrayList) {
            for (ResultList resultList : participant.getResults()) {
                if (!resultList.getEvent().getEventName().equalsIgnoreCase(event)){
                    System.out.println("No event with that name or no results found.");
//                    System.out.print(resultList.getEvent().getEventName() + event); //test för att se vad som söks
//                    return;
                }
                else{
                    topList.add(new TopListPosition(participant.getFirstName() + " " + participant.getLastName(),
                            resultList.getResult().getResultat(),
                            resultList.getEvent()));
                }
            }
        }
        Collections.sort(topList, new Comparator<TopListPosition>() {
                    public int compare(TopListPosition obj1, TopListPosition obj2) {
                        if (e.getBiggerBetter())
                            return (int) (obj2.score - obj1.score);
                        else
                            return (int) (obj1.score - obj2.score);
                    }
                }
        );
        int placement = 1;
        TopListPosition previousPosition = null;
//        for (TopListPosition topListPosition : topList) {
        for (int i = 0; i < topList.size(); i++) {
            TopListPosition currentPosition = topList.get(i);
            if (previousPosition != null) {
                if(currentPosition.score != previousPosition.score){
                    placement = i;
                    placement++;
                }
            }
            previousPosition = currentPosition;

            System.out.println((placement) + " " + currentPosition.score  + " " + currentPosition.name);

        }

    }

    Integer getPosition(Participant p, Event event) {
        List<TopListPosition> topList = new ArrayList<TopListPosition>();
        for (Participant participant : this.participantArrayList) {
            for (ResultList resultList : participant.getResults()) {
                if (resultList.getEvent().getEventName().equalsIgnoreCase(event.getEventName())) {
                    topList.add(new TopListPosition(participant.getFirstName() + " " + participant.getLastName(),
                            resultList.getResult().getResultat(),
                            resultList.getEvent()));
                }
            }
        }
        Collections.sort(topList, new Comparator<TopListPosition>() {
            public int compare(TopListPosition obj1, TopListPosition obj2) {
                if (event.getBiggerBetter())
                    return (int) (obj2.score - obj1.score);
                else
                    return (int) (obj1.score - obj2.score);
            }
        });
        int i = 1;
        for (TopListPosition tlp : topList) {
            if (tlp.name.equals(p.getFirstName() + " " + p.getLastName()))
                return i;
            ++i;
        }
        return null;
    }

    private static class TeamMedals {
        public int firstPlace;
        public int secondPlace;
        public int thirdPlace;

        public TeamMedals(int fp, int sp, int tp) {
            this.firstPlace = fp;
            this.secondPlace = sp;
            this.thirdPlace = tp;
        }
    }

    private static class TeamResult {
        public final TeamMedals teamMedals;
        public final String team;
        public TeamResult(TeamMedals teamMedals, String team) {
            this.teamMedals = teamMedals;
            this.team = team;
        }
    }

    private void resultTeam() {
        Map<String, TeamMedals> teams = new HashMap<>();
        for (Participant participant : this.participantArrayList) {
            for (Event event : this.eventArrayList) {
                Integer position = this.getPosition(participant, event);
                if (position != null) {
                    TeamMedals teamMedals = teams.get(participant.getTeamName());
                    if (teamMedals == null) {
                        teamMedals = new TeamMedals(0, 0, 0);
                        teams.put(participant.getTeamName(), teamMedals);
                    }
                    if (position == 1) {
                        teamMedals.firstPlace++;
                    } else if (position == 2) {
                        teamMedals.secondPlace++;
                    } else if (position == 3) {
                        teamMedals.thirdPlace++;
                    }
                }
            }
        }
        List<TeamResult> teamResults = new ArrayList<TeamResult>();
        for (Entry<String, TeamMedals> entry : teams.entrySet()) {
            teamResults.add(new TeamResult(entry.getValue(), entry.getKey()));
        }
        Collections.sort(teamResults, new Comparator<TeamResult>() {
            public int compare(TeamResult obj1, TeamResult obj2) {
                if (obj1.teamMedals.firstPlace != obj2.teamMedals.firstPlace) {
                    return obj2.teamMedals.firstPlace - obj1.teamMedals.firstPlace;
                }
                if (obj1.teamMedals.secondPlace != obj2.teamMedals.secondPlace) {
                    return obj2.teamMedals.secondPlace - obj1.teamMedals.secondPlace;
                }
                return obj2.teamMedals.thirdPlace - obj1.teamMedals.thirdPlace;
            }
        });
        System.out.println("1st    2nd     3rd    Team name");
        System.out.println("*******************************");
        for (TeamResult teamResult : teamResults) {
            System.out.println(teamResult.teamMedals.firstPlace + "    " +
                    teamResult.teamMedals.secondPlace + "       " +
                    teamResult.teamMedals.thirdPlace + "      " +
                    teamResult.team);
        }
    }

    private void message(String message) {

        message = message.replaceFirst("message", "");

        if (message.length() > 56) {
            message = message.substring(0, 56);

        }

        char fill = ' ';

        String toPad = "#";
        String specialare = new String(new char[toPad.length() + 56 - message.length()]).replace('\0', fill) + toPad;
        System.out.println("");
        System.out.println("############################################################");
        System.out.println("#                                                          #");
        System.out.print("# " + message.toUpperCase());
        System.out.println(specialare);
        System.out.println("#                                                          #");
        System.out.println("############################################################");

    }

    private void reinitialize() {

        String message = " ALL DATA HAS BEEN REMOVED";

        char fill = ' ';

        String toPad = "#";
        String specialare = new String(new char[toPad.length() + 57 - message.length()]).replace('\0', fill) + toPad;
        System.out.println("");
        System.out.println("############################################################");
        System.out.println("#                                                          #");
        System.out.print("#" + message);
        System.out.println(specialare);
        System.out.println("#                                                      	   #");
        System.out.println("############################################################");

        while (participantArrayList.size() > 0) {
            participantArrayList.clear();
        }

        while (resultArrayList.size() > 0) {
            resultArrayList.clear();
        }

        while (eventArrayList.size() > 0) {
            eventArrayList.clear();
        }
    }

    private void exit() {
        System.out.println("Good bye!");
        System.exit(0);
    }

    private void execute() {
        writeMenu();
        while (true) {
            String command = readCommand();
            manageCommand(command);
        }
    }
    private Participant getParticipant(int id) {
        for (Participant p : participantArrayList) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
    private Event getEvent (String eventName){
        for (Event e : eventArrayList){
            if (e.getEventName().equalsIgnoreCase(eventName)){
                return e;
            }
        }
        return null;
    }
    public static void main(String[] args) {
        Main competition = new Main();
        competition.initiate();
        competition.execute();
        competition.exit();
    }

}
