package cz.cvut.k36.omo.semestral.stuff.usable.microwave;

import cz.cvut.k36.omo.semestral.inmates.peoples.Person;
import cz.cvut.k36.omo.semestral.report.actions.Use;
import cz.cvut.k36.omo.semestral.stuff.StuffAPI;

/**
 * The method switches the state of the microwave.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, December 2021
 */
public class CookingFoodState implements MicrowaveState{
    @Override
    public boolean isUsed(MicrowaveContext context) {
        return true;
    }

    @Override
    public void putFood(MicrowaveContext context, String food) {
    }

    @Override
    public void takeFood(MicrowaveContext context) {
        context.getMicrowave().takeFood();
    }

    @Override
    public Use use(MicrowaveContext context, Person person, int time , StuffAPI stuffAPI) {
        return null;
    }

    @Override
    public void stopUsing(MicrowaveContext context, Person person) {
        context.getMicrowave().stopUsing(person);
    }

}
