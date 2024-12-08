public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int ticketReleaseRate;
    private final int vendorId;
    private boolean running = true;

    public Vendor(TicketPool ticketPool, int vendorId, int ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.vendorId = vendorId;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(1000); // Simulate time delay for adding tickets

                ticketPool.addTicket(vendorId, ticketReleaseRate);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Vendor " + vendorId + " was interrupted.");
            }
        }
    }

    public void stop() {
        running = false;
    }
}
