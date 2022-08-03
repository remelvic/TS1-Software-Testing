package cz.cvut.k36.omo.semestral.report.actions.repair;

import cz.cvut.k36.omo.semestral.inmates.peoples.Dad;
import cz.cvut.k36.omo.semestral.inmates.peoples.Person;
import cz.cvut.k36.omo.semestral.stuff.Stuff;

/**
 * The class needs an action call from the chain of responsibility. Read documentation
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, January 2022
 */
public class ReadDocRepairHandler extends AbstractRepairHandler{
    public ReadDocRepairHandler(){
        this.level = AbstractRepairHandler.READ_DOC;
    }

    @Override
    protected void handle(Stuff stuff, Person person, int time) {
        this.getStuffAPI().getStuffList().get(this.getStuffAPI().getStuffList().indexOf(stuff)).
                notifySubscribers(((Dad)person).readDocumentation(stuff.getDocumentation(),time + 40, stuff));
    }
}
