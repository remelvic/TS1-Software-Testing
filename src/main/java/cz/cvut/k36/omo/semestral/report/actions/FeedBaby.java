package cz.cvut.k36.omo.semestral.report.actions;

import cz.cvut.k36.omo.semestral.HomeUtils;
import cz.cvut.k36.omo.semestral.home.rooms.Room;
import cz.cvut.k36.omo.semestral.inmates.peoples.Baby;
import cz.cvut.k36.omo.semestral.inmates.peoples.Mom;
import cz.cvut.k36.omo.semestral.inmates.peoples.Person;

/**
 * The class creates an action when the mother feeds the baby.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, December 2021
 */
public class FeedBaby extends Action{
    private final Baby baby;
    private Mom mom;

    public FeedBaby(int time,  Room room, Person person, Baby baby) {

        super(ActionType.FEED_BABY, time, room, person);
        this.baby = baby;
        if (this.getInhabitant() instanceof Mom) {
            this.mom = (Mom) this.getInhabitant();
        }
        HomeUtils.changeRoom(this.mom, this.baby.getCurrentRoom());
        this.feedBaby();
    }

    /**
     * When the method is called, the mom will feed the baby.
     */
    private void feedBaby(){
        this.baby.setSatiety(baby.getSatiety()+20);
    }
}
