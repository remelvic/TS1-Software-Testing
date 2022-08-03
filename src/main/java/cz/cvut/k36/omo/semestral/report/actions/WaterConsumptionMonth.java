package cz.cvut.k36.omo.semestral.report.actions;

import cz.cvut.k36.omo.semestral.home.rooms.Room;

/**
 * The class is created when the action of calculating the consumed water for the month is called.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, December 2021
 */
public class WaterConsumptionMonth extends Action{
    private final double waterConsumptionMonth;

    public WaterConsumptionMonth(int time, Room room, double waterConsumptionMonth) {
        super(ActionType.WATER_CONSUMPTION_MONTH, time, room);
        this.waterConsumptionMonth = waterConsumptionMonth;
    }

    /**
     * Normal getter.
     * @return the amount of water consumed per month
     */
    public double getWaterConsumptionMonth() {
        return waterConsumptionMonth;
    }
}
