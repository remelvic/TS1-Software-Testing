package cz.cvut.k36.omo.semestral.report.actions.repair;

import cz.cvut.k36.omo.semestral.home.rooms.Room;
import cz.cvut.k36.omo.semestral.inmates.peoples.Person;
import cz.cvut.k36.omo.semestral.report.actions.Action;
import cz.cvut.k36.omo.semestral.report.actions.ActionType;
import cz.cvut.k36.omo.semestral.stuff.Stuff;

/**
 * The class creates an action when we repair any element of type Stuff.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, December 2021
 */
public class Repair extends Action {
    private final Stuff stuffToRepair;

    public Repair(int time,  Room room, Person person, Stuff stuff) {
        super(ActionType.REPAIR, time,  room, person);
        this.stuffToRepair = stuff;
    }

    /**
     * Normal getter.
     * @return stuff to repair as Stuff type
     */
    public Stuff getStuffToRepair() {
        return stuffToRepair;
    }
}
