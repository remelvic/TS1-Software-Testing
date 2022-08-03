package cz.cvut.k36.omo.semestral.report.actions.repair;

import cz.cvut.k36.omo.semestral.inmates.peoples.Dad;
import cz.cvut.k36.omo.semestral.inmates.peoples.Person;
import cz.cvut.k36.omo.semestral.inmates.peoples.RepairServiceMaster;
import cz.cvut.k36.omo.semestral.stuff.Stuff;
import cz.cvut.k36.omo.semestral.stuff.usable.Bike;
import cz.cvut.k36.omo.semestral.stuff.usable.Ski;

/**
 * The class needs an action call from the chain of responsibility. Repair stuff
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, January 2022
 */
public class RepairHandler extends AbstractRepairHandler{

    public RepairHandler(){
        this.level = AbstractRepairHandler.REPAIR;
    }

    @Override
    protected void handle(Stuff stuff, Person person, int time){
        if (stuff instanceof Bike || stuff instanceof Ski){
            this.getStuffAPI().getStuffList().get(this.getStuffAPI().getStuffList().indexOf(stuff)).
                    notifySubscribers(((Dad) person).fix(stuff, this.getStuffAPI(),time + 20));
        }else {
            this.getStuffAPI().getStuffList().get(this.getStuffAPI().getStuffList().indexOf(stuff)).
                    notifySubscribers((new RepairServiceMaster("Jesus", 33)).
                            fix(stuff, this.getStuffAPI(),time + 80));
        }
    }
}