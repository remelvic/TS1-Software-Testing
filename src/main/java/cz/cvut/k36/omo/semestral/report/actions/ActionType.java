package cz.cvut.k36.omo.semestral.report.actions;

/**
 * The enumeration type is used to determine the type of report.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, December 2021
 */
public enum ActionType {
    REPAIR{
        @Override
        public String toString() {
            return "Repair";
        }
    }, COOK {
        @Override
        public String toString() {
            return "Cook";
        }
    }, EAT {
        @Override
        public String toString() {
            return "Eat";
        }
    }, SLEEP {
        @Override
        public String toString() {
            return "Sleep";
        }
    }, WATER_CONSUMPTION {
        @Override
        public String toString() {
            return "Water consumption day";
        }
    }, WATER_CONSUMPTION_MONTH {
        @Override
        public String toString() {
            return "Water consumption month";
        }
    }, ELECTRICITY_CONSUMPTION {
        @Override
        public String toString() {
            return "Electricity consumption day";
        }
    }, ELECTRICITY_CONSUMPTION_MONTH{
        @Override
        public String toString() {
            return "Electricity consumption month";
        }
    },  FEED_ANIMAL {
        @Override
        public String toString() {
            return "Feed animal";
        }
    }, FEED_BABY {
        @Override
        public String toString() {
            return "Feed baby";
        }
    }, BROKE {
        @Override
        public String toString() {
            return "Broke";
        }
    },WAKE_UP {
        @Override
        public String toString() {
            return "Wake up";
        }
    }, USE {
        @Override
        public String toString() {
            return "Use";
        }
    }, PUT_FOOD {
        @Override
        public String toString() {
            return "Put food";
        }
    }, TAKE_FOOD {
        @Override
        public String toString() {
            return "Take food";
        }
    }, CHANGE_SENSOR_STATE {
        @Override
        public String toString(){
            return "Change sensor  state";
        }
    }, READ_DOC {
        @Override
        public String toString(){
            return "Reading documentation";
        }
    }, FIND_DOC {
        @Override
        public String toString(){
            return "Finding documentation";
        }
    }, CALL_SERVICE {
        @Override
        public String toString(){
            return "Calling service to repair stuff";
        }
    };

    /**
     * The method will return a string for the report.
     */
    public abstract String toString();
}
