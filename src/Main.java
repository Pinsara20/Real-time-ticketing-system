import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = null;
        try{
            configuration = new Configuration();
            configuration.loadConfiguration();
        }catch (Exception e){
            System.out.println("An error occurred while loading the configuration.");
            System.exit(1);
        }

        TicketPool ticketPool = new TicketPool(new ArrayList<>(), configuration.getMaxTicketCapacity());

        ArrayList<Thread> threads = new ArrayList<>();

        int vendor = configuration.getTicketReleaseRate();
        for (int i = 0; i < vendor; i++) {
            Vendor v = new Vendor(ticketPool, i, configuration.getTicketReleaseRate());
            Thread t = new Thread(v);
            threads.add(t);
            t.start();
        }

        int customer = configuration.getCustomerRetrievalRate();
        for (int i = 0; i < customer; i++) {
            Customer c = new Customer(ticketPool, i, configuration.getCustomerRetrievalRate());
            Thread t = new Thread(c);
            threads.add(t);
            t.start();
        }




    }
}