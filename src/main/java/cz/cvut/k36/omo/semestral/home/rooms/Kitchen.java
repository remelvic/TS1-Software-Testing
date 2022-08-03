package cz.cvut.k36.omo.semestral.home.rooms;

import cz.cvut.k36.omo.semestral.inmates.Inhabitant;
import cz.cvut.k36.omo.semestral.inmates.animals.Pet;
import cz.cvut.k36.omo.semestral.inmates.peoples.Person;
import cz.cvut.k36.omo.semestral.stuff.Stuff;
import cz.cvut.k36.omo.semestral.stuff.autonomic.Blinds;
import cz.cvut.k36.omo.semestral.stuff.autonomic.Convector;
import cz.cvut.k36.omo.semestral.stuff.autonomic.Lamp;
import cz.cvut.k36.omo.semestral.stuff.autonomic.Sensor;
import cz.cvut.k36.omo.semestral.stuff.usable.Fridge;
import cz.cvut.k36.omo.semestral.stuff.usable.microwave.Microwave;

import java.util.ArrayList;

/**
 * The class contains electrical appliances and things that are in the Kitchen.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, November 2021
 */
public class Kitchen extends Room {
    private final Microwave microwave;
    private final ArrayList<Sensor> sensorList = new ArrayList<>();
    private final ArrayList<Person> personList = new ArrayList<>();
    private final ArrayList<Pet>  petList = new ArrayList<>();

    public Kitchen(ArrayList<Inhabitant> inhabitantList, int floorNum){
        this.microwave = new Microwave(this,1);
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
        this.sensorList.add(new Lamp(this,7));
        this.sensorList.add(new Lamp(this,8));
        this.sensorList.add(new Convector(this,3));
        this.sensorList.add(new Blinds(this,3));
        for (Sensor sensor : this.sensorList) {
            this.addStuff(sensor);
        }
        this.addStuff(new Fridge(this,1));
        this.addStuff(this.microwave);
    }

    @Override
    public ArrayList<Pet> getPetList() {
        return petList;
    }

    /**
     * The method returns a string that will be sent to the report.
     */
    public String toString() {
        return "Floor Number: "+this.getFloorNum()+" Type room: Kitchen\n";
    }

    /**
     * Normal getter.
     * @return fridge as Fridge type
     */
    public Fridge getFridge(){
        for (Stuff fridge : this.getStuffList()) {
            if (fridge instanceof Fridge){
                return (Fridge) fridge;
            }
        }
        return null;
    }

    /**
     * Normal getter.
     * @return microwave as Microwave type
     */
    public Microwave getMicrowave(){
        for (Stuff microwave : this.getStuffList()){
            if (microwave instanceof Microwave){
                return (Microwave) microwave;
            }
        }
        return null;
    }
}
