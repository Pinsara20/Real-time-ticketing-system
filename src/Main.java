import java.io.File;
import java.util.Scanner;

public class Main {
    private static Thread[] vendorThreads;
    private static Thread[] customerThreads;
    private static boolean running = true;

    public static void main(String[] args) {
        String configFilePath = "configuration.json";
        Configuration configuration;

        // Check if configuration file exists
        File configFile = new File(configFilePath);
        if (configFile.exists()) {
            // Ask user if they want to use existing configuration
            Scanner scanner = new Scanner(System.in);
            String choice;
            while (true) {
                System.out.println("A configuration file was found. Do you want to use it? (yes/no): ");
                choice = scanner.nextLine().trim().toLowerCase();
                if ("yes".equals(choice) || "no".equals(choice)) {
                    break; // Valid input
                } else {
                    System.out.println("Invalid input. Please type 'yes' or 'no'.");
                }
            }

            if ("yes".equals(choice)) {
                configuration = Configuration.loadConfigurationFromFile(configFilePath);
                if (configuration == null) {
                    System.out.println("Failed to load configuration. Please enter new configuration data.");
                    configuration = new Configuration();
                    configuration.loadConfiguration();
                    configuration.saveConfigurationToFile(configFilePath);
                }
            } else {
                // Load new configuration from user input
                configuration = new Configuration();
                configuration.loadConfiguration();
                configuration.saveConfigurationToFile(configFilePath);
            }
        } else {
            // Load new configuration from user input if file does not exist
            configuration = new Configuration();
            configuration.loadConfiguration();
            configuration.saveConfigurationToFile(configFilePath);
        }

        // Load the configuration for the TicketPool
        TicketPool ticketPool = new TicketPool(configuration.getTotalTickets(), configuration.getMaxTicketCapacity());

        // Create and start vendor threads
        int vendorCount = configuration.getTicketReleaseRate();
        vendorThreads = new Thread[vendorCount];
        for (int i = 0; i < vendorCount; i++) {
            Vendor vendor = new Vendor(ticketPool, i + 1, configuration.getTicketReleaseRate());
            vendorThreads[i] = new Thread(vendor);
            vendorThreads[i].start();
        }

        // Create and start customer threads
        int customerCount = configuration.getCustomerRetrievalRate();
        customerThreads = new Thread[customerCount];
        for (int i = 0; i < customerCount; i++) {
            Customer customer = new Customer(ticketPool, i + 1, configuration.getCustomerRetrievalRate());
            customerThreads[i] = new Thread(customer);
            customerThreads[i].start();
        }

        // Monitor user input for stopping threads
        Thread monitorThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (running) {
                System.out.println("Enter '1' to stop the program: ");
                if (scanner.hasNextInt() && scanner.nextInt() == 1) {
                    running = false;
                } else {
                    System.out.println("Invalid input. Please enter '1' to stop the program.");
                    scanner.nextLine(); // Clear invalid input
                }
            }

        });
        monitorThread.start();

        // Wait for the program to stop
        while (running) {
            try {
                Thread.sleep(1000); // Check periodically
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Stop vendor and customer threads
        stopThreads(vendorThreads);
        stopThreads(customerThreads);

        System.out.println("Program stopped.");
    }

    private static void stopThreads(Thread[] threads) {
        for (Thread thread : threads) {
            if (thread != null && thread.isAlive()) {
                thread.interrupt();
            }
        }
    }
}
