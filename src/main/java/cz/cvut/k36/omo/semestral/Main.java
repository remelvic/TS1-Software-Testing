package cz.cvut.k36.omo.semestral;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.k36.omo.semestral.home.Home;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        Scanner sc = new Scanner(System.in);

        System.out.println("Write json config file path: ");
        String configFilePath = sc.next();

        Config config = null;
        try {
            config = mapper.readValue(new File(configFilePath), Config.class);
        }catch (IOException e){
            System.out.println(e);
            exit(1);
        }
        System.out.println("Write report file name: ");
        String filename = sc.next();

        Home home = Home.getInstance(config);
        Simulation simulation = new Simulation(365,home,filename);
        simulation.runSimulation();
        simulation.getReportAPI().addReportToFile();
    }
}
