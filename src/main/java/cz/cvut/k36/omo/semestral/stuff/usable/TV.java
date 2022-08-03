package cz.cvut.k36.omo.semestral.stuff.usable;

import cz.cvut.k36.omo.semestral.Subscriber;
import cz.cvut.k36.omo.semestral.home.rooms.Room;
import cz.cvut.k36.omo.semestral.inmates.peoples.Person;
import cz.cvut.k36.omo.semestral.report.actions.Action;
import cz.cvut.k36.omo.semestral.report.actions.Use;
import cz.cvut.k36.omo.semestral.stuff.StuffAPI;
import cz.cvut.k36.omo.semestral.stuff.StuffState;

import java.util.ArrayList;

/**
 * The class contains methods related to the TV.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, November 2021
 */
public class TV implements Switchable{
    private boolean isOccupied;
    private final int personCapacity = 3;
    private int quality;
    private final String documentation;
    private final ArrayList<Person> viewers;
    private StuffState stuffState;
    private double electricityConsumptionDay;
    private final Room room;
    private Subscriber sub;
    private final String serialNumber;

    public TV(Room room, int serialNumber){
        this.serialNumber = "№ "+serialNumber;
        this.quality = 100;
        this.isOccupied = false;
        this.electricityConsumptionDay = 0;
        this.documentation = "Turn on the TV: Take the remote control, press the red start button. To fix the TV: " +
                "unplug the wire from the socket, insert it back and turn it on again. If it does not help, call the " +
                "workshop on +420 603 437 378, and follow the instructions of the master";
        this.stuffState = StuffState.WORKING;
        this.viewers = new ArrayList<>();
        this.room = room;
    }

    @Override
    public Use use(Person person, int time, StuffAPI stuffAPI) {
        Use useAction = null;
        if(!this.isOccupied){
            this.decreaseQuality();
            person.setUsing(true);
            person.setUsedStuff(this);
            useAction = new Use(time, this.room, person, this, stuffAPI);
            if(viewers.size() != personCapacity) {
                this.viewers.add(person);
            }else{
                this.isOccupied = true;
            }
        }
        return useAction;
    }

    @Override
    public boolean isUsed() {
        return !this.viewers.isEmpty();
    }

    @Override
    public void stopUsing(Person person) {
        this.viewers.remove(person);
        if (viewers.size() < this.personCapacity){
            this.isOccupied = false;
        }
        if (viewers.isEmpty()){
            this.electricityConsumptionDay += 1.5;
        }
    }

    @Override
    public Person getPerson() {
        return this.viewers.get(0);
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
        this.quality = 100;
    }

    @Override
    public void decreaseQuality() {
        this.quality -= 10;
    }

    @Override
    public void changeStuffState(StuffState stuffState) {
        if (stuffState == StuffState.WORKING){
            this.quality = 100;
        }
        this.stuffState = stuffState;
    }

    @Override
    public double getElectricityConsumptionDay() {
        double ret = this.electricityConsumptionDay ;
        this.electricityConsumptionDay = 0;
        return ret;
    }

    @Override
    public Room getRoom() {
        return this.room;
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

    @Override
    public String getDocumentation(){
        return documentation;
    }

    /**
     * The method returns a string that will be sent to the report.
     */
    public String toString() {
        return "Stuff type: TV; Serial number: "+serialNumber+"; Consumption: "+
                electricityConsumptionDay +"; Quality: "+quality+"; Stuff State: "+stuffState+"\n";
    }
}
