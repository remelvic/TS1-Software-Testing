package cz.cvut.k36.omo.semestral.report.actions;

import cz.cvut.k36.omo.semestral.home.rooms.Room;

/**
 * The class creates an action when we want to measure the amount of electricity used.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, December 2021
 */
public class ElectricityConsumptionDay extends Action{
    private final double electricityConsumptionDay;

    public ElectricityConsumptionDay(int time, Room room, double electricityConsumptionDay) {
        super(ActionType.ELECTRICITY_CONSUMPTION, time, room);
        this.electricityConsumptionDay = electricityConsumptionDay;
    }

    /**
     * Normal getter.
     * @return the amount of electricity consumed per day
     */
    public double getElectricityConsumptionDay() {
        return electricityConsumptionDay;
    }
}
