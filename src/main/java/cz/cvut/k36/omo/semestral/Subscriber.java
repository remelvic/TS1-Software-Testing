package cz.cvut.k36.omo.semestral;

import cz.cvut.k36.omo.semestral.report.actions.Action;

/**
 * The interface is needed to notify subscribers. Observer pattern design
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, December 2021
 */
public interface Subscriber {

    /**
     * The method is needed to update the subscriber notification.
     * @param action this update for subscribers
     */
    void update(Action action) ;
}
