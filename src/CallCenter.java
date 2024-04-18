import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

public class CallCenter {

    /*
     * N is the total number of customers that each agent will serve in
     * this simulation.
     * (Note that an agent can only serve one customer at a time.)
     */
    private static final int CUSTOMERS_PER_AGENT = 5;

    /*
     * NUMBER_OF_AGENTS specifies the total number of agents.
     */
    private static final int NUMBER_OF_AGENTS = 3;

    /*
     * NUMBER_OF_CUSTOMERS specifies the total number of customers to create
     * for this simulation.
     */
    private static final int NUMBER_OF_CUSTOMERS = NUMBER_OF_AGENTS * CUSTOMERS_PER_AGENT;

    /*
     * NUMBER_OF_THREADS specifies the number of threads to use for this simulation.
     * (The number of threads should be greater than the number of agents and
     * greeter combined
     * to allow simulating multiple concurrent customers.)
     */
    private static final int NUMBER_OF_THREADS = 10;

    /*
     * Create queues
     */
    private static Queue<Customer> waitQueue = new LinkedList<>();
    private static Semaphore waitLock = new Semaphore(1, true);
    private static Queue<Customer> dispatchQueue = new LinkedList<>();
    private static ReentrantLock displatchLock = new ReentrantLock();

    /*
     * Create the greeter and agents threads first, and then create the customer
     * threads.
     */
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        try {
            for (int i = 0; i < NUMBER_OF_AGENTS; i++) {
                es.submit(new Agent((int) Math.random() + 1111 * 9999));
                sleep(ThreadLocalRandom.current().nextInt(0, 150));
            }

            for (int i = 0; i < NUMBER_OF_CUSTOMERS; i++) {
                es.submit(new Customer((int) Math.random() + 1111 * 9999));
                sleep(ThreadLocalRandom.current().nextInt(0, 150));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert a random sleep between 0 and 150 miliseconds after submitting every
        // customer task,
        // to simulate a random interval between customer arrivals.

    }

    /*
     * The Agent class.
     */
    public static class Agent implements Runnable {

        // The ID of the agent.
        private final int ID;

        // Feel free to modify the constructor
        public Agent(int i) {
            ID = i;
        }

        /*
         * Your Agent implementation must call the method below
         * to serve each customer.
         * Do not modify this method.
         */
        public void serve(int customerID) {
            System.out.println("Agent " + ID + " is serving customer " + customerID);
            try {
                /*
                 * Simulate busy serving a customer by sleeping for a random amount of time.
                 */
                sleep(ThreadLocalRandom.current().nextInt(10, 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            for (int i = 0; i < CUSTOMERS_PER_AGENT; i++) {
                serve(dispatchQueue.remove().ID);
            }
        }

    }

    /*
     * The greeter class.
     * To greet a customer, tell the customer their current position in the dispatch
     * queue.
     */
    public static class Greeter implements Runnable {

        public Greeter(Customer c) {
            this.customer = c;
        }

        @Override
        public void run() {
            for (int i = 0; i < NUMBER_OF_CUSTOMERS; i++) {
                dispatchQueue.add(waitQueue.remove());
                System.out.println(dispatchQueue.size());
            }
        }

    }

    /*
     * The customer class.
     */
    public static class Customer implements Runnable {

        // The ID of the customer.
        private final int ID;

        // Feel free to modify the constructor
        public Customer(int i) {
            ID = i;
        }

        @Override
        public void run() {
            waitQueue.add(this);
        }
    }

}
