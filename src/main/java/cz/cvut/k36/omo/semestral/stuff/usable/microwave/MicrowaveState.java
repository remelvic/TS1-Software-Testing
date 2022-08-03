package cz.cvut.k36.omo.semestral.stuff.usable.microwave;

import cz.cvut.k36.omo.semestral.inmates.peoples.Person;
import cz.cvut.k36.omo.semestral.report.actions.Use;
import cz.cvut.k36.omo.semestral.stuff.StuffAPI;

/**
 * The interface is needed to implement the State Machine pattern.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, December 2021
 */
public interface MicrowaveState {

    /**
     * The method determines whether a microwave is being used.
     */
    boolean isUsed(MicrowaveContext context);

    /**
     * Method means that the food was put in the microwave.
     * @param food is what kind of food was put
     */
    void putFood(MicrowaveContext context, String food);

    /**
     * Method means that the food was taken from the microwave.
     */
    void takeFood(MicrowaveContext context);

    /**
     * The method creates human interaction with the microwave.
     * @param person the one who interacts with the microwave
     * @param time this is what time the interaction began
     * @return us an action of type Use
     */
    Use use(MicrowaveContext context, Person person, int time, StuffAPI stuffAPI);

    /**
     * The method stops using the microwave.
     * @param person is the one who will end the interaction.
     */
    void stopUsing(MicrowaveContext context, Person person);
}
