package cz.cvut.k36.omo.semestral.inmates.animals;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.cvut.k36.omo.semestral.inmates.Inhabitant;
import cz.cvut.k36.omo.semestral.inmates.InhabitantState;

/**
 * An abstract class contains basic methods for all rooms in the house.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, November 2021
 */
public abstract class Pet extends Inhabitant {
    PetType type;

    Pet(@JsonProperty("Name")
                    String name,
            @JsonProperty("Age")
                    int age){
        super(name,age);
        this.setSatiety(100);
        this.setInhabitantState(InhabitantState.SLEEPING);
    }

    /**
     * When the method are called, the amount of satiety of the animal increases.
     */
    public abstract void eat();

    /**
     * The method returns a string that will be sent to the report.
     */
    public String toString() {
        return "Pet: "+this.getName()+ "; Pet type: "+type.toString()+"; ";
    }
}
