package cz.cvut.k36.omo.semestral.home.rooms;

import cz.cvut.k36.omo.semestral.inmates.Inhabitant;
import cz.cvut.k36.omo.semestral.inmates.animals.Pet;
import cz.cvut.k36.omo.semestral.inmates.peoples.Person;
import cz.cvut.k36.omo.semestral.stuff.Stuff;

import java.util.ArrayList;

/**
 * An abstract class contains basic methods for all rooms in the house.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, November 2021
 */
public abstract class Room {
    private ArrayList<Person> personList;
    private ArrayList<Pet> petList;
    private final ArrayList<Stuff> stuffList = new ArrayList<>();
    private int floorNum;

    /**
     * Normal getter.
     * @return list of animals
     */
    public ArrayList<Pet> getPetList(){
        return petList;
    }

    /**
     * Normal getter.
     * @return list of person
     */
    public ArrayList<Person> getPersonList(){
        return personList;
    }

    /**
     * Normal setter.
     */
    public void setPersonList(ArrayList<Person> personList) {
        this.personList = personList;
    }

    /**
     * Normal setter.
     */
    public void setPetList(ArrayList<Pet> petList){
        this.petList = petList;
    }

    /**
     * The method updates the list of residents of the house who are on the given floor.
     * @param inhabitantList contains a new list of residents on the floor
     */
    public void updateInhabitantList(ArrayList<Inhabitant> inhabitantList) {
        ArrayList<Person> newPersonList = new ArrayList<>();
        ArrayList<Pet> newPetList = new ArrayList<>();
        for (Inhabitant inhabitant : inhabitantList) {
            if (inhabitant instanceof Person){
                newPersonList.add((Person)inhabitant);
            }else {
                newPetList.add((Pet) inhabitant);
            }
        }
        this.personList = newPersonList;
        this.petList = newPetList;
    }

    /**
     * The method will add one resident to the general list of residents.
     * @param inhabitant will be added to the general list of inhabitants
     */
    public void addInhabitant(Inhabitant inhabitant){
        if (inhabitant instanceof Person){
            this.personList.add((Person) inhabitant);
        }else {
            this.petList.add((Pet)inhabitant);
        }
    }

    /**
     * The method will remove one inhabitant from the general list of residents.
     * @param inhabitant will be removed from the general list of inhabitants.
     */
    public void removeInhabitant(Inhabitant inhabitant){
        if (inhabitant instanceof Person){
            this.personList.remove((Person) inhabitant);
        }else {
            this.petList.remove((Pet)inhabitant);
        }
    }

    /**
     * The method will set the current floor number.
     * @param floorNum actual floor number
     */
    public void setFloorNum(int floorNum) {
        this.floorNum = floorNum;
    }

    /**
     * Normal getter.
     * @return current floor
     */
    public int getFloorNum() {
        return floorNum;
    }

    /**
     * Normal getter.
     * @return list of stuff in home
     */
    public ArrayList<Stuff> getStuffList(){
        return this.stuffList;
    }

    /**
     * This method adds stuff to the list of all stuff in the room.
     * @param stuff is what needs to be added to the list
     */
    public void addStuff(Stuff stuff){
        this.stuffList.add(stuff);
    }

    /**
     * The method will add all the necessary sensors and elements to the room.
     */
    public abstract void configure();

}
