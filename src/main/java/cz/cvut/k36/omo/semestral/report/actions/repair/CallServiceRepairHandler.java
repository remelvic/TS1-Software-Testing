package cz.cvut.k36.omo.semestral.report.actions.repair;

import cz.cvut.k36.omo.semestral.inmates.peoples.Dad;
import cz.cvut.k36.omo.semestral.inmates.peoples.Person;
import cz.cvut.k36.omo.semestral.stuff.Stuff;

/**
 * The class needs an action call from the chain of responsibility. Call master.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, January 2022
 */
public class CallServiceRepairHandler extends AbstractRepairHandler {

    public CallServiceRepairHandler(){
        this.level = AbstractRepairHandler.CALL_SERVICE;
    }

    @Override
    protected void handle(Stuff stuff, Person person, int time) {
        this.getStuffAPI().getStuffList().get(this.getStuffAPI().getStuffList().indexOf(stuff)).
                notifySubscribers(((Dad) person).callService(stuff,this.getStuffAPI(), time + 60));
    }
}
