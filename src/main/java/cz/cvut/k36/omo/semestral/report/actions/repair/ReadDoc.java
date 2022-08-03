package cz.cvut.k36.omo.semestral.report.actions.repair;

import cz.cvut.k36.omo.semestral.home.rooms.Room;
import cz.cvut.k36.omo.semestral.inmates.peoples.Person;
import cz.cvut.k36.omo.semestral.report.actions.Action;
import cz.cvut.k36.omo.semestral.report.actions.ActionType;
import cz.cvut.k36.omo.semestral.stuff.Stuff;

/**
 * The class creates an action if you need to read the documentation.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, December 2021
 */
public class ReadDoc extends Action {
    private final Stuff brokenStuff;

    public ReadDoc(int time, Room room, String documentation, Person reader, Stuff brokenStuff) {
        super(ActionType.READ_DOC, time, room, reader);
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
