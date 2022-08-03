package cz.cvut.k36.omo.semestral;

/**
 * The enumeration type is used to determine the month.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, December 2021
 */
public enum Weather {
    JANUARY{
        private int lightLevel;
        @Override
        public double getTemperature() {
            return getRandomNumber(-15, 0) * (0.01 * lightLevel) + getRandomNumber(-15,0);
        }

        @Override
        public int getHumidity() {
            return getRandomNumber(70, 95);
        }

        @Override
        public int getLightLevel(int time) {
            int hours = (time/60) % 24;

            if (hours >= 0 && hours <= 4){
                lightLevel = 1;
            }else if (hours >= 5 && hours <= 8){
                lightLevel = getRandomNumber(2, 15);
            }
            else if (hours >= 9 && hours <= 12){
                lightLevel = getRandomNumber(16, 30);
            }
            else if (hours >= 13 && hours <= 16){
                lightLevel = getRandomNumber(30, 35);
            }
            else if (hours >= 17 && hours <= 20){
                lightLevel = getRandomNumber(5, 15);
            }
            else if (hours >= 21 && hours <= 23){
                lightLevel = getRandomNumber(0, 5);
            }
            return lightLevel;
        }
    }, FEBRUARY {
        private int lightLevel;
        @Override
        public double getTemperature() {
            return getRandomNumber(-13, 2) * (0.01 * lightLevel) + getRandomNumber(-13, 2);
        }

        @Override
        public int getHumidity() {
            return getRandomNumber(63, 92);
        }

        @Override
        public int getLightLevel(int time) {
            int hours = (time/60) % 24;

            if (hours >= 0 && hours <= 4){
                lightLevel = 1;
            }else if (hours >= 5 && hours <= 8){
                lightLevel = getRandomNumber(2, 20);
            }
            else if (hours >= 9 && hours <= 12){
                lightLevel = getRandomNumber(20, 30);
            }
            else if (hours >= 13 && hours <= 16){
                lightLevel = getRandomNumber(30, 40);
            }
            else if (hours >= 17 && hours <= 20){
                lightLevel = getRandomNumber(5, 20);
            }
            else if (hours >= 21 && hours <= 23){
                lightLevel = getRandomNumber(0, 5);
            }
            return lightLevel;
        }
    }, MARCH {
        private int lightLevel;
        @Override
        public double getTemperature() {
            return getRandomNumber(-8, 12) * (0.01 * lightLevel) + getRandomNumber(-8, 12);
        }

        @Override
        public int getHumidity() {
            return getRandomNumber(66, 86);
        }

        @Override
        public int getLightLevel(int time) {
            int hours = (time/60) % 24;

            if (hours >= 0 && hours <= 4){
                lightLevel = 1;
            }else if (hours >= 5 && hours <= 8){
                lightLevel = getRandomNumber(2, 23);
            }
            else if (hours >= 9 && hours <= 12){
                lightLevel = getRandomNumber(23, 35);
            }
            else if (hours >= 13 && hours <= 16){
                lightLevel = getRandomNumber(35, 45);
            }
            else if (hours >= 17 && hours <= 20){
                lightLevel = getRandomNumber(10, 23);
            }
            else if (hours >= 21 && hours <= 23){
                lightLevel = getRandomNumber(0, 5);
            }
            return lightLevel;
        }
    }, APRIL {
        private int lightLevel;
        @Override
        public double getTemperature() {
            return getRandomNumber(-3, 20) * (0.01 * lightLevel) + getRandomNumber(-3, 20);
        }

        @Override
        public int getHumidity() {
            return getRandomNumber(48, 78);
        }

        @Override
        public int getLightLevel(int time) {
            int hours = (time/60) % 24;

            if (hours >= 0 && hours <= 4){
                lightLevel = 1;
            }else if (hours >= 5 && hours <= 8){
                lightLevel = getRandomNumber(2, 27);
            }
            else if (hours >= 9 && hours <= 12){
                lightLevel = getRandomNumber(27, 46);
            }
            else if (hours >= 13 && hours <= 16){
                lightLevel = getRandomNumber(47, 58);
            }
            else if (hours >= 17 && hours <= 20){
                lightLevel = getRandomNumber(23, 47);
            }
            else if (hours >= 21 && hours <= 23){
                lightLevel = getRandomNumber(0, 5);
            }
            return lightLevel;
        }
    }, MAY {
        private int lightLevel;
        @Override
        public double getTemperature() {
            return getRandomNumber(5, 28) * (0.01 * lightLevel) + getRandomNumber(5, 28);
        }

        @Override
        public int getHumidity() {
            return getRandomNumber(53,72);
        }

        @Override
        public int getLightLevel(int time) {
            int hours = (time/60) % 24;

            if (hours >= 0 && hours <= 4){
                lightLevel = 1;
            }else if (hours >= 5 && hours <= 8){
                lightLevel = getRandomNumber(2, 33);
            }
            else if (hours >= 9 && hours <= 12){
                lightLevel = getRandomNumber(34, 46);
            }
            else if (hours >= 13 && hours <= 16){
                lightLevel = getRandomNumber(47, 60);
            }
            else if (hours >= 17 && hours <= 20){
                lightLevel = getRandomNumber(24, 46);
            }
            else if (hours >= 21 && hours <= 23){
                lightLevel = getRandomNumber(0, 5);
            }
            return lightLevel;
        }
    }, JUNE {
        private int lightLevel;
        @Override
        public double getTemperature() {
            return getRandomNumber(10, 30) * (0.01 * lightLevel) + getRandomNumber(10, 30);
        }

        @Override
        public int getHumidity() {
            return getRandomNumber(53,72);
        }

        @Override
        public int getLightLevel(int time) {
            int hours = (time/60) % 24;

            if (hours >= 0 && hours <= 4){
                lightLevel = 1;
            }else if (hours >= 5 && hours <= 8){
                lightLevel = getRandomNumber(2, 45);
            }
            else if (hours >= 9 && hours <= 12){
                lightLevel = getRandomNumber(46, 65);
            }
            else if (hours >= 13 && hours <= 16){
                lightLevel = getRandomNumber(65, 78);
            }
            else if (hours >= 17 && hours <= 20){
                lightLevel = getRandomNumber(27, 64);
            }
            else if (hours >= 21 && hours <= 23){
                lightLevel = getRandomNumber(0, 5);
            }
            return lightLevel;
        }
    }, JULY {
        private int lightLevel;
        @Override
        public double getTemperature() {
            return getRandomNumber(14, 35) * (0.01 * lightLevel) +  getRandomNumber(14, 35);
        }

        @Override
        public int getHumidity() {
            return getRandomNumber(48, 78);
        }

        @Override
        public int getLightLevel(int time) {
            int hours = (time/60) % 24;

            if (hours >= 0 && hours <= 4){
                lightLevel = 1;
            }else if (hours >= 5 && hours <= 8){
                lightLevel = getRandomNumber(2, 46);
            }
            else if (hours >= 9 && hours <= 12){
                lightLevel = getRandomNumber(47, 70);
            }
            else if (hours >= 13 && hours <= 16){
                lightLevel = getRandomNumber(71, 83);
            }
            else if (hours >= 17 && hours <= 20){
                lightLevel = getRandomNumber(29, 67);
            }
            else if (hours >= 21 && hours <= 23){
                lightLevel = getRandomNumber(0, 5);
            }
            return lightLevel;
        }
    }, AUGUST {
        private int lightLevel;
        @Override
        public double getTemperature() {
            return getRandomNumber(15, 35) * (0.01 * lightLevel) + getRandomNumber(15, 35);
        }

        @Override
        public int getHumidity() {
            return getRandomNumber(53,72);
        }

        @Override
        public int getLightLevel(int time) {
            int hours = (time/60) % 24;

            if (hours >= 0 && hours <= 4){
                lightLevel = 1;
            }else if (hours >= 5 && hours <= 8){
                lightLevel = getRandomNumber(2, 43);
            }
            else if (hours >= 9 && hours <= 12){
                lightLevel = getRandomNumber(44, 59);
            }
            else if (hours >= 13 && hours <= 16){
                lightLevel = getRandomNumber(60, 85);
            }
            else if (hours >= 17 && hours <= 20){
                lightLevel = getRandomNumber(33, 58);
            }
            else if (hours >= 21 && hours <= 23){
                lightLevel = getRandomNumber(0, 5);
            }
            return lightLevel;
        }
    }, SEPTEMBER {
        private int lightLevel;
        @Override
        public double getTemperature() {
            return getRandomNumber(8, 25) * (0.01 * lightLevel) + getRandomNumber(8, 25);
        }

        @Override
        public int getHumidity() {
            return getRandomNumber(59, 89);
        }

        @Override
        public int getLightLevel(int time) {
            int hours = (time/60) % 24;

            if (hours >= 0 && hours <= 4){
                lightLevel = 1;
            }else if (hours >= 5 && hours <= 8){
                lightLevel = getRandomNumber(2, 35);
            }
            else if (hours >= 9 && hours <= 12){
                lightLevel = getRandomNumber(36, 54);
            }
            else if (hours >= 13 && hours <= 16){
                lightLevel = getRandomNumber(55, 62);
            }
            else if (hours >= 17 && hours <= 20){
                lightLevel = getRandomNumber(24, 52);
            }
            else if (hours >= 21 && hours <= 23){
                lightLevel = getRandomNumber(0, 5);
            }
            return lightLevel;
        }
    }, OCTOBER {
        private int lightLevel;
        @Override
        public double getTemperature() {
            return getRandomNumber(3, 20) * (0.01 * lightLevel) + getRandomNumber(3, 20);
        }

        @Override
        public int getHumidity() {
            return getRandomNumber(66, 96);
        }

        @Override
        public int getLightLevel(int time) {
            int hours = (time/60) % 24;

            if (hours >= 0 && hours <= 4){
                lightLevel = 1;
            }else if (hours >= 5 && hours <= 8){
                lightLevel = getRandomNumber(2, 10);
            }
            else if (hours >= 9 && hours <= 12){
                lightLevel = getRandomNumber(11, 18);
            }
            else if (hours >= 13 && hours <= 16){
                lightLevel = getRandomNumber(15, 23);
            }
            else if (hours >= 17 && hours <= 20){
                lightLevel = getRandomNumber(6, 10);
            }
            else if (hours >= 21 && hours <= 23){
                lightLevel = getRandomNumber(0, 5);
            }
            return lightLevel;
        }
    }, NOVEMBER {
        private int lightLevel;
        @Override
        public double getTemperature() {
            return getRandomNumber(-3, 10) * (0.01 * lightLevel) + getRandomNumber(-3, 10);
        }

        @Override
        public int getHumidity() {
            return getRandomNumber(70, 100);
        }

        @Override
        public int getLightLevel(int time) {
            int hours = (time/60) % 24;

            if (hours >= 0 && hours <= 4){
                lightLevel = 1;
            }else if (hours >= 5 && hours <= 8){
                lightLevel = getRandomNumber(2, 8);
            }
            else if (hours >= 9 && hours <= 12){
                lightLevel = getRandomNumber(11, 16);
            }
            else if (hours >= 13 && hours <= 16){
                lightLevel = getRandomNumber(16, 19);
            }
            else if (hours >= 17 && hours <= 20){
                lightLevel = getRandomNumber(8, 15);
            }
            else if (hours >= 21 && hours <= 23){
                lightLevel = getRandomNumber(0, 5);
            }
            return lightLevel;
        }
    }, DECEMBER {
        private int lightLevel;
        @Override
        public double getTemperature() {
            return getRandomNumber(-10, 3) * (0.01 * lightLevel) + getRandomNumber(-10, 3);
        }

        @Override
        public int getHumidity() {
            return getRandomNumber(67, 97);
        }

        @Override
        public int getLightLevel(int time) {
            int hours = (time/60) % 24;

            if (hours >= 0 && hours <= 4){
                lightLevel = 1;
            }else if (hours >= 5 && hours <= 8){
                lightLevel = getRandomNumber(2, 8);
            }
            else if (hours >= 9 && hours <= 12){
                lightLevel = getRandomNumber(8, 13);
            }
            else if (hours >= 13 && hours <= 16){
                lightLevel = getRandomNumber(13, 18);
            }
            else if (hours >= 17 && hours <= 20){
                lightLevel = getRandomNumber(3, 12);
            }
            else if (hours >= 21 && hours <= 23){
                lightLevel = getRandomNumber(0, 5);
            }
            return lightLevel;
        }
    };

    /**
     * The method selects a random number from an interval of numbers.
     * @param min is the minimum number
     * @param max is the maximum number
     * @return a randomly selected number from a given interval
     */
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    /**
     * Normal getter.
     * @return temperature
     */
    public abstract double getTemperature();

    /**
     * Normal getter.
     * @return humidity
     */
    public abstract int getHumidity();

    /**
     * Normal getter.
     * @param time will determine the amount of light at a given time of day
     * @return light level
     */
    public abstract int getLightLevel(int time);
}



