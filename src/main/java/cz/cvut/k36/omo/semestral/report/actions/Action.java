package cz.cvut.k36.omo.semestral.report.actions;

import cz.cvut.k36.omo.semestral.home.rooms.Room;
import cz.cvut.k36.omo.semestral.inmates.Inhabitant;

/**
 * An abstract class was created to create all the actions that take place in the house.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, December 2021
 */
public abstract class Action {
    private final ActionType actionType;
    private final int time;
    private final Room room;
    private Inhabitant inhabitant;

    public Action(ActionType actionType, int time, Room room, Inhabitant inhabitant){
        this.time = time;
        this.actionType = actionType;
        this.room = room;
        this.inhabitant = inhabitant;
    }

    public Action(ActionType actionType, int time, Room room) {
        this.actionType = actionType;
        this.time = time;
        this.room = room;
    }

    /**
     * Normal getter.
     * @return time as a String type
     */
    public int getTime(){
        return this.time;
    }

    /**
     * Normal getter.
     * @return action type as ActionType
     */
    public ActionType getActionType() {
        return this.actionType;
    }

    /**
     * Normal getter.
     * @return room as a Room type
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Normal getter.
     * @return inhabitant as Inhabitant type
     */
    public Inhabitant getInhabitant() {
        return inhabitant;
    }
}
