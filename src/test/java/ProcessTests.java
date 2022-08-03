import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.k36.omo.semestral.Config;
import cz.cvut.k36.omo.semestral.Simulation;
import cz.cvut.k36.omo.semestral.home.Home;
import cz.cvut.k36.omo.semestral.home.rooms.Bedroom;
import cz.cvut.k36.omo.semestral.home.rooms.Room;
import cz.cvut.k36.omo.semestral.inmates.peoples.Dad;
import cz.cvut.k36.omo.semestral.report.Report;
import cz.cvut.k36.omo.semestral.stuff.Stuff;
import cz.cvut.k36.omo.semestral.stuff.StuffAPI;
import cz.cvut.k36.omo.semestral.stuff.autonomic.Lamp;
import cz.cvut.k36.omo.semestral.stuff.usable.TV;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.System.exit;
import static org.junit.Assert.*;

public class ProcessTests {

    Home home;
    Simulation simulation;
    Simulation mockitoSimulation;
    int simulationTime;
    @Before
    public void init(){
        ObjectMapper mapper = new ObjectMapper();
        Config config = null;
        try {
            config = mapper.readValue(new File("smallHouse.json"), Config.class);
        }catch (IOException e){
            System.out.println(e);
            exit(1);
        }
        home = Home.getInstance(config);
        simulation = new Simulation(3, home, "ProcessTest");
        mockitoSimulation = Mockito.spy(simulation);
        simulationTime = (3 * 24) * 60;
        mockitoSimulation.getReportAPI().addReportToFile();
    }

    // 1st process Test
    @Test
    public void DadRepairStuffTrue(){
        Stuff tv = home.getSecondFloor().getRoomList().get(2).getStuffList().get(6);
        assertNotEquals(null, tv);
        ArrayList<Stuff> tvList = new ArrayList();
        if(tv instanceof TV){
            Stuff tvMock = Mockito.spy(tv);
            tvList.add(tvMock);
            while (true) {
                if (!tvMock.checkQuality()) {
                    Dad dad = (Dad) home.getInhabitantList().stream().filter(t -> t instanceof Dad).findFirst().get();
                    assertNotEquals(null, dad);
                    dad.findDocumentation(tvMock, 0);
                    assertNotEquals(null,mockitoSimulation.getReportAPI().
                            createFindDocReport(dad.findDocumentation(tvMock, 0)));
                    dad.readDocumentation(tvMock.getDocumentation(), 0, tvMock);
                    assertNotEquals(null,mockitoSimulation.getReportAPI().
                            createReadDocReport(dad.readDocumentation(tvMock.getDocumentation(), 0, tvMock)));
                    dad.callService(tvMock,new StuffAPI(tvList, home),0);
                    assertNotEquals(null,mockitoSimulation.getReportAPI().
                            createCallServiceReport(dad.callService(tvMock,new StuffAPI(tvList, home),0)));
                    dad.fix(tvMock, new StuffAPI(tvList, home), 10);

                    assertTrue(tvMock.checkQuality());
                    break;
                } else {
                    tvMock.decreaseQuality();
                }
            }
        }
    }

    // 2nd process Test
    @Test
    public void AllPeopleSleepInBedroomFail(){
        Simulation sim = new Simulation(3, home, "report");
        sim.runSimulation();
        ArrayList<Report> reports = sim.getReportAPI().getAllReportsWithoutPetAsArrayList();
        assertNotEquals(null, reports);
        for(Report report : reports){
            if(sim.calculateHours(report.getTime()) < 8 && sim.calculateHours(report.getTime()) >= 0 &&
                    !report.toString().contains("Food Courier") && report.toString().contains("Person type")){ //&&
                //!report.toString().contains("Dad") && !report.toString().contains("Repair service master")){
                assertEquals(true, report.toString().contains("Person type") && report.toString().contains("Bedroom"));
            }
        }
    }

    // 3rd Process Test
    @Test
    public void MeasureLampTest(){

        for (int timeHome = 0; timeHome < simulationTime; timeHome += 30){
            mockitoSimulation.updateSensors(timeHome);

            if((timeHome / 60 % 24) >= 8 && mockitoSimulation.getCurrentLightLevel() < 30){ // isTurnOn
                Lamp lamp = (Lamp) home.getStuffList().stream().filter(t -> t instanceof Lamp).findFirst().get();
                double LampConsumptionDayAfter = lamp.getElectricityConsumptionDay();
                lamp.changeState((timeHome / 60 % 24) >= 8 && mockitoSimulation.getCurrentLightLevel() < 30,
                        mockitoSimulation.getCurrentLightLevel(),timeHome / 60 % 24);
//                System.out.println("Current " + lamp.getElectricityConsumptionDay());
//                System.out.println("After " + LampConsumptionDayAfter);

                assertTrue(LampConsumptionDayAfter > lamp.getElectricityConsumptionDay());
            }
        }
    }

    // 4th Process Test
    @Test
    public void ChangeRoomTest(){
        StuffAPI stuffAPI = new StuffAPI(home.getStuffList(), home);
        assertNotEquals(null, stuffAPI);
        Room dadOldRoom = null;

        for (int timeHome = 0; timeHome < simulationTime; timeHome += 30) {
            Dad dad = (Dad) home.getInhabitantList().stream().filter(t -> t instanceof Dad).findFirst().get();
            assertNotEquals(null, dad);
            Bedroom bedroom = (Bedroom) home.getSecondFloor().getRoomList().stream().filter(t -> t instanceof Bedroom).findFirst().get();
            assertNotEquals(null, bedroom);

            if((timeHome/ 60) % 24 <= 8) {
                dadOldRoom = dad.getCurrentRoom();
            }

            home.chooseInhabitants(timeHome, stuffAPI);
            if((timeHome / 60) %24 >= 12 && dadOldRoom != bedroom) {
                assertNotEquals(dadOldRoom, dad.getCurrentRoom());
            }
        }
    }
}

