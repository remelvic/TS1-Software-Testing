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
public class ReadyToCookState implements MicrowaveState{

    @Override
    public boolean isUsed(MicrowaveContext context) {
        return false;
    }

    @Override
    public void putFood(MicrowaveContext context, String food) {
        context.getMicrowave().putFood(food);
    }

    @Override
    public void takeFood(MicrowaveContext context) {
    }

    @Override
    public Use use(MicrowaveContext context, Person person, int time , StuffAPI stuffAPI) {
        return context.getMicrowave().use(person, time, stuffAPI);
    }

    @Override
    public void stopUsing(MicrowaveContext context, Person person) {

    }
}
