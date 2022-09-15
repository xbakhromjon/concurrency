package uz.bakhromjon;

public class KitchenCleaner implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("Olivia cleaned the kitchen.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class DaemonThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread olivia = new Thread(new KitchenCleaner());
        olivia.setDaemon(true);
        olivia.start();

        System.out.println("Barron is cooking...");
        Thread.sleep(600);

        System.out.println("Barron is cooking...");
        Thread.sleep(600);

        System.out.println("Barron is cooking...");
        Thread.sleep(600);

        System.out.println("Barron is done.");
    }
}
