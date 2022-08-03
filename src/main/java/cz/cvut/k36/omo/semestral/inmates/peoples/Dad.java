package cz.cvut.k36.omo.semestral.inmates.peoples;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.cvut.k36.omo.semestral.Subscriber;
import cz.cvut.k36.omo.semestral.report.actions.*;
import cz.cvut.k36.omo.semestral.report.actions.repair.CallService;
import cz.cvut.k36.omo.semestral.report.actions.repair.FindDoc;
import cz.cvut.k36.omo.semestral.report.actions.repair.ReadDoc;
import cz.cvut.k36.omo.semestral.report.actions.repair.Repair;
import cz.cvut.k36.omo.semestral.stuff.Stuff;
import cz.cvut.k36.omo.semestral.stuff.StuffAPI;
import cz.cvut.k36.omo.semestral.stuff.StuffState;

/**
 * The class contains methods related to the dad.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, November 2021
 */
public class Dad extends Person{
    private Subscriber sub;
    public Dad(
            @JsonProperty("Name")
                    String name,
            @JsonProperty("Age")
                    int age) {
        super(name, age);
        this.type = PersonType.DAD;
    }

    @Override
    public void decreaseSatiety() {
        setSatiety(getSatiety()-27);
    }

    @Override
    public boolean checkSatiety() {
        return getSatiety() < 23;
    }

    @Override
    public Eat eat(int time,StuffAPI stuffAPI) {
        return new Eat(time, this.getCurrentRoom(), this, stuffAPI);
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
     * When the stuff method is called, the house will be repaired.
     * @param stuffToRepair is the stuff that needs to be repaired.
     * @param stuffAPI with the help of this variable we refer to the StuffAPI which is already fixing our stuff.
     */
    public Repair fix(Stuff stuffToRepair, StuffAPI stuffAPI, int time) {
        stuffAPI.getStuffList().get(stuffAPI.getStuffList().indexOf(stuffToRepair)).changeStuffState(StuffState.WORKING);
        stuffAPI.getStuffList().get(stuffAPI.getStuffList().indexOf(stuffToRepair)).setQuality();
        return new Repair(time, stuffToRepair.getRoom(), this, stuffToRepair);
    }

    /**
     * The method will create documentation read actions for stuff in the house.
     * @param doc is what the person reads.
     * @param time is at what time a person reads the documentation.
     * @param stuff is stuff to which we read the documentation.
     * @return an action like ReadDoc.
     */
    public ReadDoc readDocumentation(String doc, int time, Stuff stuff){
        return new ReadDoc(time, stuff.getRoom(), doc, this, stuff);
    }

    /**
     * The method will create documentation lookup actions for stuff in the house.
     * @param stuff is the stuff we're looking for documentation for.
     * @param time is the time at which we started looking for documentation.
     * @return an action of type FindDoc.
     */
    public FindDoc findDocumentation(Stuff stuff, int time){
        return new FindDoc(time , stuff.getRoom(), this, stuff);
    }

    /**
     * The method will create workshop call actions.
     * @param stuff is stuff because of which we are calling the service.
     * @param time is the time at which we call the service.
     * @return an action of type CallService.
     */
    public CallService callService(Stuff stuff, StuffAPI stuffAPI, int time){
        return new CallService(time, stuff.getRoom(), this, stuff);
    }
}
