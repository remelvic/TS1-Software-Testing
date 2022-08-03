package cz.cvut.k36.omo.semestral.stuff.usable;

/**
 * The interface contains methods that will be used by all classes that relate to the KitchenStuff interface.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, November 2021
 */
public interface KitchenStuff extends Usable{

    /**
     *  When calling the methods, put food in the microwave or refrigerator.
     * @param food put in the microwave or refrigerator
     */
    void putFood(String food) ;

    /**
     * When calling the methods, take food in the microwave or refrigerator.
     */
    String takeFood();

    /**
     * Normal getter.
     * @return electricity consumption of day as double type
     */
    double getElectricityConsumptionDay();

}
