package cz.cvut.k36.omo.semestral.report.actions;

import cz.cvut.k36.omo.semestral.home.rooms.Room;
import cz.cvut.k36.omo.semestral.inmates.Inhabitant;

/**
 * The class creates an action when all residents of the house wake up.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, December 2021
 */
public class WakeUp extends Action {

    public WakeUp(int time, Room room, Inhabitant person) {
        super(ActionType.WAKE_UP, time, room, person);
        this.getInhabitant().wakeUp();
    }
}
