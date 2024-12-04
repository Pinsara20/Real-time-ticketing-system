import java.util.ArrayList;
import java.util.List;

public class Vendor implements Runnable {
    private final int vendorId;
    private final TicketPool ticketPool;
    private final int ticketReleaseRate;

    private boolean running = true;

    public Vendor(TicketPool ticketPool, int vendorId, int ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.vendorId = vendorId;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    @Override
    public void run() {
        System.out.println("Vendor " + vendorId + " is now running.");
        while(running) {
            try {
                Thread.sleep(1000);

                List<String> newTicket = new ArrayList<>();
                for (int i = 0; i < ticketReleaseRate; i++) {
                    newTicket.add("Ticket " + (ticketPool.getTickets() + 1));
                }
                ticketPool.addTicket(newTicket.toString());
                System.out.println("Vendor " + vendorId + " added 1 ticket. Total tickets remaining: " + ticketPool.getTickets());
            }catch(InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Vendor " + vendorId + " was interrupted.");
            }
        }
        System.out.println("Vendor " + vendorId + " has stopped.");
    }
    public void stop() {
        running = false;
    }
}
