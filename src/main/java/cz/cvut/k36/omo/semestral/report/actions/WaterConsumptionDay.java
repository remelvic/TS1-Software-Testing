package cz.cvut.k36.omo.semestral.report.actions;

import cz.cvut.k36.omo.semestral.home.rooms.Room;

/**
 * The class creates an action when we want to measure the amount of water spent.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, December 2021
 */
public class WaterConsumptionDay extends Action{
    private final double waterConsumptionDay;

    public WaterConsumptionDay(int time, Room room, double waterConsumptionDay) {
        super(ActionType.WATER_CONSUMPTION, time, room);
        this.waterConsumptionDay = waterConsumptionDay;
    }

    /**
     * Normal getter.
     * @return the amount of water consumed per day
     */
    public double getWaterConsumptionDay() {
        return waterConsumptionDay;
    }
}
