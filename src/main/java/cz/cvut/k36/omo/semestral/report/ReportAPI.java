package cz.cvut.k36.omo.semestral.report;

import cz.cvut.k36.omo.semestral.inmates.animals.Pet;
import cz.cvut.k36.omo.semestral.inmates.peoples.Person;
import cz.cvut.k36.omo.semestral.report.actions.*;
import cz.cvut.k36.omo.semestral.report.actions.repair.CallService;
import cz.cvut.k36.omo.semestral.report.actions.repair.FindDoc;
import cz.cvut.k36.omo.semestral.report.actions.repair.ReadDoc;
import cz.cvut.k36.omo.semestral.report.actions.repair.Repair;
import cz.cvut.k36.omo.semestral.stuff.Stuff;

import java.io.*;
import java.util.ArrayList;

import static cz.cvut.k36.omo.semestral.report.actions.ActionType.*;

/**
 * The class will generate reports and write everything to one file.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, December 2021
 */
public class ReportAPI {
    private File reportFile;
    private final String path;

    public ReportAPI(String path){
        this.reportFile = new File(path);
        this.path = path;
    }

    /**
     * The method will add a report to the file.
     */
    public void addReportToFile() {
        try {
            reportFile = new File(path);
            FileWriter fw = new FileWriter(reportFile);
            for (int j = 0; j < ReportBuilder.getArray().size(); j++) {
                fw.write(ReportBuilder.getArray().get(j).toString()+"\n");
            }
            fw.close();

        }catch (IOException e) {
            System.err.println("ERROR IN REPORT FILE");
        }
    }

    /**
     * The method creates a report if something breaks.
     * @param action is a break action
     * @return Returns a finished report
     */
    public Report createBrokeReport(Broke action){
        ReportBuilder reportBrokeBuilder = new ReportBuilder();
        reportBrokeBuilder.addStuff(action.getBrokenStuff());
        reportBrokeBuilder.addTime(action.getTime());
        reportBrokeBuilder.addRoom(action.getRoom());
        reportBrokeBuilder.addActionType(BROKE);
        return reportBrokeBuilder.getResult();
    }

    /**
     * The method creates a report if someone prepares something.
     * @param action is the cooking action
     * @return Returns a finished report
     */
    public Report createCookReport(Cook action){
        ReportBuilder reportCookBuilder = new ReportBuilder();
        reportCookBuilder.addStuff((Stuff) action.getStuffAPI());
        reportCookBuilder.addTime(action.getTime());
        reportCookBuilder.addRoom(action.getRoom());
        reportCookBuilder.addPerson((Person) action.getInhabitant());
        reportCookBuilder.addActionType(ActionType.COOK);
        return reportCookBuilder.getResult();
    }

    /**
     * The method creates a report if someone eats something.
     * @param action is the action of the meal.
     * @return Returns a finished report
     */
    public Report createEatReport(Eat action){
        ReportBuilder reportEatBuilder = new ReportBuilder();
        reportEatBuilder.addTime(action.getTime());
        reportEatBuilder.addRoom(action.getRoom());
        reportEatBuilder.addFood(action.getFood());
        reportEatBuilder.addPerson((Person) action.getInhabitant());
        reportEatBuilder.addActionType(ActionType.EAT);
        return reportEatBuilder.getResult();
    }

    /**
     * The method creates a report if someone feeds the animal.
     * @param action is the action of feeding the animal.
     * @return Returns a finished report
     */
    public Report createFeedAnimalReport(FeedAnimal action){
        ReportBuilder reportFeedAnimalBuilder = new ReportBuilder();
        reportFeedAnimalBuilder.addTime(action.getTime());
        reportFeedAnimalBuilder.addRoom(action.getRoom());
        reportFeedAnimalBuilder.addPerson((Person) action.getInhabitant());
        reportFeedAnimalBuilder.addPet(action.getPet());
        reportFeedAnimalBuilder.addActionType(ActionType.FEED_ANIMAL);
        return reportFeedAnimalBuilder.getResult();
    }

    /**
     * The method creates a report if a mother feeds a child.
     * @param action is the action of feeding the baby.
     * @return Returns a finished report
     */
    public Report createFeedBabyReport(FeedBaby action){
        ReportBuilder reportFeedBabyBuilder = new ReportBuilder();
        reportFeedBabyBuilder.addTime(action.getTime());
        reportFeedBabyBuilder.addPerson((Person) action.getInhabitant());
        reportFeedBabyBuilder.addActionType(ActionType.FEED_BABY);
        return reportFeedBabyBuilder.getResult();
    }

    /**
     * The method creates a report if we want to get the amount of electricity used per month.
     * @param action is the action of measuring the consumed electricity
     * @return Returns a finished report
     */
    public Report createStuffElectricConsumptionReportMonth(ElectricityConsumptionMonth action){
        ReportBuilder reportStuffElectricConsumptionMonthBuilder = new ReportBuilder();
        reportStuffElectricConsumptionMonthBuilder.addConsumption(action.getElectricityConsumptionMonth());
        reportStuffElectricConsumptionMonthBuilder.addDay(action.getTime());
        reportStuffElectricConsumptionMonthBuilder.addActionType(ActionType.ELECTRICITY_CONSUMPTION_MONTH);
        return reportStuffElectricConsumptionMonthBuilder.getResult();
    }

    /**
     * The method creates a report if we want to get the amount of electricity used per day.
     * @param action is the action of measuring the consumed electricity
     * @return Returns a finished report
     */
    public Report createStuffElectricConsumptionReportDay(ElectricityConsumptionDay action){
        ReportBuilder reportStuffElectricConsumptionDayBuilder = new ReportBuilder();
        reportStuffElectricConsumptionDayBuilder.addConsumption(action.getElectricityConsumptionDay());
        reportStuffElectricConsumptionDayBuilder.addDay(action.getTime());
        reportStuffElectricConsumptionDayBuilder.addActionType(ActionType.ELECTRICITY_CONSUMPTION);
        return reportStuffElectricConsumptionDayBuilder.getResult();
    }

    /**
     * The method creates a report if we want to get the amount of water used per month.
     * @param action is the action of measuring the consumed water
     * @return Returns a finished report
     */
    public Report createStuffWaterConsumptionReportMonth(WaterConsumptionMonth action){
        ReportBuilder reportStuffWaterConsumptionMonthBuilder = new ReportBuilder();
        reportStuffWaterConsumptionMonthBuilder.addConsumption(action.getWaterConsumptionMonth());
        reportStuffWaterConsumptionMonthBuilder.addDay(action.getTime());
        reportStuffWaterConsumptionMonthBuilder.addActionType(ActionType.WATER_CONSUMPTION_MONTH);
        return reportStuffWaterConsumptionMonthBuilder.getResult();
    }

    /**
     * The method creates a report if we want to get the amount of water used per day.
     * @param action is the action of measuring the consumed water
     * @return Returns a finished report
     */
    public Report createStuffWaterConsumptionReportDay(WaterConsumptionDay action){
        ReportBuilder reportStuffWaterConsumptionDayBuilder = new ReportBuilder();
        reportStuffWaterConsumptionDayBuilder.addConsumption(action.getWaterConsumptionDay());
        reportStuffWaterConsumptionDayBuilder.addDay(action.getTime());
        reportStuffWaterConsumptionDayBuilder.addActionType(ActionType.WATER_CONSUMPTION);
        return reportStuffWaterConsumptionDayBuilder.getResult();
    }

    /**
     * The method creates a report if the device was repaired.
     * @param action is the action of fixing the device
     * @return Returns a finished report
     */
    public Report createRepairReport(Repair action){
        ReportBuilder reportRepairBuilder = new ReportBuilder();
        reportRepairBuilder.addStuff(action.getStuffToRepair());
        reportRepairBuilder.addTime(action.getTime());
        reportRepairBuilder.addRoom(action.getRoom());
        reportRepairBuilder.addPerson((Person) action.getInhabitant());
        reportRepairBuilder.addActionType(ActionType.REPAIR);
        return reportRepairBuilder.getResult();
    }

    /**
     * The method creates a report if everyone is asleep.
     * @param action is the sleep action of the inhabitants of the house
     * @return Returns a finished report
     */
    public Report createSleepReport(Sleep action){
        ReportBuilder reportSleepBuilder = new ReportBuilder();
        reportSleepBuilder.addTime(action.getTime());
        reportSleepBuilder.addRoom(action.getRoom());
        if(action.getInhabitant() instanceof Pet){
            reportSleepBuilder.addPet((Pet) action.getInhabitant());
        }else {
            reportSleepBuilder.addPerson((Person) action.getInhabitant());
        }
        reportSleepBuilder.addActionType(ActionType.SLEEP);
        return reportSleepBuilder.getResult();
    }

    /**
     * The method creates a report if someone uses something.
     * @param action is the action of using the device
     * @return Returns a finished report
     */
    public Report createUseReport(Use action){
        ReportBuilder reportUseBuilder = new ReportBuilder();
        reportUseBuilder.addStuff(action.getStuffToUse());
        reportUseBuilder.addTime(action.getTime());
        reportUseBuilder.addRoom(action.getRoom());
        reportUseBuilder.addPerson((Person) action.getInhabitant());
        reportUseBuilder.addActionType(ActionType.USE);
        return reportUseBuilder.getResult();
    }

    /**
     * The method creates a report if everyone is awake.
     * @param action is the action of waking up the inhabitants of the house.
     * @return Returns a finished report
     */
    public Report createWakeUpReport(WakeUp action){
        ReportBuilder reportWakeUpBuilder = new ReportBuilder();
        reportWakeUpBuilder.addTime(action.getTime());
        reportWakeUpBuilder.addRoom(action.getRoom());
        if(action.getInhabitant() instanceof Pet){
            reportWakeUpBuilder.addPet((Pet) action.getInhabitant());
        }else {
            reportWakeUpBuilder.addPerson((Person) action.getInhabitant());
        }
        reportWakeUpBuilder.addActionType(ActionType.WAKE_UP);
        return reportWakeUpBuilder.getResult();
    }

    /**
     * The method creates a report if the state of the sensor has changed.
     * @param action is an action that changes the state of the sensor
     * @return Returns a finished report
     */
    public Report createChangeSensorStateReport(ChangeSensorState action){
        ReportBuilder reportChangeSensorStateBuilder = new ReportBuilder();
        reportChangeSensorStateBuilder.addStuff(action.getSensor());
        reportChangeSensorStateBuilder.addTime(action.getTime());
        reportChangeSensorStateBuilder.addRoom(action.getRoom());
        reportChangeSensorStateBuilder.addChangeStateComment(action.getComment());
        reportChangeSensorStateBuilder.addActionType(ActionType.CHANGE_SENSOR_STATE);
        return reportChangeSensorStateBuilder.getResult();
    }

    /**
     * The method creates a report when someone wants to take food.
     * @param action is an action that puts food somewhere
     * @return Returns a finished report
     */
    public Report createPutFoodReport(PutFood action){
        ReportBuilder reportPutFoodBuilder = new ReportBuilder();
        reportPutFoodBuilder.addStuff(action.getStuff());
        reportPutFoodBuilder.addFood(action.getFood());
        reportPutFoodBuilder.addTime(action.getTime());
        reportPutFoodBuilder.addRoom(action.getRoom());
        reportPutFoodBuilder.addPerson((Person)action.getInhabitant());
        reportPutFoodBuilder.addActionType(ActionType.PUT_FOOD);
        return reportPutFoodBuilder.getResult();
    }

    /**
     * The method creates a report when someone wants to put food.
     * @param action is an action that takes food from somewhere
     * @return Returns a finished report
     */
    public Report createTakeFoodReport(TakeFood action){
        ReportBuilder reportTakeFoodBuilder = new ReportBuilder();
        reportTakeFoodBuilder.addStuff(action.getStuff());
        reportTakeFoodBuilder.addTime(action.getTime());
        reportTakeFoodBuilder.addRoom(action.getRoom());
        reportTakeFoodBuilder.addPerson((Person)action.getInhabitant());
        reportTakeFoodBuilder.addActionType(ActionType.TAKE_FOOD);
        return reportTakeFoodBuilder.getResult();
    }

    /**
     * The method creates a report when the documentation is read.
     * @param action this is the action that invokes reading the documentation.
     * @return Returns a finished report
     */
    public Report createReadDocReport(ReadDoc action){
        ReportBuilder reportReadDocBuilder = new ReportBuilder();
        reportReadDocBuilder.addStuff(action.getStuff());
        reportReadDocBuilder.addTime(action.getTime());
        reportReadDocBuilder.addPerson((Person)action.getInhabitant());
        reportReadDocBuilder.addActionType(ActionType.READ_DOC);
        return reportReadDocBuilder.getResult();
    }

    /**
     * The method creates a report if an action to search for documentation has occurred.
     * @param action this is the action that invokes the search for documentation.
     * @return Returns a finished report
     */
    public Report createFindDocReport(FindDoc action){
        ReportBuilder reportFindDocBuilder = new ReportBuilder();
        reportFindDocBuilder.addStuff(action.getStuff());
        reportFindDocBuilder.addTime(action.getTime());
        reportFindDocBuilder.addPerson((Person)action.getInhabitant());
        reportFindDocBuilder.addActionType(ActionType.FIND_DOC);
        return reportFindDocBuilder.getResult();
    }

    /**
     * The method creates a report when a call to the service center occurs.
     * @param action this is the action that calls the service center
     * @return Returns a finished report
     */
    public Report createCallServiceReport(CallService action){
        ReportBuilder reportCallServiceBuilder = new ReportBuilder();
        reportCallServiceBuilder.addStuff(action.getStuff());
        reportCallServiceBuilder.addTime(action.getTime());
        reportCallServiceBuilder.addPerson((Person)action.getInhabitant());
        reportCallServiceBuilder.addActionType(ActionType.CALL_SERVICE);
        return reportCallServiceBuilder.getResult();
    }

    public ArrayList<Report> getAllReportsWithoutPetAsArrayList() {
        ArrayList<Report> reportResult = new ArrayList<>();
        for(Report reportBuilder : ReportBuilder.getArray()){
            if (!reportBuilder.toString().contains("Pet") && reportBuilder.toString().contains("Time"))
                reportResult.add(reportBuilder);
        }
        return reportResult;
    }
}
