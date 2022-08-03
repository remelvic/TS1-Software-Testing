package cz.cvut.k36.omo.semestral.stuff.autonomic;

import cz.cvut.k36.omo.semestral.Simulation;
import cz.cvut.k36.omo.semestral.Subscriber;
import cz.cvut.k36.omo.semestral.home.rooms.Room;
import cz.cvut.k36.omo.semestral.report.actions.Action;
import cz.cvut.k36.omo.semestral.report.actions.ChangeSensorState;
import cz.cvut.k36.omo.semestral.stuff.StuffState;

/**
 * The class contains methods related to the humidifier. The humidifier is equipped with humidity sensors in the house
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, November 2021
 */
public class Humidifier implements Sensor{
    private final Room room;
    private int quality;
    private StuffState stuffState;
    private double electricityConsumptionDay;
    private double waterConsumptionDay;
    private boolean isMoisturizes;
    private Subscriber sub;
    private final String documentation;
    private final String serialNumber;

    public Humidifier(Room room, int serialNumber){
        this.serialNumber = "№ "+serialNumber;
        this.room = room;
        this.quality = 100;
        this.stuffState = StuffState.WORKING;
        this.waterConsumptionDay = 0;
        this.electricityConsumptionDay = 0;
        this.isMoisturizes = false;
        this.documentation = "If the humidifier breaks down, call the workshop on +420 713 830 601 and strictly " +
                "follow the instructions of the master. Never try to fix it yourself.";
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
        this.quality -= 2 ;
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
        int curHumidityLevel = simulation.getCurrentHumidity();
        this.changeState(curHumidityLevel < 75, curHumidityLevel, time);
    }

    @Override
    public String makeChangeComment(double humidity) {
        String comment;
        if (this.isMoisturizes){
            comment = "Humidifier moisturizes due to current humidity: " + humidity + "%";
        }else{
            comment = "Humidifier does not moisturize  due to current humidity: " + humidity + "%";
        }
        return comment;
    }

    @Override
    public void changeState(boolean state, double humidity, int time) {
        if(state){
            this.electricityConsumptionDay+= 0.7;
            this.waterConsumptionDay += 0.7;
        }
        if (state != this.isMoisturizes) {
            this.isMoisturizes = state;
            String changeComment = makeChangeComment(humidity);
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
     * @return will return the room in which the humidifier are located
     */
    public Room getRoom() {
        return this.room;
    }

    /**
     * The method returns a string that will be sent to the report.
     */
    public String toString() {
        return "Stuff type: Humidifier; Serial Number: " +serialNumber+ "; Consumption of day: "+
                electricityConsumptionDay +"; Quality: "+quality+"; Stuff State: "+stuffState+"\n";
    }
}
