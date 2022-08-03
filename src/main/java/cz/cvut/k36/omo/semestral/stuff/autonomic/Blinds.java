package cz.cvut.k36.omo.semestral.stuff.autonomic;

import cz.cvut.k36.omo.semestral.Simulation;
import cz.cvut.k36.omo.semestral.Subscriber;
import cz.cvut.k36.omo.semestral.home.rooms.Room;
import cz.cvut.k36.omo.semestral.report.actions.Action;
import cz.cvut.k36.omo.semestral.report.actions.ChangeSensorState;
import cz.cvut.k36.omo.semestral.stuff.StuffState;

/**
 * The class contains methods related to the blinds.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, November 2021
 */
public class Blinds implements Sensor{
    private final Room room;
    private int quality;
    private StuffState stuffState;
    private double electricityConsumptionDay;
    private boolean isOpened;
    private final String documentation;
    private Subscriber sub;
    private final String serialNumber;

    public Blinds(Room room, int serialNumber){
        this.room = room;
        this.serialNumber = "№ "+serialNumber;
        this.documentation = "If the blinds are broken, call the workshop on +420 776 062 767 and strictly " +
                "follow the instructions of the master.";
        this.quality = 100;
        this.stuffState = StuffState.WORKING;
        this.electricityConsumptionDay = 0;
        this.isOpened = true;
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
        this.quality -= 3;
    }

    @Override
    public void changeStuffState(StuffState stuffState) {
        this.stuffState = stuffState;
    }

    @Override
    public double getWaterConsumptionDay() {
        return 0;
    }

    @Override
    public double getElectricityConsumptionDay() {
        double ret = this.electricityConsumptionDay;
        this.electricityConsumptionDay = 0;
        return ret;
    }

    @Override
    public void measure(Simulation simulation, int time) {
        int curLightLevel = simulation.getCurrentLightLevel();
        changeState(curLightLevel >= 10, curLightLevel,time);
    }

    @Override
    public void changeState(boolean state, double lightLevel, int time) {
        if(state){
            this.electricityConsumptionDay += 0.5;
        }
        if (state != this.isOpened) {
            this.isOpened = state;
            String changeComment = makeChangeComment(lightLevel);
            ChangeSensorState changeState = new ChangeSensorState(time, this.room, this, changeComment);
            this.notifySubscribers(changeState);
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
    public String makeChangeComment(double lightLevel){
        String comment;
        if (this.isOpened){
            comment = "Blinds opened due to current light level: "+ lightLevel;
        }else{
            comment = "Blinds closed due to current light level: "+ lightLevel;
        }
        return comment;
    }

    @Override
    public String getDocumentation() {
        return this.documentation;
    }

    /**
     * Normal getter.
     * @return the room in which the blinds are located
     */
    public Room getRoom() {
        return this.room;
    }

    /**
     * The method returns a string that will be sent to the report.
     */
    public String toString() {
        return "Stuff type: Blinds; Serial number: "+serialNumber + "; Consumption of day: "+
                electricityConsumptionDay +"; Quality: "+quality+"; Stuff State: "+stuffState+"\n";
    }
}
