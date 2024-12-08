public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final int customerRetrievalRate;
    private final int customerId;

    public Customer(TicketPool ticketPool, int customerId, int customerRetrievalRate) {
        this.ticketPool = ticketPool;
        this.customerId = customerId;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000); // Simulate time delay for purchases
                ticketPool.removeTicket(customerId, customerRetrievalRate);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Customer " + customerId + " was interrupted.");
            }
        }
    }
}
