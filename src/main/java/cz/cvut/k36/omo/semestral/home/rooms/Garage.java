package cz.cvut.k36.omo.semestral.home.rooms;

import cz.cvut.k36.omo.semestral.inmates.Inhabitant;
import cz.cvut.k36.omo.semestral.inmates.animals.Pet;
import cz.cvut.k36.omo.semestral.inmates.peoples.Person;
import cz.cvut.k36.omo.semestral.stuff.autonomic.Lamp;
import cz.cvut.k36.omo.semestral.stuff.autonomic.Sensor;
import cz.cvut.k36.omo.semestral.stuff.usable.Bike;
import cz.cvut.k36.omo.semestral.stuff.usable.Car;
import cz.cvut.k36.omo.semestral.stuff.usable.Ski;
import cz.cvut.k36.omo.semestral.stuff.usable.Vehicle;

import java.util.ArrayList;

/**
 * The class contains electrical appliances and things that are in the garage.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, November 2021
 */
public class Garage extends Room {
    private final ArrayList<Vehicle> vehicleList = new ArrayList<>();
    private final ArrayList<Person> personList = new ArrayList<>();
    private final ArrayList<Sensor> sensorList = new ArrayList<>();
    private final ArrayList<Pet> petList = new ArrayList<>();

    public Garage(ArrayList<Inhabitant> inhabitantList, int floorNum){
        this.setFloorNum(floorNum);
        for(Inhabitant person : inhabitantList){
            if(person instanceof Person){
                this.personList.add((Person) person);
            }else if(person instanceof Pet){
                this.petList.add((Pet) person);
            }
            person.setCurrentRoom(this);
        }
        setPersonList(personList);
        setPetList(petList);
        configure();
    }

    @Override
    public ArrayList<Person> getPersonList() {
        return personList;
    }

    @Override
    public void configure() {
        this.sensorList.add(new Lamp(this,5));
        this.sensorList.add(new Lamp(this,6));
        this.vehicleList.add(new Car(this,1));
        this.vehicleList.add(new Ski(this,1));
        this.vehicleList.add(new Bike(this,1));
        for (Sensor sensor : this.sensorList) {
            this.addStuff(sensor);
        }
        for (Vehicle vehicle : this.vehicleList){
            this.addStuff(vehicle);
        }
    }

    @Override
    public ArrayList<Pet> getPetList() {
        return petList;
    }

    /**
     * The method returns a string that will be sent to the report.
     */
    public String toString() {
        return "Floor Number: "+this.getFloorNum()+" Type room: Garage\n";
    }
}
