package uz.bakhromjon;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread olivia = new MyThread();
        Thread barron = new MyThread();
        olivia.start();
        barron.start();
        olivia.join();
        barron.join();
        System.out.println("We should by " + MyThread.garlicCount + " garlic.");
        System.out.println("We should by " + MyThread.potatoCount + " potato.");
    }

}

class MyThread extends Thread {
    ReentrantLock pencil = new ReentrantLock();
    static int garlicCount = 0;
    static int potatoCount = 0;

    public void incGarlic() {
        pencil.lock();
        incPotato();
        garlicCount++;
        pencil.unlock();
    }

    public void incPotato() {
        pencil.lock();
        potatoCount++;
        pencil.unlock();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10_000_000; i++) {
            incGarlic();
//            incPotato();
        }
    }
}