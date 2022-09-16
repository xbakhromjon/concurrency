package uz.bakhromjon;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicVariableDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread olivia = new Shopper1("Olivia");
        Thread barron = new Shopper1("Barron");
        olivia.start();
        barron.start();
        olivia.join();
        barron.join();
        System.out.println("We should by " + Shopper1.atomicInteger.get() + " garlic.");
    }
}

class Shopper1 extends Thread {
    static AtomicInteger atomicInteger = new AtomicInteger(0);

    public Shopper1(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10_000_000; i++) {
            atomicInteger.incrementAndGet();
        }
    }
}