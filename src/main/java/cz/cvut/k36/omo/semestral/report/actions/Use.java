package cz.cvut.k36.omo.semestral.report.actions;

import cz.cvut.k36.omo.semestral.home.rooms.Room;
import cz.cvut.k36.omo.semestral.inmates.Inhabitant;
import cz.cvut.k36.omo.semestral.stuff.StuffAPI;
import cz.cvut.k36.omo.semestral.stuff.usable.Usable;

/**
 * The class creates an action when we want to use any element of type Stuff.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, December 2021
 */
public class Use extends Action{
    private final Usable stuffToUse;

    public Use(int time, Room room, Inhabitant inhabitant, Usable stuffToUse, StuffAPI stuffAPI) {
        super(ActionType.USE, time, room, inhabitant);
        this.stuffToUse = stuffToUse;
    }

    /**
     * Normal getter.
     * @return stuff to use as Stuff type
     */
    public Usable getStuffToUse() {
        return stuffToUse;
    }
}
