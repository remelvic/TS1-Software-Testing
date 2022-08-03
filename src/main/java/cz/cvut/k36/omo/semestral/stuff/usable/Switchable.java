package cz.cvut.k36.omo.semestral.stuff.usable;

/**
 * The interface contains methods for obtaining consumed electricity and water, devices that turn on and off.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, December 2021
 */
public interface Switchable extends Usable {

    /**
     * Normal getter.
     * @return electricity consumption of day as double type
     */
    double getElectricityConsumptionDay();
}
