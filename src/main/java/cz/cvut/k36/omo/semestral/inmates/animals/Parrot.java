package cz.cvut.k36.omo.semestral.inmates.animals;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.cvut.k36.omo.semestral.Subscriber;
import cz.cvut.k36.omo.semestral.report.actions.Action;

/**
 * The class contains methods related to the parrot.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, November 2021
 */
public class Parrot extends Pet{
    private Subscriber sub;
    public Parrot(
            @JsonProperty("Name")
                    String name,
            @JsonProperty("Age")
                    int age) {
        super(name, age);
        this.type = PetType.PARROT;
    }

    @Override
    public boolean checkSatiety() {
        return getSatiety() < 50;
    }

    @Override
    public void eat() {
        setSatiety(getSatiety()+35);
    }

    @Override
    public void decreaseSatiety() {
        setSatiety(getSatiety() - 30);
    }

    @Override
    public void subscribe(Subscriber sub) {
        this.sub = sub;
    }

    @Override
    public void unsubscribe(Subscriber sub) {
        this.sub = null;
    }

    @Override
    public void notifySubscribers(Action action) {
        this.sub.update(action);
    }
}
