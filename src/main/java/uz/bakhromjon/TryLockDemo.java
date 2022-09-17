package uz.bakhromjon;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread olivia = new TryLockThread("Olivia");
        Thread barron = new TryLockThread("Barron");
        long start = System.currentTimeMillis();
        olivia.start();
        barron.start();
        olivia.join();
        barron.join();
        long finish = System.currentTimeMillis();
        System.out.println("Elapsed time: " + (finish - start)/1000.0);
    }
}

class TryLockThread extends Thread {
    private int itemsToAdd = 0;
    public static int itemOnNotepad = 0;

    Lock pencil = new ReentrantLock();

    public TryLockThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (itemOnNotepad <= 20) {
            if ((itemsToAdd > 0) && pencil.tryLock()) {
                try {
                    itemOnNotepad += itemsToAdd;
                    System.out.println(this.getName() + " added " + itemsToAdd + " items to notepad.");
                    itemsToAdd = 0;
                    Thread.sleep(300); // time spent writing
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    pencil.unlock();
                }
            } else {
                try {
                    Thread.sleep(100);   // time spent searching
                    itemsToAdd++;
                    System.out.println(this.getName() + " found something to buy.");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
