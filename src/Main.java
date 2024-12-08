import java.util.Scanner;

public class Main {
    private static Thread[] vendorThreads;
    private static Thread[] customerThreads;

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.loadConfiguration();
        TicketPool ticketPool = new TicketPool(configuration.getTotalTickets(), configuration.getMaxTicketCapacity());

        // Create and start vendor thread
        int vendorCount = configuration.getTicketReleaseRate();
        vendorThreads = new Thread[vendorCount];
        for (int i = 0; i < vendorCount; i++) {
            Vendor vendor = new Vendor(ticketPool, i + 1, configuration.getTicketReleaseRate());
            vendorThreads[i] = new Thread(vendor);
            vendorThreads[i].start();
        }

        // Create and start customer thread
        int customerCount = configuration.getCustomerRetrievalRate();
        customerThreads = new Thread[customerCount];
        for (int i = 0; i < customerCount; i++) {
            Customer customer = new Customer(ticketPool, i + 1, configuration.getCustomerRetrievalRate());
            customerThreads[i] = new Thread(customer);
            customerThreads[i].start();
        }

        // Run for a specified duration (e.g., 30 seconds for demonstration)
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Stop threads gracefully
        try {
            vendorThreads.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            customerThreads.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}





