package cz.cvut.k36.omo.semestral.report.actions;

import cz.cvut.k36.omo.semestral.home.rooms.Room;
import cz.cvut.k36.omo.semestral.inmates.peoples.Person;
import cz.cvut.k36.omo.semestral.stuff.StuffAPI;

/**
 * The class creates an action when something is cooked.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, December 2021
 */
public class Cook extends Action{
    private final StuffAPI stuffAPI;

    public Cook(int time, Room room, Person person, StuffAPI stuffAPI) {

        super(ActionType.COOK, time,  room, person);
        this.stuffAPI = stuffAPI;
    }

    /**
     * Normal getter.
     * @return stuffAPI as StuffAPI type
     */
    public StuffAPI getStuffAPI() {
        return stuffAPI;
    }
}
