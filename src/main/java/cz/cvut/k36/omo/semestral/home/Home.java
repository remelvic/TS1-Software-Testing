package cz.cvut.k36.omo.semestral.home;

import cz.cvut.k36.omo.semestral.Config;
import cz.cvut.k36.omo.semestral.HomeUtils;
import cz.cvut.k36.omo.semestral.home.rooms.*;
import cz.cvut.k36.omo.semestral.inmates.Inhabitant;
import cz.cvut.k36.omo.semestral.inmates.animals.*;
import cz.cvut.k36.omo.semestral.inmates.peoples.*;
import cz.cvut.k36.omo.semestral.stuff.Stuff;
import cz.cvut.k36.omo.semestral.stuff.StuffAPI;
import cz.cvut.k36.omo.semestral.stuff.usable.Car;
import cz.cvut.k36.omo.semestral.stuff.usable.TV;
import cz.cvut.k36.omo.semestral.stuff.usable.Usable;

import java.util.ArrayList;
import java.util.Random;

/**
 * The Home class is the main class in which the whole process takes place.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, November 2021
 */
public class Home {
    private static Home INSTANCE;
    private final Floor firstFloor;
    private final Floor secondFloor;
    private final ArrayList<Inhabitant> inhabitantList;
    private final ArrayList<Stuff> stuffList;

    private Home(Config config) {
        stuffList = new ArrayList<>();
        inhabitantList = new ArrayList<>();

        inhabitantList.add(config.getMom());
        inhabitantList.add(config.getDad());
        inhabitantList.addAll(config.getChildren());
        inhabitantList.add(config.getGrandpa());
        inhabitantList.add(config.getGrandma());
        inhabitantList.addAll(config.getBabies());
        inhabitantList.addAll(config.getCats());
        inhabitantList.addAll(config.getDogs());
        inhabitantList.add(config.getParrot());

        ArrayList<Room> firstFloorRoomList = new ArrayList<>();
        ArrayList<Room> secondFloorRoomList = new ArrayList<>();
        ArrayList<Inhabitant> bedroomInhabitantList = new ArrayList<>();
        ArrayList<Inhabitant> livingRoomInhabitantList = new ArrayList<>();
        for (Inhabitant inhabitant : this.inhabitantList) {
            if (inhabitant instanceof Person) {
                bedroomInhabitantList.add(inhabitant);
            } else {
                livingRoomInhabitantList.add(inhabitant);
            }
        }

        firstFloorRoomList.add(new Garage(new ArrayList<>(), 1));
        firstFloorRoomList.add(new Kitchen(new ArrayList<>(), 1));
        secondFloorRoomList.add(new Bathroom(new ArrayList<>(), 2));
        secondFloorRoomList.add(new Bedroom(bedroomInhabitantList, 2));
        secondFloorRoomList.add(new LivingRoom(livingRoomInhabitantList, 2));

        this.firstFloor = new Floor(firstFloorRoomList);
        this.secondFloor = new Floor(secondFloorRoomList);
    }

    /**
     * The method is needed to use the Singleton pattern.
     * @return an instance of the Home class
     */
    public synchronized static Home getInstance(Config config) {
        if (INSTANCE == null){
            INSTANCE = new Home(config);
        }
        return INSTANCE;
    }

    /**
     * Normal getter.
     * @return list of Inhabitant
     */
    public ArrayList<Inhabitant> getInhabitantList() {
        return inhabitantList;
    }

    /**
     * The method determines which of the inhabitants of the house at what time will be in any room.
     * @param time is obtained in minutes, it is needed to determine the time interval
     */
    public void chooseInhabitants(int time, StuffAPI stuffAPI) {
        int hourTime = (time / 60) % 24;
        //NIGHT sleep
        if (hourTime >= 0 && hourTime <= 8) {
            ArrayList<Inhabitant> personList = new ArrayList<>();
            ArrayList<Inhabitant> petList = new ArrayList<>();
            for (Inhabitant inhabitant : this.inhabitantList) {
                if (inhabitant instanceof Pet) {
                    petList.add(inhabitant);
                } else if (inhabitant instanceof Person) {
                    if (((Person) inhabitant).isUsing()) {
                        HomeUtils.stopUseStuff((Person) inhabitant, stuffAPI);
                    }
                    personList.add(inhabitant);
                }
            }
            for (int i = 0; i < this.secondFloor.getRoomList().size(); i++) {
                if (this.secondFloor.getRoomList().get(i) instanceof Bedroom) {
                    this.secondFloor.getRoomList().get(i).updateInhabitantList(personList);
                    for (Person person : this.secondFloor.getRoomList().get(i).getPersonList()) {
                        HomeUtils.changeRoom(person, this.secondFloor.getRoomList().get(i));
                    }
                }
                if (this.secondFloor.getRoomList().get(i) instanceof LivingRoom) {
                    this.secondFloor.getRoomList().get(i).updateInhabitantList(petList);
                    for (Pet pet : this.secondFloor.getRoomList().get(i).getPetList()) {
                        HomeUtils.changeRoom(pet, this.secondFloor.getRoomList().get(i));
                    }
                }
            }
            //DAY change rooms
        } else if (hourTime >= 8 && hourTime <= 23) {
            Room randomRoom;
            for (Stuff usableStuff : this.stuffList) {
                if (usableStuff instanceof Usable && ((Usable) usableStuff).isUsed()) {
                    if (usableStuff instanceof Car || usableStuff instanceof TV) {
                        Person lastPerson;
                        while (((Usable) usableStuff).isUsed()) {
                            lastPerson = ((Usable) usableStuff).getPerson();
                            HomeUtils.stopUseStuff(lastPerson, stuffAPI);
                        }
                    } else {
                        Person usingPerson = ((Usable) usableStuff).getPerson();
                        HomeUtils.stopUseStuff(usingPerson, stuffAPI);
                    }
                }
            }
            ArrayList<Room> roomList = new ArrayList<>(this.firstFloor.getRoomList());
            roomList.addAll(this.secondFloor.getRoomList());
            for (Inhabitant inhabitant : this.inhabitantList) {
                if (inhabitant instanceof Baby) {
                    ArrayList<Room> randomList = new ArrayList<>();
                    for (Room room : this.secondFloor.getRoomList()) {
                        if (room instanceof Bedroom || room instanceof LivingRoom) {
                            randomList.add(room);
                        }
                    }
                    randomRoom = randomList.get(new Random().nextInt(randomList.size()));
                } else {
                    randomRoom = roomList.get(new Random().nextInt(roomList.size()));
                }
                HomeUtils.changeRoom(inhabitant, randomRoom);
            }
        }
    }

    /**
     * Normal getter.
     * @return a list of all things in the house (sensors, vehicles, appliances, etc.)
     */
    public ArrayList<Stuff> getStuffList(){
        for(Room room : firstFloor.getRoomList()){
            stuffList.addAll(room.getStuffList());
        }
        for(Room room : secondFloor.getRoomList()){
            stuffList.addAll(room.getStuffList());
        }
        return stuffList;
    }

    /**
     * Normal getter.
     * @return kitchen as Kitchen room type
     */
    public Kitchen getKitchen(){
        return (Kitchen) this.firstFloor.getRoomList().get(1);
    }

    public Floor getSecondFloor(){
        return secondFloor;
    }
}
