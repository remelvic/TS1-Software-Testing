package cz.cvut.k36.omo.semestral.stuff.usable;

import cz.cvut.k36.omo.semestral.Subscriber;
import cz.cvut.k36.omo.semestral.home.rooms.Room;
import cz.cvut.k36.omo.semestral.inmates.peoples.Baby;
import cz.cvut.k36.omo.semestral.inmates.peoples.Child;
import cz.cvut.k36.omo.semestral.inmates.peoples.Person;
import cz.cvut.k36.omo.semestral.report.actions.Action;
import cz.cvut.k36.omo.semestral.report.actions.Use;
import cz.cvut.k36.omo.semestral.stuff.StuffAPI;
import cz.cvut.k36.omo.semestral.stuff.StuffState;

import java.util.ArrayList;

/**
 * The class contains methods related to the auto.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, November 2021
 */
public class Car implements Vehicle{
    private final int capacity = 4;
    private Person driver;
    private final ArrayList<Person> personsInCar;
    private int quality;
    private final String documentation;
    private boolean isUsed;
    private StuffState stuffState;
    private final Room room;
    private Subscriber sub;
    private final String serialNumber;

    public Car(Room room, int serialNumber){
        this.serialNumber = "№ "+serialNumber;
        this.driver = null;
        this.personsInCar = new ArrayList<>();
        this.quality = 110;
        this.documentation = "In case of breakdown of your car, call +420 271 077 111. Do not forget to change the " +
                "oil, select the correct gasoline and undergo an inspection once a year.";
        this.isUsed = false;
        this.stuffState = StuffState.WORKING;
        this.room = room;
    }

    @Override
    public Use use(Person person, int time, StuffAPI stuffAPI) {
        Use useAction = null;
        this.decreaseQuality();
        if(this.personsInCar.size() <= this.capacity) {
            useVehicle(person);
            person.setUsing(true);
            useAction = new Use(time,person.getCurrentRoom(),person, this, stuffAPI);
        }
        return useAction;
    }

    @Override
    public boolean isUsed() {
        this.isUsed = !personsInCar.isEmpty();
        return this.isUsed;
    }

    @Override
    public void stopUsing(Person person) {
        leaveVehicle(person);
    }

    @Override
    public Person getPerson() {
        return this.personsInCar.get(0);
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
        this.quality = 110;
    }

    @Override
    public void decreaseQuality() {
        this.quality -= 10;
    }

    @Override
    public void changeStuffState(StuffState stuffState) {
        this.stuffState = stuffState;
    }

    @Override
    public void useVehicle(Person person) {
        if (this.personsInCar.size() <= this.capacity) {
            if ((!(person instanceof Baby) && !(person instanceof Child)) || this.driver == null) {
                if (this.driver == null) {
                    this.driver = person;
                }
            }
            this.personsInCar.add(person);
            person.setUsedStuff(this);
            person.setUsing(true);
        }
    }

    @Override
    public void leaveVehicle(Person person) {
        if (this.personsInCar.size() == 0){
            return;
        }
        if (this.driver == person){
            while(personsInCar.size() != 0) {
                this.personsInCar.remove(0);
            }
        }
        this.personsInCar.remove(person);
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
        return documentation;
    }

    /**
     * The method returns a string that will be sent to the report.
     */
    public String toString() {
        return "Stuff type: Car; Serial number: "+serialNumber+"; Quality: "+quality+"; Stuff State: "+stuffState+"\n";
    }
}
