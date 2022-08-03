package cz.cvut.k36.omo.semestral.report.actions.repair;

import cz.cvut.k36.omo.semestral.home.rooms.Room;
import cz.cvut.k36.omo.semestral.inmates.peoples.Person;
import cz.cvut.k36.omo.semestral.report.actions.Action;
import cz.cvut.k36.omo.semestral.report.actions.ActionType;
import cz.cvut.k36.omo.semestral.stuff.Stuff;

/**
 * The class creates an action if you need to find documentation.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, December 2021
 */
public class FindDoc extends Action {
    private final Stuff brokenStuff;

    public FindDoc(int time, Room room, Person person, Stuff brokenStuff) {
        super(ActionType.FIND_DOC, time, room, person);
        this.brokenStuff = brokenStuff;
    }

    /**
     * Normal getter.
     * @return stuff as a string
     */
    public Stuff getStuff(){
        return this.brokenStuff;
    }
}
