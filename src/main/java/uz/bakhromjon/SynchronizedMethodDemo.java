package uz.bakhromjon;

public class SynchronizedMethodDemo {
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

class Shopper2 extends Thread {
    static int garlicCount = 0;

    private synchronized void addGarlic() {
        garlicCount++;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10_000_000; i++) {
            addGarlic();
        }
    }
}
