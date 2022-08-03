package cz.cvut.k36.omo.semestral.report.actions;

import cz.cvut.k36.omo.semestral.home.rooms.Room;
import cz.cvut.k36.omo.semestral.stuff.autonomic.Sensor;

/**
 * The class creates an action when the state of the sensors changes.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, December 2021
 */
public class ChangeSensorState extends Action{
    private final Sensor sensor;
    private final String comment;

    public ChangeSensorState(int time, Room room, Sensor sensor, String comment) {
        super(ActionType.CHANGE_SENSOR_STATE, time, room);
        this.sensor = sensor;
        this.comment = comment;
    }

    /**
     * Normal getter.
     * @return sensor as Sensor type
     */
    public Sensor getSensor() {
        return sensor;
    }

    /**
     * Normal getter.
     * @return comment as String type
     */
    public String getComment(){
        return this.comment;
    }
}
