package cz.cvut.k36.omo.semestral.inmates;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.cvut.k36.omo.semestral.Publisher;
import cz.cvut.k36.omo.semestral.home.rooms.Room;

/**
 * The abstract class Inhabitant contains methods that keep track of which floor the house is on.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, December 2021
 */
public abstract class Inhabitant implements Publisher {

    @JsonProperty("Name")
    private final String name;
    @JsonProperty("Age")
    private final int age;

    private int satiety;
    private Room currentRoom;
    private InhabitantState inhabitantState;

    protected Inhabitant(String name, int age) {
        this.age = age;
        this.name = name;
    }

    /**
     * Normal setter
     */
    public void setInhabitantState(InhabitantState inhabitantState) {
        this.inhabitantState = inhabitantState;
    }

    /**
     * Normal getter.
     * @return the type of the room
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Normal setter.
     * @param currentRoom сonfigures the current type of room
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * The method will indicate that the inhabitant of the house is sleeping.
     */
    public void sleep(){
        if (this.inhabitantState == InhabitantState.AWAKE){
            this.inhabitantState = InhabitantState.SLEEPING;
        }
    }

    /**
     * The method will indicate that the inhabitant of the house is awake.
     */
    public void wakeUp(){
        if (this.inhabitantState == InhabitantState.SLEEPING){
            this.inhabitantState = InhabitantState.AWAKE;
        }
    }

    /**
     * When calling the method, reduces the satiety of the animal.
     */
    public abstract void decreaseSatiety();

    /**
     * When calling the method, set the level of satiety of the animal.
     * @param satiety an animal
     */
    public void setSatiety(int satiety){
        this.satiety = satiety;
    }

    /**
     * The method checks the level of satiety in the animal.
     * @return the true if satiety is below a certain level
     */
    public abstract boolean checkSatiety();

    /**
     * Normal getter.
     * @return satiety of an animal
     */
    public int getSatiety(){
        return satiety;
    }


    /**
     * Normal getter.
     * @return name of an inhabitant
     */
    public String getName() {
        return name;
    }

    /**
     * Normal getter.
     * @return age of an inhabitant
     */
    public int getAge() { return age; }

}
