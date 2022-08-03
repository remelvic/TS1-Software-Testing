package cz.cvut.k36.omo.semestral.stuff.usable;

import cz.cvut.k36.omo.semestral.Subscriber;
import cz.cvut.k36.omo.semestral.home.rooms.Room;
import cz.cvut.k36.omo.semestral.inmates.peoples.Baby;
import cz.cvut.k36.omo.semestral.inmates.peoples.Person;
import cz.cvut.k36.omo.semestral.report.actions.Action;
import cz.cvut.k36.omo.semestral.report.actions.Use;
import cz.cvut.k36.omo.semestral.stuff.StuffAPI;
import cz.cvut.k36.omo.semestral.stuff.StuffState;

/**
 * The class contains methods related to the ski.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, November 2021
 */
public class Ski implements Vehicle{
    private int quality;
    private Person skier;
    private StuffState stuffState;
    private boolean isUsed;
    private final Room room;
    private Subscriber sub;
    private final String documentation;
    private final String serialNumber;

    public Ski(Room room, int serialNumber){
        this.serialNumber = "№ "+serialNumber;
        this.isUsed = false;
        this.skier = null;
        this.quality = 100;
        this.stuffState = StuffState.WORKING;
        this.room = room;
        this.documentation = "If your skis are broken, buy new ones. If the skis do not run well, just lubricate them.";
    }

    @Override
    public Use use(Person person, int time, StuffAPI stuffAPI) {
        Use useAction = null;
        this.decreaseQuality();
        if(!(person instanceof Baby)){
            person.setUsedStuff(this);
            useVehicle(person);
            useAction = new Use(time,this.room,person,this, stuffAPI);
        }
        return useAction;
    }

    @Override
    public boolean isUsed() {
        if(skier != null){
            this.isUsed = true;
        }
        return this.isUsed;
    }

    @Override
    public void stopUsing(Person person) {
        leaveVehicle(person);
    }

    @Override
    public Person getPerson() {
        return this.skier;
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
        this.quality -= 25;
    }

    @Override
    public void changeStuffState(StuffState stuffState) {
        this.stuffState = stuffState;
    }

    @Override
    public void useVehicle(Person person) {
        this.skier = person;
        this.skier.setUsing(true);
        this.isUsed = true;
    }

    @Override
    public void leaveVehicle(Person person) {
        this.skier = null;
        this.isUsed = false;
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
    public Room getRoom() {
        return this.room;
    }

    @Override
    public String getDocumentation() {
        return this.documentation;
    }

    /**
     * The method returns a string that will be sent to the report.
     */
    public String toString() {
        return "Stuff type: Ski; Serial number: "+serialNumber+"; Quality: "+quality+"; Stuff State: "+stuffState+"\n";
    }
}
