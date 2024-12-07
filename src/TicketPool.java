import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private final int maxTicketCapacity;
    private final Queue<String> tickets = new LinkedList<>();
    private int totalTickets;
    private int id;

    public TicketPool(int maxTicketCapacity, int initialTotalTickets) {
        this.maxTicketCapacity = maxTicketCapacity;
        this.totalTickets = initialTotalTickets;

        for (int i = 0; i < initialTotalTickets; i++) {
            tickets.add("Ticket"+totalTickets+1);
        }
    }

    public synchronized void addTicket(int vendorId, int addTickets) {
        if (tickets.size() + addTickets <= maxTicketCapacity) {
            for (int i = 0; i < addTickets; i++) {
                tickets.add("Ticket"+ totalTickets+1);
                totalTickets++;
            }
        }else {
            System.out.println("Vendor " + vendorId + " attempted to add tickets, but the pool is full.");
        }
    }

    public synchronized void removeTicket(int customerId, int removeTickets) {
        int ticketPurchesed = 0;
        while (ticketPurchesed < removeTickets && !tickets.isEmpty()) {
            tickets.poll();
            ticketPurchesed++;
            removeTickets--;
        }
        if (ticketPurchesed > 0) {
            System.out.println("Customer " + customerId + " purchased " + ticketPurchesed + " ticket(s). Remaining tickets in pool: " + tickets.size());
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
