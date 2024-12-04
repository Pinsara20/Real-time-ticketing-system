import java.util.Scanner;

public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    public void loadConfiguration() {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            try {
                System.out.println("Enter the maximum ticket capacity: ");
                this.maxTicketCapacity = scanner.nextInt();
                if (this.maxTicketCapacity <= 0) {
                    throw new IllegalArgumentException("Invalid input. Please enter a positive integer.");
                }
                break;
            }catch(NumberFormatException e) {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.next();
            }
        }
        while(true) {
            try {
                System.out.println("Enter the total number of tickets: ");
                this.totalTickets = scanner.nextInt();
                if (this.totalTickets <= 0) {
                    throw new IllegalArgumentException("Invalid input. Please enter a positive integer.");
                }
                break;
            }catch(NumberFormatException e) {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.next();
            }
        }
        while(true) {
            try {
                System.out.println("Enter the ticket release rate: ");
                this.ticketReleaseRate = scanner.nextInt();
                if (this.ticketReleaseRate <= 0) {
                    throw new IllegalArgumentException("Invalid input. Please enter a positive integer.");
                }
                break;
            }catch(NumberFormatException e) {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.next();
            }
        }
        while(true) {
            try {
                System.out.println("Enter the customer retrieval rate: ");
                this.customerRetrievalRate = scanner.nextInt();
                if (this.customerRetrievalRate <= 0) {
                    throw new IllegalArgumentException("Invalid input. Please enter a positive integer.");
                }
                break;
            }catch(NumberFormatException e) {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.next();
            }
        }
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }
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
