package cz.cvut.k36.omo.semestral.report.actions;

import cz.cvut.k36.omo.semestral.home.rooms.Room;
import cz.cvut.k36.omo.semestral.stuff.Stuff;
import cz.cvut.k36.omo.semestral.stuff.StuffAPI;
import cz.cvut.k36.omo.semestral.stuff.StuffState;

/**
 * The class creates an action when something breaks.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, December 2021
 */
public class Broke extends Action{
    private final Stuff brokenStuff;

    public Broke(int time, Room room, Stuff stuff, StuffAPI stuffAPI) {
        super(ActionType.BROKE, time, room);
        this.brokenStuff = stuff;
        stuffAPI.changeStuffState(StuffState.BROKEN, this.brokenStuff);
    }

    /**
     * Normal getter.
     * @return broken stuff as Stuff type
     */
    public Stuff getBrokenStuff() {
        return brokenStuff;
    }
}
