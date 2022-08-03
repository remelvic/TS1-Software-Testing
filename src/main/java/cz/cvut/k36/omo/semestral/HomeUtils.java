package cz.cvut.k36.omo.semestral;

import cz.cvut.k36.omo.semestral.home.rooms.Room;
import cz.cvut.k36.omo.semestral.inmates.Inhabitant;
import cz.cvut.k36.omo.semestral.inmates.peoples.Person;
import cz.cvut.k36.omo.semestral.stuff.StuffAPI;

/**
 * The class contains methods that contain frequently repeated code in different classes.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, December 2021
 */
public class HomeUtils {

    /**
     * The method simply changes the room of the resident of the house.
     * @param inhabitant of the house is the one who will change the room
     * @param newRoom is the room in which the inhabitant of the house will appear
     */
    public static void changeRoom(Inhabitant inhabitant, Room newRoom){
        inhabitant.getCurrentRoom().removeInhabitant(inhabitant);
        inhabitant.setCurrentRoom(newRoom);
        inhabitant.getCurrentRoom().addInhabitant(inhabitant);
    }

    /**
     * When the method is called, the person stops interacting with the elements of the house in addition to the
     * inhabitants of the house .
     * @param person is the one who stops any interaction with the elements of the house
     * @param stuffAPI is what we use to stop the action with any stuff in the house.
     */
    public static void stopUseStuff(Person person, StuffAPI stuffAPI){
        person.setUsing(false);
        stuffAPI.stopUsingStuff(person, person.getUsedStuff());
        person.setUsedStuff(null);
    }
}
