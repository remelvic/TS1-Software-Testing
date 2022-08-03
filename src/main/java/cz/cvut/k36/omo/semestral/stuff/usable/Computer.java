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
 * The class contains methods related to the computer.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, November 2021
 */
public class Computer implements Switchable{
    private boolean isOccupied;
    private int quality;
    private final String documentation;
    private StuffState stuffState;
    private Person user;
    private double electricityConsumptionDay;
    private final Room room;
    private Subscriber sub;
    private final String serialNumber;

    public Computer(Room room, int serialNumber){
        this.serialNumber = "№ "+serialNumber;
        this.quality = 85;
        this.isOccupied = false;
        this.electricityConsumptionDay = 0;
        this.documentation = "Start your computer: click on the start button, enter your login: admin and password: " +
                "admin and enjoy. In case of malfunction, restart your computer. If nothing has changed, call the " +
                "master at the number: +420 224 26 28 26 and follow the instructions of the master. Do NOT try to fix" +
                " it yourself.";
        this.stuffState = StuffState.WORKING;
        this.user = null;
        this.room = room;
    }

    @Override
    public Use use(Person person, int time, StuffAPI stuffAPI) {
        Use useAction = null;
        if (!(person instanceof Baby)) {
            this.decreaseQuality();
            person.setUsing(true);
            person.setUsedStuff(this);
            if (!this.isOccupied) {
                this.isOccupied = true;
                this.user = person;
                useAction = new Use(time, this.room, person, this, stuffAPI);
            }
        }
        return useAction;
    }

    @Override
    public boolean isUsed() {
        return this.isOccupied;
    }

    @Override
    public void stopUsing(Person person) {
        this.isOccupied = false;
        this.user = null;
        this.electricityConsumptionDay += 0.5;
    }

    @Override
    public Person getPerson() {
        return this.user;
    }

    @Override
    public boolean checkQuality() {
        if (this.quality <= 25){
            this.stuffState = StuffState.BROKEN;
            return false;
        }
        return true;
    }

    @Override
    public void setQuality() {
        this.quality = 85;
    }

    @Override
    public void decreaseQuality() {
        this.quality -= 8;
    }

    @Override
    public void changeStuffState(StuffState stuffState) {
        this.stuffState = stuffState;
    }

    @Override
    public double getElectricityConsumptionDay() {
        double ret = this.electricityConsumptionDay;
        this.electricityConsumptionDay = 0;
        return ret;
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
    public String getDocumentation(){
        return documentation;
    }

    /**
     * The method returns a string that will be sent to the report.
     */
    public String toString() {
        return "Stuff type: Computer; Serial number: "+serialNumber+"; Consumption of day: "+
                electricityConsumptionDay +"; Quality: "+quality+"; " + "Stuff State: "+stuffState+"\n";
    }
}
