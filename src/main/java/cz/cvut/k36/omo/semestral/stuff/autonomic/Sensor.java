package cz.cvut.k36.omo.semestral.stuff.autonomic;

import cz.cvut.k36.omo.semestral.Simulation;
import cz.cvut.k36.omo.semestral.stuff.Stuff;

/**
 * The interface contains methods that will be used by all sensors in the house.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, November 2021
 */
public interface Sensor extends Stuff {

    /**
     * Normal getter.
     * @return water consumption of day as double type
     */
    double getWaterConsumptionDay();

    /**
     * Normal getter.
     * @return electricity consumption of day as double type
     */
    double getElectricityConsumptionDay();

    /**
     * The method measures the amount of light or the amount of humidity depending on the type of sensor. For example,
     * if there is a lot of light in the room the method will turn off all the lamps.
     */
    void measure(Simulation simulation, int time);

    /**
     * The method changes the state of the sensor.
     * @param state is true if the sensor is on or false if it is off
     * @param data is either temperature or humidity or light, all depending on the type of sensor
     * @param time is just time in minutes
     */
    void changeState(boolean state, double data, int time);

    /**
     * The method creates a comment when the state of the sensor changes.
     * @param data is either temperature or humidity or light, all depending on the type of sensor
     * @return the comment as a string
     */
    String makeChangeComment(double data);
}
