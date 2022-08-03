package cz.cvut.k36.omo.semestral.stuff.usable;

/**
 * The enumeration type is used to define the type of food.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, December 2021
 */
public enum FoodType {
    SPAGHETTI{
        @Override
        public String toString() {
            return "Spaghetti";
        }
    }, LASAGNA {
        @Override
        public String toString() {
            return "Lasagna";
        }
    }, GOULASH {
        @Override
        public String toString() {
            return "Goulash";
        }
    }, SALAD {
        @Override
        public String toString() {
            return "Salad";
        }
    }, SOUP {
        @Override
        public String toString() {
            return "Soup";
        }
    }, BURGER {
        @Override
        public String toString() {
            return "Burger";
        }
    }, FRIES {
        @Override
        public String toString() {
            return "Fries";
        }
    };

    /**
     * @return food as type string.
     */
    public abstract String toString();
}
