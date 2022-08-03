package cz.cvut.k36.omo.semestral;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.cvut.k36.omo.semestral.inmates.animals.Cat;
import cz.cvut.k36.omo.semestral.inmates.animals.Dog;
import cz.cvut.k36.omo.semestral.inmates.animals.Parrot;
import cz.cvut.k36.omo.semestral.inmates.peoples.*;

import java.util.List;

public class Config {

    @JsonProperty("Mom")
    private Mom mom;

    @JsonProperty("Dad")
    private Dad dad;

    @JsonProperty("Children")
    private List<Child> children;

    @JsonProperty("Babies")
    private List<Baby> babies;

    @JsonProperty("Grandpa")
    private Grandpa grandpa;

    @JsonProperty("Grandma")
    private Grandma grandma;

    @JsonProperty("Cats")
    private List<Cat> cats;

    @JsonProperty("Dogs")
    private List<Dog> dogs;

    @JsonProperty("Parrot")
    private Parrot parrot;

    public Mom getMom() {
        return mom;
    }

    public Dad getDad() {
        return dad;
    }

    public List<Child> getChildren() {
        return children;
    }

    public List<Baby> getBabies() {
        return babies;
    }

    public Grandpa getGrandpa() {
        return grandpa;
    }

    public Grandma getGrandma() {
        return grandma;
    }

    public List<Cat> getCats() {
        return cats;
    }

    public List<Dog> getDogs() {
        return dogs;
    }

    public Parrot getParrot() {
        return parrot;
    }
}
