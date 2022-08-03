package cz.cvut.k36.omo.semestral.inmates.animals;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.cvut.k36.omo.semestral.Subscriber;
import cz.cvut.k36.omo.semestral.report.actions.Action;

/**
 * The class contains methods related to the cat.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, November 2021
 */
public class Cat extends Pet{
    private Subscriber sub;
    public Cat(
            @JsonProperty("Name")
                    String name,
            @JsonProperty("Age")
                    int age) {
        super(name, age);
        this.type = PetType.CAT;
    }

    @Override
    public boolean checkSatiety() {
        return getSatiety() < 15;
    }

    @Override
    public void eat() {
        setSatiety(getSatiety()+40);
    }

    @Override
    public void decreaseSatiety() {
        setSatiety(getSatiety() - 20);
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
