package eu.poland.trafficsimulator;

import eu.poland.threading.SimulationController;
import eu.poland.jms.Producer;
import java.util.Scanner;
import javax.jms.JMSException;

/**
 *
 * @author Robin
 */
public class Main {

    private static String activeMQIp = "192.168.25.14";

    private static SimulationController simulator;

    public static void main(String[] args) throws JMSException {
        System.out.println("Loading properties file. . .");

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
                    simulator.startNewSim();
                    System.out.println("A new simulation thread has been spawned.");
                    break;
                case "count":
                    int count = simulator.getSimCount();
                    System.out.printf("Currently live simulation threads: %s\n", count);
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

        /*System.out.println(getJsonRoute(new LatLng(52.0828121, 17.0008908), new LatLng(51.8774911, 17.0028028)));
        DirectionsRoute route = getRoute(new LatLng(52.0828121, 17.0008908), new LatLng(51.8774911, 17.0028028)).routes[0];
        Ride ride = new Ride(route);
        System.out.println(ride.getCurrentLocation());
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    try {
                        System.out.println(ride.progress());
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();

        try {
            for (double i = 0; i < 3; i++) {
                double offset = i / 10;
                String output = getJsonRoute(new LatLng(52.0828121 + offset, 17.0008908 + offset), new LatLng(51.8774911 + offset, 17.0028028 + offset));

                msgQueueSender.sendMessage(output);
            }
        } catch (JMSException jex) {
            jex.printStackTrace();
        } finally {
            msgQueueSender.close();
        }*/
    }

    private static void showMainMenu() {
        System.out.println(""
                + "----------------------------------------------------------------------------------------------------\n"
                + "info\t\tShow current status of the Simulator\n"
                + "spawn\t\tSpawn a new simulation thread\n"
                + "count\t\tShow the amount of currently active simulations\n"
                + "stop\t\tStop the simulator\n"
                + "----------------------------------------------------------------------------------------------------\n");
    }
}
