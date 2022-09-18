package uz.bakhromjon;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StarvationDemo {
    public static void main(String[] args) {
        Lock chopStickA = new ReentrantLock();
        Lock chopStickB = new ReentrantLock();
        Lock chopStickC = new ReentrantLock();
        for (int i = 0; i < 500; i++) {
            new Philosopher3("Olivia", chopStickA, chopStickB).start();
            new Philosopher3("Barron", chopStickB, chopStickC).start();
            new Philosopher3("Steve", chopStickA, chopStickC).start();
        }
    }
}

class Philosopher3 extends Thread {
    private Lock firstChopstick, secondChopstick;
    private static int sushiCount = 500;
    private int eatenSushi = 0;

    public Philosopher3(String name, Lock firstChopstick, Lock secondChopstick) {
        super(name);
        this.firstChopstick = firstChopstick;
        this.secondChopstick = secondChopstick;
    }


    @Override
    public void run() {
        while (sushiCount > 0) {
            firstChopstick.lock();
            secondChopstick.lock();
            try {
                if (sushiCount > 0) {
                    sushiCount--;
                    eatenSushi++;
                    System.out.println(this.getName() + " took a piece. Sushi remaining " + sushiCount);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                firstChopstick.unlock();
                secondChopstick.unlock();
            }
        }
        System.out.println(this.getName() + " eaten " + eatenSushi + " sushi.");
    }
}
