package cz.cvut.k36.omo.semestral.stuff;

import cz.cvut.k36.omo.semestral.Publisher;
import cz.cvut.k36.omo.semestral.home.rooms.Room;

/**
 *  The interface is used to store the same type for sensors and refrigerator, car, etc.
 *  @author Victor Remel
 *  @author Roman Bulavkin
 *  @version 1.0, December 2021
 */
public interface Stuff extends Publisher {

    /**
     * Methods checks the usable classes quality.
     */
    boolean checkQuality();

    /**
     * Normal setter.
     */
    void setQuality();

    /**
     * When calling methods, the quality of the usable classes decreases.
     * Ex: (the TV breaks)
     */
    void decreaseQuality();

    /**
     * The method will change the state of stuff to received.
     * @param stuffState is the new state that stuff changes to
     */
    void changeStuffState(StuffState stuffState);

    /**
     * Normal getter
     * @return room as Room type
     */
    Room getRoom();

    /**
     * Normal getter.
     * @return documentation to stuff in the house
     */
    String getDocumentation();
}
