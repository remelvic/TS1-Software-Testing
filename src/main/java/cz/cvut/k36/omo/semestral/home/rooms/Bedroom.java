package cz.cvut.k36.omo.semestral.home.rooms;

import cz.cvut.k36.omo.semestral.inmates.Inhabitant;
import cz.cvut.k36.omo.semestral.inmates.animals.Pet;
import cz.cvut.k36.omo.semestral.inmates.peoples.*;
import cz.cvut.k36.omo.semestral.stuff.autonomic.*;

import java.util.ArrayList;

/**
 * The class contains electrical appliances and things that are in the bedroom.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, November 2021
 */
public class Bedroom extends Room {
    private final ArrayList<Person> personList = new ArrayList<>();
    private final ArrayList<Pet> petList = new ArrayList<>();
    private final ArrayList<Sensor> sensorList = new ArrayList<>();

    public Bedroom(ArrayList<Inhabitant> inhabitantList, int floorNum){
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
    public void configure() {
        this.sensorList.add(new Lamp(this,3));
        this.sensorList.add(new Lamp(this,4));
        this.sensorList.add(new Blinds(this,1));
        this.sensorList.add(new Blinds(this,2));
        this.sensorList.add(new Convector(this,2));
        this.sensorList.add(new Humidifier(this,1));
        for(Sensor sensor: this.sensorList){
            this.addStuff(sensor);
        }
    }

    @Override
    public ArrayList<Person> getPersonList(){
        return personList;
    }

    @Override
    public ArrayList<Pet> getPetList(){
        return petList;
    }

    /**
     * The method returns a string that will be sent to the report.
     */
    public String toString() {
        return "Floor Number: "+this.getFloorNum()+" Type room: Bedroom\n";
    }
}
