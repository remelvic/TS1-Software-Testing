package cz.cvut.k36.omo.semestral.home;

import cz.cvut.k36.omo.semestral.home.rooms.*;

import java.util.ArrayList;

/**
 * The class contains which rooms are present on the floor.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, November 2021
 */
public class Floor {
    private final ArrayList<Room> roomsList;

    public Floor(ArrayList<Room> roomsList){
        this.roomsList = roomsList;
    }

    /**
     * Normal getter.
     * @return list of all rooms
     */
    public ArrayList<Room> getRoomList() {
        return roomsList;
    }
}
