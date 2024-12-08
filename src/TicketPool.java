import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private final int maxTicketCapacity;
    private final Queue<String> tickets = new LinkedList<>();
    private int totalTickets;

    public TicketPool(int maxTicketCapacity, int initialTotalTickets) {
        this.maxTicketCapacity = maxTicketCapacity;
        this.totalTickets = 0;

        for (int i = 0; i < initialTotalTickets; i++) {
            tickets.add("Ticket" + (totalTickets + 1));
            totalTickets++;
        }
    }

    public synchronized void addTicket(int vendorId, int addTickets) {
        if (tickets.size() + addTickets <= maxTicketCapacity) {
            for (int i = 0; i < addTickets; i++) {
                tickets.add("Ticket" + (totalTickets + 1));
                totalTickets++;
            }
            System.out.println("Vendor " + vendorId + " added " + addTickets + " ticket(s). Total tickets in pool: " + tickets.size());
        } else {
            System.out.println("Vendor " + vendorId + " attempted to add tickets, but the pool is full.");
        }
    }

    public synchronized void removeTicket(int customerId, int removeTickets) {
        LinkedList<String> purchasedTickets = new LinkedList<>();
        int ticketsPurchased = 0;

        while (ticketsPurchased < removeTickets && !tickets.isEmpty()) {
            purchasedTickets.add(tickets.poll());
            ticketsPurchased++;
        }

        if (!purchasedTickets.isEmpty()) {
            System.out.println("Customer " + customerId + " purchased " + ticketsPurchased + " ticket(s): " + purchasedTickets + ". Remaining tickets in pool: " + tickets.size());
        } else {
            System.out.println("Customer " + customerId + " attempted to purchase tickets, but none were available.");
        }
    }

    public synchronized boolean canAddTicket() {
        return tickets.size() < maxTicketCapacity;
    }

    public synchronized int getTickets() {
        return tickets.size();
    }
}
