package uz.bakhromjon;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Shopper extends Thread {
    static int garlicCount = 0;
    static Lock lock = new ReentrantLock();

    public Shopper(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            lock.lock();
            garlicCount++;
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " is thinking.");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class DataRaceDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread olivia = new Shopper("Olivia");
        Thread barron = new Shopper("Barron");
        olivia.start();
        barron.start();
        olivia.join();
        barron.join();
        System.out.println("We should by " + Shopper.garlicCount + " garlic.");
    }
}
