import java.util.Scanner;

public class Main {
    private static Thread[] vendorThreads;
    private static Thread[] customerThreads;
    private static boolean running = true;

    public static void main(String[] args) {
        Configuration configuration = new Configuration();

        // Load configuration from user input
        configuration.loadConfiguration();

        // Save configuration to a file
        String configFilePath = "configuration.json";
        configuration.saveConfigurationToFile(configFilePath);

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
                if (scanner.nextInt() == 1) {
                    running = false;
                }
            }
            scanner.close();
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
