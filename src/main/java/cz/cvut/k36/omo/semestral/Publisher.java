package cz.cvut.k36.omo.semestral;

import cz.cvut.k36.omo.semestral.report.actions.Action;

/**
 * The interface is used to use the observer pattern design.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, December 2021
 */
public interface Publisher {

    /**
     * The method adds subscriptions to the list.
     * @param sub to be added to the list
     */
    void subscribe(Subscriber sub);

    /**
     * The method removes subscriptions from the list.
     * @param sub to remove from the list
     */
    void unsubscribe(Subscriber sub);

    /**
     * The method notifies the subscriber of the action.
     * @param action that will be reported to the subscriber
     */
    void notifySubscribers(Action action) ;
}
