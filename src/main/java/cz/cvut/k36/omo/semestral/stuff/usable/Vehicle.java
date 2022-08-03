package cz.cvut.k36.omo.semestral.stuff.usable;

import cz.cvut.k36.omo.semestral.inmates.peoples.Person;

/**
 * The interface contains methods that will be used by all Vehicle in the house.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, November 2021
 */
public interface Vehicle extends Usable {

    /**
     * When calling the method, we determine who uses our vehicle.
     * @param person someone who, for example, rides a bike
     */
    void useVehicle(Person person);

    /**
     * When we call the method, the person leaves the vehicle.
     * @param person is the one who will leave the vehicle
     */
    void leaveVehicle(Person person);

}
