package cz.cvut.k36.omo.semestral;

import cz.cvut.k36.omo.semestral.report.ReportAPI;
import cz.cvut.k36.omo.semestral.report.ReportBuilder;
import cz.cvut.k36.omo.semestral.report.actions.*;
import cz.cvut.k36.omo.semestral.report.actions.repair.*;

/**
 * The class is required to update the report, the observer design pattern is applied.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, December 2021
 */
public class ActionSubscriber implements Subscriber{
    private final ReportAPI reportAPI;

    public ActionSubscriber(ReportAPI reportAPI){
        this.reportAPI = reportAPI;
    }

    @Override
    public void update(Action action) {
        switch (action.getActionType()) {
            case REPAIR -> ReportBuilder.addReportToList(this.reportAPI.createRepairReport((Repair) action));
            case COOK -> ReportBuilder.addReportToList(this.reportAPI.createCookReport((Cook) action));
            case EAT -> ReportBuilder.addReportToList(this.reportAPI.createEatReport((Eat) action));
            case SLEEP -> ReportBuilder.addReportToList(this.reportAPI.createSleepReport((Sleep) action));
            case WATER_CONSUMPTION -> ReportBuilder.addReportToList(this.reportAPI.
                    createStuffWaterConsumptionReportDay((WaterConsumptionDay) action));
            case ELECTRICITY_CONSUMPTION -> ReportBuilder.addReportToList(this.reportAPI.
                    createStuffElectricConsumptionReportDay((ElectricityConsumptionDay) action));
            case ELECTRICITY_CONSUMPTION_MONTH -> ReportBuilder.addReportToList(this.reportAPI.
                    createStuffElectricConsumptionReportMonth((ElectricityConsumptionMonth) action));
            case WATER_CONSUMPTION_MONTH -> ReportBuilder.addReportToList(this.reportAPI.
                    createStuffWaterConsumptionReportMonth((WaterConsumptionMonth) action));
            case FEED_ANIMAL -> ReportBuilder.addReportToList(this.reportAPI.createFeedAnimalReport((FeedAnimal) action));
            case FEED_BABY -> ReportBuilder.addReportToList(this.reportAPI.createFeedBabyReport((FeedBaby) action));
            case BROKE -> ReportBuilder.addReportToList(this.reportAPI.createBrokeReport((Broke) action));
            case WAKE_UP -> ReportBuilder.addReportToList(this.reportAPI.createWakeUpReport((WakeUp) action));
            case USE -> ReportBuilder.addReportToList(this.reportAPI.createUseReport((Use) action));
            case PUT_FOOD -> ReportBuilder.addReportToList(this.reportAPI.createPutFoodReport((PutFood) action));
            case TAKE_FOOD -> ReportBuilder.addReportToList(this.reportAPI.createTakeFoodReport((TakeFood) action));
            case CHANGE_SENSOR_STATE -> ReportBuilder.addReportToList(this.reportAPI.
                    createChangeSensorStateReport((ChangeSensorState) action));
            case READ_DOC -> ReportBuilder.addReportToList(this.reportAPI.createReadDocReport((ReadDoc) action));
            case FIND_DOC -> ReportBuilder.addReportToList(this.reportAPI.createFindDocReport((FindDoc) action));
            case CALL_SERVICE -> ReportBuilder.addReportToList(this.reportAPI.createCallServiceReport((CallService) action));
            default -> {
            }
        }
    }
}
