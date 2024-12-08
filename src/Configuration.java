import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Configuration {
    private int totalTickets; // Initial tickets in the pool
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    public int getTotalTickets() {
        return totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void loadConfiguration() {
        Scanner scanner = new Scanner(System.in);

        // Get the initial tickets count
        while (true) {
            try {
                System.out.println("Enter the initial total number of tickets in the pool: ");
                this.totalTickets = scanner.nextInt();
                if (this.totalTickets <= 0) {
                    throw new IllegalArgumentException("Invalid input. Please enter a positive integer.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.next();
            }
        }

        // Get the maximum ticket capacity
        while (true) {
            try {
                System.out.println("Enter the maximum ticket capacity for all vendors combined: ");
                this.maxTicketCapacity = scanner.nextInt();
                if (this.maxTicketCapacity <= 0) {
                    throw new IllegalArgumentException("Invalid input. Please enter a positive integer.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.next();
            }
        }

        // Get the ticket release rate
        while (true) {
            try {
                System.out.println("Enter the ticket release rate (tickets per vendor per second): ");
                this.ticketReleaseRate = scanner.nextInt();
                if (this.ticketReleaseRate <= 0) {
                    throw new IllegalArgumentException("Invalid input. Please enter a positive integer.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.next();
            }
        }

        // Get the customer retrieval rate
        while (true) {
            try {
                System.out.println("Enter the customer retrieval rate (tickets per customer per second): ");
                this.customerRetrievalRate = scanner.nextInt();
                if (this.customerRetrievalRate <= 0) {
                    throw new IllegalArgumentException("Invalid input. Please enter a positive integer.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.next();
            }
        }
    }

    public void saveConfigurationToFile(String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(this, writer);
            System.out.println("Configuration saved to " + filePath);
        } catch (IOException e) {
            System.out.println("Failed to save configuration: " + e.getMessage());
        }
    }

    public static Configuration loadConfigurationFromFile(String filePath) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, Configuration.class);
        } catch (IOException e) {
            System.out.println("Failed to load configuration: " + e.getMessage());
            return null;
        }
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "totalTickets=" + totalTickets +
                ", ticketReleaseRate=" + ticketReleaseRate +
                ", customerRetrievalRate=" + customerRetrievalRate +
                ", maxTicketCapacity=" + maxTicketCapacity +
                '}';
    }
}
