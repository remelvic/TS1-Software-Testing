package cz.cvut.k36.omo.semestral.report;

import cz.cvut.k36.omo.semestral.report.actions.ActionType;

import java.util.Arrays;

/**
 *  We use the Report class to store getters and setters.
 *  @author Victor Remel
 *  @author Roman Bulavkin
 *  @version 1.0, December 2021
 */
public class Report {
    private String time;
    private String room;
    private String person;
    private String stuff;
    private ActionType actionType;
    private String consumption;
    private String pet;
    private String changeStateComment;
    private String food;

    public Report(){
        this.time = "";
        this.room = "";
        this.person = "";
        this.stuff = "";
        this.pet = "";
        this.consumption = "";
        this.changeStateComment = "";
        this.food = "";
    }

    @Override
    public String toString(){
        return this.actionType.toString() + this.time + this.room + this.stuff + this.person + this.food + this.pet +
                this.consumption + this.changeStateComment+"\n";
    }

    /**
     * Normal setter.
     */
    public void setChangeStateComment(String changeStateComment) {
        this.changeStateComment = changeStateComment;
    }

    /**
     * Normal setter.
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Normal setter.
     */
    public void setRoom(String room) {
        this.room = room;
    }

    /**
     * Normal setter.
     */
    public void setPerson(String person) {
        this.person = person;
    }

    /**
     * Normal setter.
     */
    public void setStuff(String stuff) {
        this.stuff = stuff;
    }

    /**
     * Normal setter.
     */
    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    /**
     * Normal setter.
     */
    public void setConsumption(double consumption) {
        this.consumption = String.valueOf(consumption);
    }

    /**
     * Normal setter.
     */
    public void setPet(String pet){
        this.pet = pet;
    }

    /**
     * Normal setter.
     */
    public void setFood(String food){
        this.food = food;
    }

    public int getTime(){
        String str;
        str = time.replaceAll("[^0-9]+", " ");
        int hour = Integer.parseInt(Arrays.asList(str.trim().split(" ")).get(1));
        int min = Integer.parseInt(Arrays.asList(str.trim().split(" ")).get(2));
        int hoursInMins = hour * 60;
        return hoursInMins + min;
    }

}
