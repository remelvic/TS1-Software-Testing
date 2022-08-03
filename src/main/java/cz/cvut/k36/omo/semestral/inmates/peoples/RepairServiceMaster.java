package cz.cvut.k36.omo.semestral.inmates.peoples;

import cz.cvut.k36.omo.semestral.Subscriber;
import cz.cvut.k36.omo.semestral.report.actions.Action;
import cz.cvut.k36.omo.semestral.report.actions.Eat;
import cz.cvut.k36.omo.semestral.report.actions.repair.Repair;
import cz.cvut.k36.omo.semestral.stuff.Stuff;
import cz.cvut.k36.omo.semestral.stuff.StuffAPI;
import cz.cvut.k36.omo.semestral.stuff.StuffState;

/**
 * The class is needed in order to fix any stuff in the house that the father cannot fix.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, December 2021
 */
public class RepairServiceMaster extends Person{

    public RepairServiceMaster(String name, int age) {
        super(name, age);
        this.type = PersonType.REPAIR_SERVICE_MASTER;
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
}
