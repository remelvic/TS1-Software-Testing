package cz.cvut.k36.omo.semestral;

import cz.cvut.k36.omo.semestral.home.Home;
import cz.cvut.k36.omo.semestral.inmates.Inhabitant;
import cz.cvut.k36.omo.semestral.inmates.animals.Pet;
import cz.cvut.k36.omo.semestral.inmates.peoples.Baby;
import cz.cvut.k36.omo.semestral.inmates.peoples.Courier;
import cz.cvut.k36.omo.semestral.inmates.peoples.Mom;
import cz.cvut.k36.omo.semestral.inmates.peoples.Person;
import cz.cvut.k36.omo.semestral.report.ReportAPI;
import cz.cvut.k36.omo.semestral.report.actions.*;
import cz.cvut.k36.omo.semestral.stuff.Stuff;
import cz.cvut.k36.omo.semestral.stuff.StuffAPI;
import cz.cvut.k36.omo.semestral.stuff.autonomic.*;
import cz.cvut.k36.omo.semestral.stuff.usable.*;
import cz.cvut.k36.omo.semestral.stuff.usable.microwave.Microwave;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * The class runs a simulation of the whole house and the lives of the inhabitants in this house.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, December 2021
 */
public class Simulation {
    private final int simulationTime;
    private final Home home;
    private Weather currentWeather;
    private double currentTemperature;
    private int currentHumidity;
    private int currentLightLevel;
    private final StuffAPI stuffAPI;
    private final ReportAPI reportAPI;

    public Simulation(int simulationTime, Home home, String reportFilePath){
        this.simulationTime = (simulationTime * 24) * 60;
        this.home = home;
        this.currentWeather = Weather.JANUARY;
        this.currentTemperature = this.currentWeather.getTemperature();
        this.currentHumidity = this.currentWeather.getHumidity();
        this.currentLightLevel = this.currentWeather.getLightLevel(0);
        this.stuffAPI = new StuffAPI(this.home.getStuffList(), this.home);
        this.reportAPI = new ReportAPI(reportFilePath);
        ActionSubscriber actionSubscriber = new ActionSubscriber(this.reportAPI);
        this.stuffAPI.subscribeAllStuff(actionSubscriber);
    }

    /**
     * The method will start a simulation.
     */
    public void runSimulation() {
        int day = 0;
        for (int i = 0; i < simulationTime; i += 30) {
            this.stuffAPI.updateStuffQuality(i);
            if (i % 1440 == 0){
                System.out.println("DAY: "+day);
                for (int j = 0; j < 6; j++) {
                    String food = randomFood();
                    Courier foodDelivery = new Courier("Food Courier", 30);

                    this.stuffAPI.getKitchen().getFridge().putFood(food);
                    this.stuffAPI.getKitchen().getFridge().notifySubscribers(this.stuffAPI.useStuff(foodDelivery,i,
                            stuffAPI.getKitchen().getFridge()));
                    this.stuffAPI.getKitchen().getFridge().notifySubscribers(new PutFood(i,this.stuffAPI.getKitchen()
                            ,foodDelivery,food,this.stuffAPI.getKitchen().getFridge()));
                }
                this.stuffAPI.getKitchen().getFridge().decreaseQuality();
                this.stuffAPI.getKitchen().getFridge().updateFridgeConsumption();
                this.stuffAPI.getKitchen().getMicrowave().decreaseQuality();
                updateConsumption(day++);
            }
            this.home.chooseInhabitants(i,this.stuffAPI);
            this.currentWeather = updateWeather(i);
            updateSensors(i);
            generateActions(i);
        }
    }

    /**
     * The method increases the amount of consumed electricity and water in one day.
     * @param day is on which day the action takes place
     */
    public void updateConsumption(int day){
        this.stuffAPI.updateConsumption(day);
    }

    /**
     * The method updates all sensors in the house (their state).
     * @param time is simply the time at which the update takes place.
     */
    public void updateSensors(int time){
        for (Sensor sensor: this.stuffAPI.getSensorList()) {
            this.stuffAPI.measureSensor(this, sensor, time);
        }
    }

    /**
     * Normal getter.
     * @return reportAPI as ReportAPI type
     */
    public ReportAPI getReportAPI(){
        return this.reportAPI;
    }

    /**
     * The method updates the weather depending on the time obtained.
     * @param time we get in minutes
     * @return а month, which contains temperature, humidity and the amount of light.
     * if time >= 0 && time <= 44640 ---> January
     * if time >= 44641 && time <= 84960 ---> February
     * if time >= 84961 && time <= 129600 ---> March
     * if time >= 129601 && time <= 172800 ---> April
     * if time >= 172801 && time <= 217440 ---> May
     * if time >= 217441 && time <= 260640 ---> June
     * if time >= 260641 && time <= 305280 ---> July
     * if time >= 305281 && time <= 348480 ---> August
     * if time >= 348481 && time <= 391680 ---> September
     * if time >= 391681 && time <= 436320 ---> October
     * if time >= 436321 && time <= 479520 ---> November
     * if time >= 479521 ---> December
     */
    public Weather updateWeather(int time) {
        Weather weather = null;
        if (time >= 0 && time <= 44640) {
            weather = Weather.JANUARY;
        }
        else if (time >= 44641 && time <= 84960) {
            weather = Weather.FEBRUARY;
        }
        else if (time >= 84961 && time <= 129600) {
            weather = Weather.MARCH;
        }
        else if (time >= 129601 && time <= 172800) {
            weather = Weather.APRIL;
        }
        else if (time >= 172801 && time <= 217440) {
            weather = Weather.MAY;
        }
        else if (time >= 217441 && time <= 260640) {
            weather = Weather.JUNE;
        }
        else if (time >= 260641 && time <= 305280) {
            weather = Weather.JULY;
        }
        else if (time >= 305281 && time <= 348480) {
            weather = Weather.AUGUST;
        }
        else if (time >= 348481 && time <= 391680) {
            weather = Weather.SEPTEMBER;
        }
        else if (time >= 391681 && time <= 436320) {
            weather = Weather.OCTOBER;
        }
        else if (time >= 436321 && time <= 479520) {
            weather = Weather.NOVEMBER;
        }
        else if (time >= 479521) {
            weather = Weather.DECEMBER;
        }

        assert weather != null;
        updateHumidity(weather);
        updateLightLevel(weather, time);
        updateTemperatureLevel(weather);
        return weather;
    }

    /**
     * The method updates the humidity depending on the weather.
     * @param weather is a month in the year by which we determine the amount of humidity in this month
     */
    public void updateHumidity(Weather weather) {
        this.currentHumidity = weather.getHumidity();
    }

    /**
     * The method updates the light level depending on the weather.
     * @param weather is a month in the year by which we determine the amount of light in this month
     * @param time    determines how much light at a certain time of day
     */
    public void updateLightLevel(Weather weather, int time) {
        this.currentLightLevel = weather.getLightLevel(time);
    }

    /**
     * The method updates the temperature depending on the weather.
     * @param weather is the month in the year by which we determine the temperature in this month
     */
    public void updateTemperatureLevel(Weather weather) {
        this.currentTemperature = weather.getTemperature();
    }

    /**
     * The method creates any events in the house.
     * @param time is needed to determine at what time this or that event occurred
     */
    public void generateActions(int time) {
        if (calculateHours(time) < 7 && calculateHours(time) >= 0) {
            for (Inhabitant inhabitant : this.home.getInhabitantList()) {
                inhabitant.notifySubscribers(new Sleep(time, inhabitant.getCurrentRoom(), inhabitant));
            }
        }else if(calculateHours(time) == 8){
            for(Inhabitant inhabitant : this.home.getInhabitantList()){
                inhabitant.notifySubscribers(new WakeUp(time, inhabitant.getCurrentRoom(), inhabitant));
            }
        }else {
            for (Inhabitant inhabitant : this.home.getInhabitantList()) {
                if (time % 120 == 0) {
                    inhabitant.decreaseSatiety();
                }
                if(inhabitant.checkSatiety()){
                    if (inhabitant instanceof Pet){
                        for (Person person: inhabitant.getCurrentRoom().getPersonList()) {
                            if (!person.isUsing()){
                                FeedAnimal feedAnimalAction = person.feedPet((Pet)inhabitant, time);
                                inhabitant.notifySubscribers(feedAnimalAction);
                                break;
                            }
                        }
                    }else if (inhabitant instanceof Baby){
                        for (Inhabitant mom: this.home.getInhabitantList()){
                            if (mom instanceof Mom){
                                FeedBaby feedBabyAction = ((Mom) mom).feedBaby((Baby) inhabitant, time);
                                inhabitant.notifySubscribers(feedBabyAction);
                                break;
                            }
                        }

                    }else {
                        Eat eatAction = ((Person)inhabitant).eat(time, this.stuffAPI);
                        inhabitant.notifySubscribers(eatAction);
                    }
                }
                if (inhabitant instanceof Person) {
                    Usable randomToUse = randomUsable(inhabitant.getCurrentRoom().getStuffList());
                    Use useAction = null;
                    if (!(randomToUse instanceof Microwave || randomToUse instanceof Fridge)) { // check weather
                        if(randomToUse instanceof Bike){
                            if(!(this.currentWeather == Weather.JANUARY || this.currentWeather == Weather.DECEMBER ||
                                    this.currentWeather == Weather.FEBRUARY)){
                                useAction = this.stuffAPI.useStuff((Person) inhabitant, time, randomToUse);
                            }
                        }else if (randomToUse instanceof Ski){
                            if(this.currentWeather == Weather.JANUARY || this.currentWeather == Weather.DECEMBER ||
                                    this.currentWeather == Weather.FEBRUARY){
                                useAction = this.stuffAPI.useStuff((Person) inhabitant, time, randomToUse);
                            }
                        }else if (randomToUse != null){
                            useAction = this.stuffAPI.useStuff((Person) inhabitant, time, randomToUse);

                        }
                        if (useAction != null) {
                            inhabitant.notifySubscribers(useAction);
                        }
                    }
                }
            }
        }
    }

    /**
     * The method selects a random element of type Usable.
     * @param stuffList contains all the stuff in the house.
     * @return a randomly selected house element
     */
    public Usable randomUsable(ArrayList<Stuff> stuffList){
        Usable ret;
        Random rand = new Random();
        ArrayList<Usable> usableList = new ArrayList<>();
        for (Stuff stuff : stuffList) {
            if (stuff instanceof Usable) {
                if (!((Usable) stuff).isUsed() && !(stuff instanceof Fridge) && !(stuff instanceof Microwave)) {
                    usableList.add((Usable) stuff);
                }
            }
        }
        if (usableList.size() > 0){
        ret = usableList.get(rand.nextInt(usableList.size()));
        } else {
            ret = null;
        }
        return ret;
    }

    /**
     * The method calculates what hour at the time of the call.
     * @param time received in minutes will be converted to hours
     * @return the hour of type int
     */
    public int calculateHours(int time) {
        return time / 60 % 24;
    }

    /**
     * Method selects random food.
     * @return a random selected food of type string
     */
    public String randomFood(){
        List<FoodType> foodTypes = Arrays.asList(FoodType.values());
        Random rand = new Random();
        return (foodTypes.get(rand.nextInt(foodTypes.size()))).toString();
    }

    /**
     * Normal getter.
     * @return temperature as double type
     */
    public double getCurrentTemperature() {
        return currentTemperature;
    }

    /**
     * Normal getter.
     * @return humidity as int type
     */
    public int getCurrentHumidity() {
        return currentHumidity;
    }

    /**
     * Normal getter.
     * @return light level as int type
     */
    public int getCurrentLightLevel() {
        return currentLightLevel;
    }
}
