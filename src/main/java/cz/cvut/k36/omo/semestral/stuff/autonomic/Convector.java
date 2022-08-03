package cz.cvut.k36.omo.semestral.stuff.autonomic;

import cz.cvut.k36.omo.semestral.Simulation;
import cz.cvut.k36.omo.semestral.Subscriber;
import cz.cvut.k36.omo.semestral.home.rooms.Room;
import cz.cvut.k36.omo.semestral.report.actions.Action;
import cz.cvut.k36.omo.semestral.report.actions.ChangeSensorState;
import cz.cvut.k36.omo.semestral.stuff.StuffState;

/**
 * The class contains methods related to the convector.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, November 2021
 */
public class Convector implements Sensor{
    private final Room room;
    private int quality;
    private StuffState stuffState;
    private double electricityConsumptionDay;
    private double waterConsumptionDay;
    private boolean isHeating;
    private Subscriber sub;
    private final String documentation;
    private final String serialNumber;

    public Convector(Room room,int serialNumber){
        this.serialNumber = "№ " + serialNumber;
        this.room = room;
        this.quality = 100;
        this.stuffState = StuffState.WORKING;
        this.waterConsumptionDay = 0;
        this.electricityConsumptionDay = 0;
        this.isHeating = false;
        this.documentation = "If the battery breaks down, call the workshop at +420 602 941 590 and strictly follow " +
                "the instructions of the wizard. Never try to fix it yourself.";
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
        this.quality = 100;
    }

    @Override
    public void decreaseQuality() {
        this.quality--;
    }

    @Override
    public void changeStuffState(StuffState stuffState) {
        this.stuffState = stuffState;
    }

    @Override
    public double getWaterConsumptionDay() {
        double ret = this.waterConsumptionDay;
        this.waterConsumptionDay = 0;
        return ret;
    }

    @Override
    public double getElectricityConsumptionDay() {
        double ret = this.electricityConsumptionDay;
        this.electricityConsumptionDay = 0;
        return ret;
    }

    @Override
    public void measure(Simulation simulation, int time) {
        double currentTemperature = simulation.getCurrentTemperature();
        changeState(currentTemperature < 15, currentTemperature, time);
    }

    @Override
    public String makeChangeComment(double temperature) {
        String comment;
        if (this.isHeating){
            comment = "Convector heats up due to current temperature: "+ temperature + "C°";
        }else{
            comment = "Convector does not heat due to current temperature: "+ temperature + "C°";
        }
        return comment;
    }

    @Override
    public void changeState(boolean state, double temperature, int time) {
        if (state){
            this.electricityConsumptionDay+= 0.7;
            this.waterConsumptionDay+= 0.7;
        }
        if (state != this.isHeating) {
            this.isHeating = state;
            String changeComment = this.makeChangeComment(temperature);
            ChangeSensorState changeStateAction = new ChangeSensorState(time, this.room, this, changeComment);
            this.notifySubscribers(changeStateAction);
        }
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
    public String getDocumentation() {
        return this.documentation;
    }

    /**
     * Normal getter.
     * @return the room in which the Convector are located
     */
    public Room getRoom() {
        return this.room;
    }

    /**
     * The method returns a string that will be sent to the report.
     */
    public String toString() {
        return "Stuff type: Convector; Serial number: "+serialNumber + "; Consumption of day: "+
                electricityConsumptionDay +"; Quality: "+quality+"; Stuff State: "+stuffState+"\n";
    }
}
