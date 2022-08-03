package cz.cvut.k36.omo.semestral.report.actions;

import cz.cvut.k36.omo.semestral.home.rooms.Room;

/**
 * The class is created when the action of calculating the consumed electricity for the month is called.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, December 2021
 */
public class ElectricityConsumptionMonth extends Action{
    private final double electricityConsumptionMonth;

    public ElectricityConsumptionMonth(int time, Room room, double electricityConsumptionMonth) {
        super(ActionType.ELECTRICITY_CONSUMPTION_MONTH, time, room);
        this.electricityConsumptionMonth = electricityConsumptionMonth;
    }

    /**
     * Normal getter.
     * @return amount of consumed electricity for a month
     */
    public double getElectricityConsumptionMonth() {
        return electricityConsumptionMonth;
    }
}
