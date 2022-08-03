package cz.cvut.k36.omo.semestral.inmates.peoples;

/**
 * The enum type is used to define the type of person.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, November 2021
 */
public enum PersonType {

    BABY{
        @Override
        public boolean isBaby() {
            return true;
        }

        @Override
        public String toString() {
            return "Baby";
        }
    },CHILD{
        @Override
        public boolean isBaby() {
            return false;
        }

        @Override
        public String toString() {
            return "Child";
        }
    },DAD{
        @Override
        public boolean isBaby() {
            return false;
        }

        @Override
        public String toString() {
            return "Dad";
        }
    },GRANDMA{
        @Override
        public boolean isBaby() {
            return false;
        }

        @Override
        public String toString() {
            return "Grandma";
        }
    },GRANDPA{
        @Override
        public boolean isBaby() {
            return false;
        }

        @Override
        public String toString() {
            return "Grandpa";
        }
    },MOM{
        @Override
        public boolean isBaby() {
            return false;
        }

        @Override
        public String toString() {
            return "Mom";
        }
    },COURIER {
        @Override
        public boolean isBaby() {
            return false;
        }

        @Override
        public String toString() {
            return "Courier";
        }
    }, REPAIR_SERVICE_MASTER{
        @Override
        public boolean isBaby() {
            return false;
        }

        @Override
        public String toString() {
            return "Repair service master";
        }
    };

    /**
     * The method determines whether the baby is or not.
     * @return true if it's a baby
     */
    public abstract boolean isBaby();


    /**
     * @return a string of the type of person depending on who is calling it.
     */
    public abstract String toString();
}

