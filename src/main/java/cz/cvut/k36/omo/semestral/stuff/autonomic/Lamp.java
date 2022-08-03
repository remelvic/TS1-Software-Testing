package cz.cvut.k36.omo.semestral.stuff.autonomic;

import cz.cvut.k36.omo.semestral.Simulation;
import cz.cvut.k36.omo.semestral.Subscriber;
import cz.cvut.k36.omo.semestral.home.rooms.Room;
import cz.cvut.k36.omo.semestral.report.actions.Action;
import cz.cvut.k36.omo.semestral.report.actions.ChangeSensorState;
import cz.cvut.k36.omo.semestral.stuff.StuffState;

/**
 * The class contains methods related to the lamp.  Also, the lamp is
 * illuminated by sensors with the help of which the light comes on
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, November 2021
 */
public class Lamp implements Sensor{
    private final Room room;
    private int quality;
    private StuffState stuffState;
    private double electricityConsumptionDay;
    private boolean isLightning;
    private Subscriber sub;
    private final String documentation;
    private final String serialNumber;

    public Lamp(Room room, int serialNumber){
        this.serialNumber = "№ "+serialNumber;
        this.room = room;
        this.quality = 150;
        this.stuffState = StuffState.WORKING;
        this.isLightning = false;
        this.electricityConsumptionDay = 0;
        this.documentation = "If the lamp breaks. Turn off the light, change the light bulb. If nothing has " +
                "changed, then call the workshop at +420 713 830 601 and strictly follow the instructions of the " +
                "wizard. Never try to fix it yourself.";
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
        this.quality = 150;
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
        int hour = time / 60 % 24;
        this.changeState(curLightLevel < 30 && hour >= 8, curLightLevel, time);
    }

    @Override
    public void changeState(boolean state, double lightLevel, int time) {
        if (state){
            this.electricityConsumptionDay += 0.2;
        }
        if (state != this.isLightning) {
            String changeComment = makeChangeComment(lightLevel);
            ChangeSensorState changeStateAction = new ChangeSensorState(time, this.room, this, changeComment);
            this.isLightning = state;
            this.notifySubscribers(changeStateAction);
        }
    }

    @Override
    public String makeChangeComment(double lightLevel) {
        String comment;
        if (this.isLightning){
            comment = "The lamp is on due to current light level: " +  lightLevel+ "%";
        }else{
            comment = "The lamp is off due to current light level: " + lightLevel + "%";
        }
        return comment;
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
     * @return the room in which the lamp are located
     */
    public Room getRoom() {
        return this.room;
    }

    /**
     * The method returns a string that will be sent to the report.
     */
    public String toString() {
        return "Stuff type: Lamp; Serial number: "+serialNumber+"; Consumption of day: "+
                electricityConsumptionDay +"; Quality: "+quality+"; Stuff State: "+stuffState+"\n";
    }
}
