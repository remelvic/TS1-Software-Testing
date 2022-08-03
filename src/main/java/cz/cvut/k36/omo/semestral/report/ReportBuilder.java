package cz.cvut.k36.omo.semestral.report;

import cz.cvut.k36.omo.semestral.home.rooms.Room;
import cz.cvut.k36.omo.semestral.inmates.animals.Pet;
import cz.cvut.k36.omo.semestral.inmates.peoples.Person;
import cz.cvut.k36.omo.semestral.report.actions.ActionType;
import cz.cvut.k36.omo.semestral.stuff.Stuff;

import java.util.ArrayList;

/**
 * The class creates reports and then all reports will be combined into one report.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, December 2021
 */
public class ReportBuilder {
    private static final ArrayList<Report> arrayReport = new ArrayList<>();
    private final Report report = new Report();

    /**
     * The method determines the type of action that is occurring.
     * @param actionType The parameter specifies the type of action
     */
    public void addActionType(ActionType actionType) {
        report.setActionType(actionType);
    }

    /**
     * The method determines when this event occurred.
     * @param time in minutes
     */
    public void addTime(int time){
        String result = "Day: " + time / 24 / 60 + " Time: " + time / 60 % 24 + ":" + String.format("%02d",time % 60) + " ; ";
        report.setTime(result);
    }

    /**
     * The method will add only the day on which the action took place.
     * @param time in day
     */
    public void addDay(int time){
        String result = "Day: " + time/24/60+"; ";
        report.setTime(result);
    }

    /**
     * The method determines where this event occurred.
     * @param room is where the event happened
     */
    public void addRoom(Room room){
        report.setRoom(room.toString());
    }

    /**
     * The method determines who this event happened to.
     * @param person is with whom the event happened
     */
    public void addPerson(Person person){
        report.setPerson(person.toString());
    }

    /**
     * The method adds to the report why the state stuff has changed.
     * @param comment contains the reason for the change to stuff
     */
    public void addChangeStateComment(String comment){
        report.setChangeStateComment(comment);
    }

    /**
     * The method determines which sensor this event is associated with.
     * @param stuff is with what device the event happened
     */
    public void addStuff(Stuff stuff){
        report.setStuff(stuff.toString());
    }

    /**
     * The method will add the amount of energy expended to the report.
     */
    public void addConsumption(double consumption){
        report.setConsumption(consumption);
    }

    /**
     * The method will add an animal with which the action is taking place.
     * @param pet this is who the action takes place with
     */
    public void addPet(Pet pet){
        report.setPet(pet.toString());
    }

    /**
     * The method will add food to the report.
     * @param food this food is used by a person
     */
    public void addFood(String food){
        report.setFood(food);
    }

    /**
     * Normal getter.
     * @return array list with report
     */
    public static ArrayList<Report> getArray(){
        return arrayReport;
    }

    /**
     * The method will add reports to the list.
     * @param report what needs to be added to the list
     */
    public static void addReportToList(Report report){
        arrayReport.add(report);
    }

    /**
     * The method determines to return to us a ready-made report.
     */
    public Report getResult(){
        return this.report;
    }

}
