import java.util.concurrent.atomic.AtomicInteger;

public class Customer implements Runnable {
    private final int customerId;
    private final TicketPool ticketPool;
    private final int customerRetrievalRate;

    private boolean running = true;

    public Customer(TicketPool ticketPool, int customerId, int customerRetrievalRate) {
        this.ticketPool = ticketPool;
        this.customerId = customerId;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    @Override
    public void run() {
        while(running) {
            try {
                Thread.sleep(1000);
                for (int i = 0; i < customerRetrievalRate; i++) {
                    String ticket = ticketPool.removeTicket();
                    if (ticket != null) {
                        System.out.println("Customer " + customerId + " attempted to purchase a ticket, but there are no tickets remaining.");
                        ticketPool.addTicket(ticket);
                        continue;
                    }else{
                        System.out.println("All tickets have been sold. The ticket pool is now empty.");
                    }
                }
                System.out.println("Customer " + customerId + " purchased 1 ticket. Total tickets remaining: " + ticketPool.getTickets());
            }catch(InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Customer " + customerId + " was interrupted.");
            }
        }
        System.out.println("Customer " + customerId + " has stopped.");
    }
    public void stop() {
        running = false;
    }


}
