package cz.cvut.k36.omo.semestral.report.actions;

import cz.cvut.k36.omo.semestral.HomeUtils;
import cz.cvut.k36.omo.semestral.home.rooms.Room;
import cz.cvut.k36.omo.semestral.inmates.peoples.*;
import cz.cvut.k36.omo.semestral.stuff.Stuff;
import cz.cvut.k36.omo.semestral.stuff.StuffAPI;
import cz.cvut.k36.omo.semestral.stuff.usable.FoodType;
import cz.cvut.k36.omo.semestral.stuff.usable.Fridge;
import cz.cvut.k36.omo.semestral.stuff.usable.Usable;
import cz.cvut.k36.omo.semestral.stuff.usable.microwave.Microwave;
import cz.cvut.k36.omo.semestral.stuff.usable.microwave.MicrowaveContext;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * The class creates an action when someone eats.
 * @author Victor Remel
 * @author Roman Bulavkin
 * @version 1.0, December 2021
 */
public class Eat extends Action {
    private final Person person;
    private final StuffAPI stuffAPI;
    private Stuff currStuff;
    private String food;

    public Eat(int time, Room room, Person person, StuffAPI stuffAPI) {
        super(ActionType.EAT, time,  room, person);
        this.stuffAPI = stuffAPI;
        this.person = (Person)this.getInhabitant();
        this.eat(time);
    }

    /**
     * When the method is called, the residents of the house will have an increase in their satiety.
     */
    private void eat(int time){
        HomeUtils.changeRoom(this.person, this.stuffAPI.getKitchen());

        for(Stuff fridge : this.person.getCurrentRoom().getStuffList()){
            if (fridge instanceof Fridge){
                if (((Fridge) fridge).isEmpty()){
                    this.cook(time);
                }else{
                    food = ((Fridge) fridge).takeFood();
                    fridge.notifySubscribers(new Use(time , this.person.getCurrentRoom(), this.person,
                            (Usable) fridge, this.stuffAPI));
                    fridge.notifySubscribers(new TakeFood(time,this.person.getCurrentRoom(), this.person,
                            food, fridge));
                    currStuff = fridge;
                }
            }
        }
        if (this.person instanceof Child){
            this.person.setSatiety(this.person.getSatiety()+33);
        }else if(this.person instanceof Dad){
            this.person.setSatiety(this.person.getSatiety()+29);
        }else if (this.person instanceof Grandma){
            this.person.setSatiety(this.person.getSatiety()+19);
        }else if(this.person instanceof Grandpa){
            this.person.setSatiety(this.person.getSatiety()+23);
        }else if(this.person instanceof Mom){
            this.person.setSatiety(this.person.getSatiety()+36);
        }
    }

    /**
     * The method interacts with the microwave (reheats food).
     */
    private void cook(int time){
        MicrowaveContext microwaveContext = new MicrowaveContext();
        for (Stuff microwave : this.person.getCurrentRoom().getStuffList()){
            if (microwave instanceof Microwave){
                food = randomFood();
                microwaveContext.setMicrowave((Microwave) microwave);
                microwaveContext.getMicrowave().notifySubscribers(this.stuffAPI.useStuff(this.person,
                        time, microwaveContext.getMicrowave()));
                microwaveContext.putFood(food);
                microwaveContext.getMicrowave().notifySubscribers(new PutFood(time, this.person.getCurrentRoom(),
                        this.person,food ,microwaveContext.getMicrowave()));
                microwaveContext.takeFood();
                microwaveContext.getMicrowave().notifySubscribers(new TakeFood(time + 2,
                        this.person.getCurrentRoom(), this.person,food ,microwaveContext.getMicrowave()));
                microwaveContext.stopUsing(this.person);
            }
            currStuff = microwave;
        }
    }

    /**
     * Normal getter.
     * @return food as String type
     */
    public String getFood(){
        return this.food;
    }

    /**
     * The method will choose a random food from the available ones.
     * @return the name of the food as a string
     */
    public String randomFood(){
        List<FoodType> foodTypes = Arrays.asList(FoodType.values());
        Random rand = new Random();
        return (foodTypes.get(rand.nextInt(foodTypes.size()))).toString();
    }

    /**
     * Normal getter.
     * @return stuff as Stuff type
     */
    public Stuff getStuff(){
        return this.currStuff;
    }
}
