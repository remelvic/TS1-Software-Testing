package cz.cvut.k36.omo.semestral.report.actions;

import cz.cvut.k36.omo.semestral.HomeUtils;
import cz.cvut.k36.omo.semestral.home.rooms.Room;
import cz.cvut.k36.omo.semestral.inmates.animals.Pet;
import cz.cvut.k36.omo.semestral.inmates.peoples.Person;

/**
 * The class creates an action when someone feeds the animal.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, December 2021
 */
public class FeedAnimal extends Action{
    private final Pet pet;

    public FeedAnimal(int time,  Room room, Person person, Pet pet) {
        super(ActionType.FEED_ANIMAL, time, room, person);
        this.pet = pet;
        Person person1 = (Person) this.getInhabitant();
        HomeUtils.changeRoom(person1, pet.getCurrentRoom());
        this.feedPet();
    }

    /**
     * Normal getter.
     * @return pet as Pet type
     */
    public Pet getPet() {
        return this.pet;
    }

    /**
     * When the method is called, the person feeds the animal.
     */
    private void feedPet(){
        this.pet.eat();
    }
}
