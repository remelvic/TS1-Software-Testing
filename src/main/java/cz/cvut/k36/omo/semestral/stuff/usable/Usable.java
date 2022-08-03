package cz.cvut.k36.omo.semestral.stuff.usable;

import cz.cvut.k36.omo.semestral.inmates.peoples.Person;
import cz.cvut.k36.omo.semestral.report.actions.Use;
import cz.cvut.k36.omo.semestral.stuff.Stuff;
import cz.cvut.k36.omo.semestral.stuff.StuffAPI;

/**
 * The interface contains methods that will be used by all classes that relate to the Usable interface.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, November 2021
 */
public interface Usable extends Stuff {

    /**
     * We use the method to control the vehicle. Everyone will use the transport for 120 minutes. Then the leaveVehicle
     * method is called which indicates that the ride has ended
     * @param person is the one who controls the vehicles
     */
    Use use(Person person, int time, StuffAPI stuffAPI);

    /**
     * Method checks if someone interacts with our class.
     */
    boolean isUsed();

    /**
     * When the method is called, the person stops interacting with any element, not including the inhabitants of
     * the house.
     * @param person is the one who stops interacting with anything
     */
    void stopUsing(Person person);

    /**
     * Normal getter.
     * @return person as Person type
     */
    Person getPerson();
}
