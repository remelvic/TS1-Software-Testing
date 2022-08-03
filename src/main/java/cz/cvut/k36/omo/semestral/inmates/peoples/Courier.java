package cz.cvut.k36.omo.semestral.inmates.peoples;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.cvut.k36.omo.semestral.Subscriber;
import cz.cvut.k36.omo.semestral.report.actions.Action;
import cz.cvut.k36.omo.semestral.report.actions.Eat;
import cz.cvut.k36.omo.semestral.stuff.StuffAPI;

/**
 * The class is needed for a courier who will replenish food in the refrigerator.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, December 2021
 */
public class Courier extends Person{
    public Courier(String name, int age) {
        super(name, age);
        this.type = PersonType.COURIER;
    }

    @Override
    public void subscribe(Subscriber sub) {

    }

    @Override
    public void unsubscribe(Subscriber sub) {

    }

    @Override
    public void notifySubscribers(Action action) {

    }

    @Override
    public void decreaseSatiety() {

    }

    @Override
    public boolean checkSatiety() {
        return false;
    }

    @Override
    public Eat eat(int time, StuffAPI stuffAPI) {
        return null;
    }
}
