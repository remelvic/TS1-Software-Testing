package cz.cvut.k36.omo.semestral.inmates.animals;

/**
 * the enum type is used to define the type of animals.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, November 2021
 */
public enum PetType {

    DOG{
        @Override
        public String toString(){
            return "Dog";
        }
    },CAT{
        @Override
        public String toString(){
            return "Cat";
        }
    },PARROT{
        @Override
        public String toString(){
            return "Parrot";
        }
    };

    /**
     * @return a string of the type of pet depending on who is calling it.
     */
    public abstract String toString();
}

