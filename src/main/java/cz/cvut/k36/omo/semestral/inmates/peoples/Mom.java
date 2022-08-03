package cz.cvut.k36.omo.semestral.inmates.peoples;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.cvut.k36.omo.semestral.Subscriber;
import cz.cvut.k36.omo.semestral.report.actions.Action;
import cz.cvut.k36.omo.semestral.report.actions.Eat;
import cz.cvut.k36.omo.semestral.report.actions.FeedBaby;
import cz.cvut.k36.omo.semestral.stuff.StuffAPI;

/**
 * The class contains methods related to the mom.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, November 2021
 */
public class Mom extends Person{
    private Subscriber sub;
    public Mom(
            @JsonProperty("Name")
            String name,
            @JsonProperty("Age")
            int age) {
        super(name, age);
        this.type = PersonType.MOM;
    }


    @Override
    public void decreaseSatiety() {
        setSatiety(getSatiety() - 34);
    }

    @Override
    public boolean checkSatiety() {
        return getSatiety() < 24;
    }

    @Override
    public Eat eat(int time, StuffAPI stuffAPI) {
        return new Eat(time , this.getCurrentRoom(), this, stuffAPI);
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

    /**
     * When the methods are called, the mother feeds the baby.
     * @param baby who needs to be fed.
     */
    public FeedBaby feedBaby(Baby baby, int time) {
        return new FeedBaby(time,baby.getCurrentRoom(), this, baby);
    }
}
