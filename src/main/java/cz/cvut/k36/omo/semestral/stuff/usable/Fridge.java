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
 * The class contains methods related to the fridge.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, November 2021
 */
public class Fridge implements KitchenStuff{

    private int quality;
    private final String documentation;
    private boolean isOccupied;
    private StuffState stuffState;
    private final ArrayList<String> foodInFridge;
    private double electricityConsumptionDay;
    private Person usingPerson;
    private final Room room;
    private Subscriber sub;
    private final String serialNumber;

    public Fridge(Room room,int serialNumber){
        this.serialNumber = "№ "+serialNumber;
        this.quality = 100;
        this.isOccupied = false;
        this.usingPerson = null;
        this.documentation = "Plug the refrigerator into a power outlet, select the temperature and enjoy. In the " +
                "event of a breakdown, call the foreman at the number: +420 261 302 111. Do not forget to wash the" +
                " refrigerator every few months";
        this.foodInFridge = new ArrayList<>();
        this.stuffState = StuffState.WORKING;
        this.electricityConsumptionDay = 0;
        this.room = room;
    }

    @Override
    public Use use(Person person, int time, StuffAPI stuffAPI) {
        Use useAction;
        person.setUsing(true);
        person.setUsedStuff(this);
        this.isOccupied = true;
        this.usingPerson = person;
        useAction = new Use(time, this.room, person, this, stuffAPI);
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
        this.quality = 100;
    }

    @Override
    public void decreaseQuality() {
        this.quality -= 4;
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
    public void putFood(String food) {
        foodInFridge.add(food);
    }

    @Override
    public String takeFood() {
        String food = foodInFridge.get(0);
        foodInFridge.remove(0);
        return food;
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
        return this.documentation;
    }

    /**
     * The method checks if the refrigerator is empty.
     * @return true if the refrigerator is empty otherwise it will return a false
     */
    public boolean isEmpty(){
        return this.foodInFridge.isEmpty();
    }

    /**
     * The method returns a string that will be sent to the report.
     */
    public String toString() {
        return "Stuff type: Fridge; Serial number: "+serialNumber+"; Consumption of day: "+
                electricityConsumptionDay +"; Quality: "+quality+"; Stuff State: "+stuffState+"\n";
    }

    /**
     * The method updates the amount of electricity spent.
     */
    public void updateFridgeConsumption(){
        this.electricityConsumptionDay += 3;
    }
}
