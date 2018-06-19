package eu.poland.trafficsimulator;

import eu.poland.threading.SimulationController;
import eu.poland.jms.Producer;
import java.util.Scanner;
import java.util.Set;
import javax.jms.JMSException;

/**
 *
 * @author Robin
 */
public class Main {

    private static String activeMQIp = "192.168.25.35";

    private static SimulationController simulator;

    public static void main(String[] args) throws JMSException {
        System.out.println("Connecting to ActiveMQ server. . .");
        Producer msgQueueSender = new Producer("tcp://" + activeMQIp + ":61616", "admin", "secret");
        msgQueueSender.setup("TrafficQueue");

        System.out.println("\nSimulator is starting. . .\n");
        simulator = new SimulationController(msgQueueSender);

        showMainMenu();
        Scanner sc = new Scanner(System.in);
        String input = "";
        boolean stopServer = false;
        while (!stopServer) {
            input = sc.nextLine();
            switch (input) {
                case "info":
                    System.out.println("The server is currently ONLINE.\n"
                            + "You were able to enter a command, did you really expect anything else?");
                    break;
                case "spawn":
                    System.out.println("Please enter a number of simulations to spawn: ");
                    int sims = sc.nextInt();
                    // skip the rest of this line
                    sc.nextLine();
                    if (sims > 1) {
                        for (int i = 0; i < sims; i++) {
                            simulator.startNewSim();
                        }
                    } else if (sims == 1) {
                        System.out.println("Please enter a Cartracker ID (leave empty for random): ");
                        input = sc.nextLine();
                        if (input.equals("")) {
                            simulator.startNewSim();
                        } else {
                            simulator.startNewSim(input);
                        }
                        System.out.println("A new simulation thread has been spawned.");
                    }
                    break;
                case "count":
                    int count = simulator.getSimCount();
                    System.out.printf("Currently live simulation threads: %s\n", count);
                    break;
                case "list":
                    Set<String> trackerIds = simulator.getSimTrackerIds();
                    System.out.printf("Currently live trackers:\n%s\n", trackerIds);
                    break;
                case "stop":
                    System.out.println("\nSimulator is stopping. . .");
                    stopServer = true;
                    simulator.stop(0);
                    break;
                case "help":
                    showMainMenu();
                    break;
                default:
                    System.out.println("Invalid input, type 'help' for a list of commands.");
                    break;
            }
        }
    }

    private static void showMainMenu() {
        System.out.println(""
                + "----------------------------------------------------------------------------------------------------\n"
                + "info\t\tShow current status of the Simulator\n"
                + "spawn\t\tSpawn new simulation thread(s)\n"
                + "count\t\tShow the amount of currently active simulations\n"
                + "list\t\tShow the trackerIds of the currently active simulations\n"
                + "stop\t\tStop the simulator\n"
                + "help\t\tShow this list\n"
                + "----------------------------------------------------------------------------------------------------\n");
    }
}
