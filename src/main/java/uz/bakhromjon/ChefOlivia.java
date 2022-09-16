package uz.bakhromjon;

public class ChefOlivia implements Runnable {
    @Override
    public void run() {
        System.out.println("Olivia started & waiting for sausage to thaw...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Olivia is done cutting sausage.");
    }
}

class ThreadLifeCycleDemo {
    public static void main(String[] args) throws InterruptedException {
        StringBuilder as = new StringBuilder("as");
        as.append(12);
        System.out.println(as);
        System.out.println("Barron started & requesting Olivia's help.");
        Thread olivia = new Thread(new ChefOlivia());
        System.out.println("Olivia state: " + olivia.getState().name());

        System.out.println("Barron tells Olivia to start.");
        olivia.start();
        System.out.println("Olivia state: " + olivia.getState().name());

        System.out.println("Barron continues cooking soup.");
        Thread.sleep(500);
        System.out.println("Olivia state: " + olivia.getState().name());

        System.out.println("Barron patiently waits for Olivia to finish and join...");
        olivia.join();
        System.out.println("Olivia state: " + olivia.getState().name());

        System.out.println("Barron and Olivia both done!");
    }
}