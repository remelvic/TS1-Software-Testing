package cz.cvut.k36.omo.semestral.stuff;

import cz.cvut.k36.omo.semestral.HomeUtils;
import cz.cvut.k36.omo.semestral.Simulation;
import cz.cvut.k36.omo.semestral.Subscriber;
import cz.cvut.k36.omo.semestral.home.Home;
import cz.cvut.k36.omo.semestral.home.rooms.Kitchen;
import cz.cvut.k36.omo.semestral.inmates.Inhabitant;
import cz.cvut.k36.omo.semestral.inmates.peoples.Dad;
import cz.cvut.k36.omo.semestral.inmates.peoples.Person;
import cz.cvut.k36.omo.semestral.report.actions.*;
import cz.cvut.k36.omo.semestral.report.actions.repair.*;
import cz.cvut.k36.omo.semestral.stuff.autonomic.*;
import cz.cvut.k36.omo.semestral.stuff.usable.*;
import cz.cvut.k36.omo.semestral.stuff.usable.microwave.Microwave;

import java.util.ArrayList;

/**
 * The API class contains functions that are used to manage all the stuff at home.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, December 2021
 */
public class StuffAPI {
    ArrayList<Stuff> stuffList;
    private final Home home;
    private double electricityConsumption;
    private double waterConsumption;
    private final ArrayList<Double> electricityConsumptionList;
    private final ArrayList<Double> waterConsumptionList;
    private final ArrayList<Double> electricityConsumptionMonthList;
    private final ArrayList<Double> waterConsumptionMonthList;

    public StuffAPI(ArrayList<Stuff> stuffList, Home home){
        this.stuffList = stuffList;
        this.home = home;
        this.electricityConsumption = 0;
        this.waterConsumption = 0;
        this.electricityConsumptionList = new ArrayList<>();
        this.waterConsumptionList = new ArrayList<>();
        this.electricityConsumptionMonthList = new ArrayList<>();
        this.waterConsumptionMonthList = new ArrayList<>();
    }

    /**
     * Normal getter.
     * @return kitchen as Kitchen Room type
     */
    public Kitchen getKitchen(){
        return this.home.getKitchen();
    }

    /**
     * The method call will decrease the quality of each stuff in the list.
     */
    public void updateStuffQuality(int time){
        for (Stuff stuff : this.stuffList){
            if (!(stuff instanceof Sensor) && !stuff.checkQuality()){
                Broke brokeAction = new Broke(time, stuff.getRoom(),stuff,this);
                stuff.notifySubscribers(brokeAction);
                repairStuff(stuff, time);
            }else if (stuff instanceof Sensor){
                stuff.decreaseQuality();
                if (!stuff.checkQuality()){
                    Broke brokeAction = new Broke(time, stuff.getRoom(),stuff,this);
                    stuff.notifySubscribers(brokeAction);
                    repairStuff(stuff, time);
                }
            }
        }
    }

    /**
     * The method updates the amount of water and electricity used per day.
     * @param day is needed to determine the day in the month for a more accurate calculation of the electricity and
     *           water used
     */
    public void updateConsumption(int day){
        Stuff currStuff = null;
        for (Stuff stuff : this.stuffList) {
            if (!(stuff instanceof Ski || stuff instanceof Bike || stuff instanceof Car)){
                if (stuff instanceof TV){
                    this.electricityConsumption += ((TV) stuff).getElectricityConsumptionDay();
                }else if (stuff instanceof Fridge){
                    this.electricityConsumption += ((Fridge) stuff).getElectricityConsumptionDay();
                }else if (stuff instanceof Computer){
                    this.electricityConsumption += ((Computer) stuff).getElectricityConsumptionDay();
                }else if (stuff instanceof Microwave){
                    this.electricityConsumption += ((Microwave) stuff).getElectricityConsumptionDay();
                }else if (stuff instanceof Lamp){
                    this.electricityConsumption += ((Lamp) stuff).getElectricityConsumptionDay();
                }else if (stuff instanceof Humidifier){
                    this.electricityConsumption += ((Humidifier) stuff).getElectricityConsumptionDay();
                    this.waterConsumption += ((Humidifier) stuff).getWaterConsumptionDay();
                }else if (stuff instanceof Convector){
                    this.electricityConsumption += ((Convector) stuff).getElectricityConsumptionDay();
                    this.waterConsumption += ((Convector) stuff).getWaterConsumptionDay();
                }else if (stuff instanceof Blinds){
                    this.electricityConsumption += ((Blinds) stuff).getElectricityConsumptionDay();
                }
            }
            currStuff = stuff;
        }
        this.electricityConsumptionList.add(this.electricityConsumption);
        this.waterConsumptionList.add(this.waterConsumption);
        assert currStuff != null;
        currStuff.notifySubscribers(new ElectricityConsumptionDay(day*1440,currStuff.getRoom(),
                electricityConsumption));
        currStuff.notifySubscribers(new WaterConsumptionDay(day*1440,currStuff.getRoom(),waterConsumption));
        this.waterConsumption = 0;
        this.electricityConsumption = 0;
        updateMonthConsumption(day);
    }

    /**
     * The method determines what month is at the time the method is called and calculates a more
     * accurate use of the monthly use of electricity and water.
     * @param day the day parameter helps you determine what month it is
     *            31 -> January     59 -> February    90 -> March
     *            120 -> April      151 -> May        181 -> June
     *            212 -> July       243 -> August     274 -> September
     *            305 -> October    335 -> November   365 -> December
     */
    public void updateMonthConsumption(int day){
        Stuff stuff = this.stuffList.get(0);
        switch (day) {
            case 31 -> {
                this.countMonthCons(0, day);
                stuff.notifySubscribers(new ElectricityConsumptionMonth(day * 1440, stuff.getRoom(),
                        electricityConsumptionMonthList.get(0)));
                stuff.notifySubscribers(new WaterConsumptionMonth(day * 1440, stuff.getRoom(),
                        waterConsumptionMonthList.get(0)));
            }
            case 59 -> {
                this.countMonthCons(31, day);
                stuff.notifySubscribers(new ElectricityConsumptionMonth(day * 1440, stuff.getRoom(),
                        electricityConsumptionMonthList.get(1)));
                stuff.notifySubscribers(new WaterConsumptionMonth(day * 1440, stuff.getRoom(),
                        waterConsumptionMonthList.get(1)));
            }
            case 90 -> {
                this.countMonthCons(59, day);
                stuff.notifySubscribers(new ElectricityConsumptionMonth(day * 1440, stuff.getRoom(),
                        electricityConsumptionMonthList.get(2)));
                stuff.notifySubscribers(new WaterConsumptionMonth(day * 1440, stuff.getRoom(),
                        waterConsumptionMonthList.get(2)));
            }
            case 120 -> {
                this.countMonthCons(90, day);
                stuff.notifySubscribers(new ElectricityConsumptionMonth(day * 1440, stuff.getRoom(),
                        electricityConsumptionMonthList.get(3)));
                stuff.notifySubscribers(new WaterConsumptionMonth(day * 1440, stuff.getRoom(),
                        waterConsumptionMonthList.get(3)));
            }
            case 151 -> {
                this.countMonthCons(120, day);
                stuff.notifySubscribers(new ElectricityConsumptionMonth(day * 1440, stuff.getRoom(),
                        electricityConsumptionMonthList.get(4)));
                stuff.notifySubscribers(new WaterConsumptionMonth(day * 1440, stuff.getRoom(),
                        waterConsumptionMonthList.get(4)));
            }
            case 181 -> {
                this.countMonthCons(151, day);
                stuff.notifySubscribers(new ElectricityConsumptionMonth(day * 1440, stuff.getRoom(),
                        electricityConsumptionMonthList.get(5)));
                stuff.notifySubscribers(new WaterConsumptionMonth(day * 1440, stuff.getRoom(),
                        waterConsumptionMonthList.get(5)));
            }
            case 212 -> {
                this.countMonthCons(181, day);
                stuff.notifySubscribers(new ElectricityConsumptionMonth(day * 1440, stuff.getRoom(),
                        electricityConsumptionMonthList.get(6)));
                stuff.notifySubscribers(new WaterConsumptionMonth(day * 1440, stuff.getRoom(),
                        waterConsumptionMonthList.get(6)));
            }
            case 243 -> {
                this.countMonthCons(212, day);
                stuff.notifySubscribers(new ElectricityConsumptionMonth(day * 1440, stuff.getRoom(),
                        electricityConsumptionMonthList.get(7)));
                stuff.notifySubscribers(new WaterConsumptionMonth(day * 1440, stuff.getRoom(),
                        waterConsumptionMonthList.get(7)));
            }
            case 274 -> {
                this.countMonthCons(243, day);
                stuff.notifySubscribers(new ElectricityConsumptionMonth(day * 1440, stuff.getRoom(),
                        electricityConsumptionMonthList.get(8)));
                stuff.notifySubscribers(new WaterConsumptionMonth(day * 1440, stuff.getRoom(),
                        waterConsumptionMonthList.get(8)));
            }
            case 305 -> {
                this.countMonthCons(274, day);
                stuff.notifySubscribers(new ElectricityConsumptionMonth(day * 1440, stuff.getRoom(),
                        electricityConsumptionMonthList.get(9)));
                stuff.notifySubscribers(new WaterConsumptionMonth(day * 1440, stuff.getRoom(),
                        waterConsumptionMonthList.get(9)));
            }
            case 335 -> {
                this.countMonthCons(305, day);
                stuff.notifySubscribers(new ElectricityConsumptionMonth(day * 1440, stuff.getRoom(),
                        electricityConsumptionMonthList.get(10)));
                stuff.notifySubscribers(new WaterConsumptionMonth(day * 1440, stuff.getRoom(),
                        waterConsumptionMonthList.get(10)));
            }
            case 365 -> {
                this.countMonthCons(335, day);
                stuff.notifySubscribers(new ElectricityConsumptionMonth(day * 1440, stuff.getRoom(),
                        electricityConsumptionMonthList.get(11)));
                stuff.notifySubscribers(new WaterConsumptionMonth(day * 1440, stuff.getRoom(),
                        waterConsumptionMonthList.get(11)));
            }
            default -> {
            }
        }
    }

    /**
     * The method will collect all the amounts of water and electricity used for the month.
     * @param startDay is the beginning of the month
     * @param dayNum is the given day in the simulation
     */
    public void countMonthCons(int startDay, int dayNum){
        double electricityMonthConsumption = 0;
        double waterMonthConsumption = 0;
        for (int i = startDay; i < dayNum; i++) {
            electricityMonthConsumption += this.electricityConsumptionList.get(i);
            waterMonthConsumption += this.waterConsumptionList.get(i);
        }
        this.electricityConsumptionMonthList.add(electricityMonthConsumption);
        this.waterConsumptionMonthList.add(waterMonthConsumption);
    }

    /**
     * The method will change the state stuff to the received state.
     * @param stuffState is the state to which we will change
     * @param stuff his state changes
     */
    public void changeStuffState(StuffState stuffState, Stuff stuff){
        this.stuffList.get(this.stuffList.indexOf(stuff)).changeStuffState(stuffState);
    }

    /**
     * @return the chain of responsibilities from the very first element.
     */
    private AbstractRepairHandler getChainOfHandlers(){
        AbstractRepairHandler.setStuffAPI(this);
        AbstractRepairHandler findDocHandler = new FindDocRepairHandler();
        AbstractRepairHandler readDocHandler = new ReadDocRepairHandler();
        AbstractRepairHandler callServiceHandler = new CallServiceRepairHandler();
        AbstractRepairHandler repairHandler = new RepairHandler();
        findDocHandler.setNextHandler(readDocHandler);
        readDocHandler.setNextHandler(callServiceHandler);
        callServiceHandler.setNextHandler(repairHandler);
        return findDocHandler;
    }

    /**
     * Normal getter.
     * @return dad as Dad type
     */
    private Dad getDad(){
        for (Inhabitant person : this.home.getInhabitantList()) {
            if (person instanceof Dad){
                return (Dad) person;
            }
        }
        return null;
    }

    /**
     * The method will fix stuff.
     * @param stuffToRepair this is stuff that changes state
     */
    public void repairStuff(Stuff stuffToRepair, int time) {
        Dad dad = getDad();
        assert dad != null;
        if (dad.isUsing()){
            HomeUtils.stopUseStuff(dad, this);
        }
        HomeUtils.changeRoom(dad, stuffToRepair.getRoom());
        AbstractRepairHandler handlerChain = getChainOfHandlers();
        if (stuffToRepair instanceof Bike || stuffToRepair instanceof Ski){
            handlerChain.repair(stuffToRepair, AbstractRepairHandler.REPAIR, dad, time);
        }else{
            handlerChain.repair(stuffToRepair, AbstractRepairHandler.FIND_DOC,dad , time);
        }
    }

    /**
     * When the method is called, all stuff in the house will be subscribed to "updates".
     * @param sub to be added to the list
     */
    public void subscribeAllStuff(Subscriber sub){
        for (Stuff stuff : this.stuffList) {
            stuff.subscribe(sub);
        }
        for (Inhabitant inhabitant: this.home.getInhabitantList()){
            inhabitant.subscribe(sub);
        }
    }

    /**
     * Normal getter.
     * @return stuff in list as ArrayList type
     */
    public ArrayList<Stuff> getStuffList() {
        return this.stuffList;
    }

    /**
     * When the method is called, the person stops interacting with any element, except for the inhabitants of the house.
     * @param person is the one who stops interacting with the elements of the house
     * @param usedStuff is passed through all actions with the elements of the house
     */
    public void stopUsingStuff(Person person, Usable usedStuff) {
        ((Usable)this.getStuffList().get(this.getStuffList().indexOf(usedStuff))).stopUsing(person);
    }

    /**
     * When the method is called, the person begins to interact with some element besides the inhabitants of the house.
     * @param person is the one who starts interacting with something.
     * @param time is the time at which the action started
     * @param stuff is what the person started to interact with.
     * @return an action of type Use
     */
    public Use useStuff(Person person, int time, Usable stuff){
        person.setUsing(true);
        person.setUsedStuff(stuff);
        return ((Usable)this.getStuffList().get(this.getStuffList().indexOf(stuff))).use(person,time, this);
    }

    /**
     * Normal getter.
     * @return sensor list as ArrayList
     */
    public ArrayList<Sensor> getSensorList() {
        ArrayList<Sensor> ret = new ArrayList<>();
        for (Stuff stuff : this.getStuffList()) {
            if (stuff instanceof Sensor){
                ret.add((Sensor) stuff);
            }
        }
        return ret;
    }

    /**
     * The method calls the measure method on a specific sensor.
     * @param simulation is where the temperature, humidity and the amount of light outside come from
     * @param sensor is the sensor from whom we are measuring
     * @param time is simply the time at which the sensor starts measuring.
     */
    public void measureSensor(Simulation simulation, Sensor sensor, int time){
        this.getSensorList().get(this.getSensorList().indexOf(sensor)).measure(simulation, time);
    }
}
