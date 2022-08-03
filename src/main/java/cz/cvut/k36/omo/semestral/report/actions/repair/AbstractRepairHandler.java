package cz.cvut.k36.omo.semestral.report.actions.repair;

import cz.cvut.k36.omo.semestral.inmates.peoples.Person;
import cz.cvut.k36.omo.semestral.stuff.Stuff;
import cz.cvut.k36.omo.semestral.stuff.StuffAPI;

/**
 * An abstract class is needed to use the chain of responsibility design pattern.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, January 2022
 */
public abstract class AbstractRepairHandler {
    public static int FIND_DOC = 1;
    public static int READ_DOC = 2;
    public static int CALL_SERVICE = 3;
    public static int REPAIR = 4;
    private static StuffAPI stuffAPI;
    protected int level;
    protected AbstractRepairHandler nextHandler;

    /**
     * Normal setter.
     */
    public static void setStuffAPI(StuffAPI stuffAPI){
        AbstractRepairHandler.stuffAPI = stuffAPI;
    }

    /**
     * Normal setter.
     */
    public void setNextHandler(AbstractRepairHandler nextHandler){
        this.nextHandler = nextHandler;
    }

    /**
     * The recursive method calls the handle method in order, which will carry out the action we need.
     * @param stuff the stuff parameter we interact with
     * @param level is needed to determine which step we are in at the moment
     * @param person is the one who will carry out the action at this level
     * @param time is when any action occurs
     */
    public void repair(Stuff stuff, int level, Person person, int time){
        if (this.level >= level){
            handle(stuff, person, time);
        }
        if (nextHandler != null){
            nextHandler.repair(stuff, level, person, time);
        }
    }

    /**
     * An abstract method, depending on the level, calls different actions from the chain of responsibilities.
     * @param stuff the stuff parameter we interact with
     * @param person is the one who will carry out the action at this level
     * @param time is when any action occurs
     */
    abstract protected void handle(Stuff stuff, Person person , int time);

    /**
     * Normal getter.
     * @return stuffAPI as StuffAPI type
     */
    public StuffAPI getStuffAPI(){
        return stuffAPI;
    }
}
