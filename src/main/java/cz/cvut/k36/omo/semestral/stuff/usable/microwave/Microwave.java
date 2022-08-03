package cz.cvut.k36.omo.semestral.stuff.usable.microwave;

import cz.cvut.k36.omo.semestral.Subscriber;
import cz.cvut.k36.omo.semestral.home.rooms.Room;
import cz.cvut.k36.omo.semestral.inmates.peoples.Person;
import cz.cvut.k36.omo.semestral.report.actions.Action;
import cz.cvut.k36.omo.semestral.report.actions.Use;
import cz.cvut.k36.omo.semestral.stuff.StuffAPI;
import cz.cvut.k36.omo.semestral.stuff.StuffState;
import cz.cvut.k36.omo.semestral.stuff.usable.KitchenStuff;

/**
 * The class contains methods related to the microwave.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, November 2021
 */

public class Microwave implements KitchenStuff {
    //microwave have State machine

    private boolean isOccupied;
    private int quality;
    private final String documentation;
    private String food;
    private StuffState stuffState;
    private double electricityConsumptionDay;
    private Person usingPerson;
    private final Room room;
    private Subscriber sub;
    private final String serialNumber;

    public Microwave(Room room, int serialNumber) {
        this.serialNumber = "№ "+serialNumber;
        this.stuffState = StuffState.WORKING;
        this.room = room;
        this.isOccupied = false;
        this.electricityConsumptionDay = 0;
        this.usingPerson = null;
        this.quality = 120;
        this.documentation = "Starting the microwave: close the microwave tightly, select the mode from the ones " +
                "offered on the panel, select the time by scrolling the wheel on the panel. If the microwave is " +
                "broken, call the master at +420 840 110 122 and follow the instructions of the master";
        this.food = "";
    }

    @Override
    public double getElectricityConsumptionDay() {
        double ret = this.electricityConsumptionDay;
        this.electricityConsumptionDay = 0;
        return ret;
    }

    @Override
    public Room getRoom() {
        return this.room;
    }

    @Override
    public Use use(Person person, int time, StuffAPI stuffAPI) {
        person.setUsing(true);
        person.setUsedStuff(this);
        Use useAction = new Use(time, this.room, person, this, stuffAPI);
        this.electricityConsumptionDay += 0.8;
        if(!this.isOccupied){
            this.usingPerson = person;
            this.isOccupied = true;
        }
        return useAction;
    }

    @Override
    public boolean isUsed() {
        return this.isOccupied;
    }

    @Override
    public void stopUsing(Person person) {
        this.usingPerson = null;
        this.isOccupied = false;
    }

    @Override
    public Person getPerson() {
        return this.usingPerson;
    }

    @Override
    public boolean checkQuality() {
        if(this.quality <= 25){
            this.stuffState = StuffState.BROKEN;
            return false;
        }
        return true;
    }

    @Override
    public void setQuality() {
        this.quality = 120;
    }

    @Override
    public void decreaseQuality() {
        this.quality -=2;
    }

    @Override
    public void changeStuffState(StuffState stuffState) {
        this.stuffState = stuffState;
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
     * Normal getter.
     * @return documentation as String
     */
    public String getDocumentation() {
        return documentation;
    }

    /**
     * Method means that the food was put in the microwave.
     * @param food is what kind of food was put
     */
    public void putFood(String food){
        this.food = food;
    }

    /**
     * Method means that the food was taken from the microwave.
     */
    public String takeFood(){
        String ret = this.food;
        this.food = null;
        return ret;
    }

    /**
     * The method returns a string that will be sent to the report.
     */
    public String toString() {
        return "Stuff type: Microwave; Serial number: "+serialNumber+"; Consumption of day : "+
                electricityConsumptionDay +"; Quality: "+quality+"; Stuff State: "+stuffState+"\n";
    }
}
