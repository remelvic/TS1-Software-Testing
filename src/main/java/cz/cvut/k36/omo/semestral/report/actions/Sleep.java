package cz.cvut.k36.omo.semestral.report.actions;

import cz.cvut.k36.omo.semestral.home.rooms.Room;
import cz.cvut.k36.omo.semestral.inmates.Inhabitant;

/**
 * The class creates an action when everyone goes to bed.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, December 2021
 */
public class Sleep extends Action{

    public Sleep(int time, Room room, Inhabitant inhabitant) {

        super(ActionType.SLEEP, time, room, inhabitant);
        this.getInhabitant().sleep();
    }
}
