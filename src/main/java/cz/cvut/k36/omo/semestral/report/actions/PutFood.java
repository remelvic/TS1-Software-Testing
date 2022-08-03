package cz.cvut.k36.omo.semestral.report.actions;

import cz.cvut.k36.omo.semestral.home.rooms.Room;
import cz.cvut.k36.omo.semestral.inmates.Inhabitant;
import cz.cvut.k36.omo.semestral.stuff.Stuff;

/**
 * The class creates an action when someone puts food in the refrigerator or microwave.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, December 2021
 */
public class PutFood extends Action{
    private final String food;
    private final Stuff stuff;

    public PutFood(int time, Room room, Inhabitant inhabitant, String food, Stuff stuff) {
        super(ActionType.PUT_FOOD, time, room, inhabitant);
        this.food = food;
        this.stuff = stuff;
    }

    /**
     * Normal getter.
     * @return food as a String type
     */
    public String getFood(){
        return this.food;
    }

    /**
     * Normal getter.
     * @return stuff as a string
     */
    public Stuff getStuff(){
        return this.stuff;
    }
}
