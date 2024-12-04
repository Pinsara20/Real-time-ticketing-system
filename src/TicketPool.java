import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketPool {
    private final List<String> tickets;
    private final int maxTicketCapacity;

    public TicketPool(List<String> tickets, int maxTicketCapacity) {
        this.tickets = tickets;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public synchronized void addTicket(String ticket) throws InterruptedException {
        while (tickets.size() >= maxTicketCapacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        }
        tickets.addAll(tickets);
        notifyAll();
    }

    public synchronized String removeTicket() throws InterruptedException {
        while (tickets.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        String ticket = tickets.remove(0);
        notifyAll();
        return ticket;
    }


    public synchronized int getTickets() {
        return tickets.size();
    }
}
