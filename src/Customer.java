public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final int customerRetrievalRate;
    private final int customerId;
    private boolean running = true;

    public Customer(TicketPool ticketPool, int customerId, int customerRetrievalRate) {
        this.ticketPool = ticketPool;
        this.customerId = customerId;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(1000); // Simulate time delay for purchases

                int ticketsPurchased = customerRetrievalRate;
                ticketPool.removeTicket(customerId, ticketsPurchased);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Customer " + customerId + " was interrupted.");
            }
        }
    }

    public void stop() {
        running = false;
    }
}
