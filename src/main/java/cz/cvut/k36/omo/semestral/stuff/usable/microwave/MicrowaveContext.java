package cz.cvut.k36.omo.semestral.stuff.usable.microwave;

import cz.cvut.k36.omo.semestral.inmates.peoples.Person;
import cz.cvut.k36.omo.semestral.report.actions.Use;
import cz.cvut.k36.omo.semestral.stuff.StuffAPI;

/**
 * The method is needed to implement the State Machine pattern.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, December 2021
 */
public class MicrowaveContext {
    Microwave microwave;
    private MicrowaveState state;

    /**
     * Normal getter.
     */
    public Microwave getMicrowave() {
        return this.microwave;
    }

    /**
     * The method will set the status variable to its original status.
     */
    public MicrowaveContext(){
        this.state = new ReadyToCookState();
    }

    /**
     * Normal setter.
     */
    public void setMicrowave(Microwave microwave){
        this.microwave = microwave;
    }

    /**
     * Normal setter.
     */
    public void setState(MicrowaveState state){
        this.state = state;
    }

    /**
     * The method determines whether a microwave is being used.
     */
    public boolean isUsed(){
        return state.isUsed(this);
    }

    /**
     * Method means that the food was put in the microwave.
     * @param food is what kind of food was put
     */
    public void putFood(String food){
        state.putFood(this, food);
    }

    /**
     * Method means that the food was taken from the microwave.
     */
    public void takeFood(){
        state.takeFood(this);
    }

    /**
     * The method creates human interaction with the microwave.
     * @param person the one who interacts with the microwave
     * @param time this is what time the interaction began
     * @return us an action of type Use
     */
    public Use use(Person person, int time, StuffAPI stuffAPI){
        return state.use(this, person, time, stuffAPI);
    }

    /**
     * Normal getter.
     */
    public MicrowaveState getState() {
        return state;
    }

    /**
     * The method stops using the microwave.
     * @param person is the one who will end the interaction.
     */
    public void stopUsing(Person person){
        state.stopUsing(this, person);
    }
}
