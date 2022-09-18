package uz.bakhromjon;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AbandonedLockDemo {
    public static void main(String[] args) {
        Lock chopStickA = new ReentrantLock();
        Lock chopStickB = new ReentrantLock();
        Lock chopStickC = new ReentrantLock();
        new Philosopher2("Olivia", chopStickA, chopStickB).start();
        new Philosopher2("Barron", chopStickB, chopStickC).start();
        new Philosopher2("Steve", chopStickA, chopStickC).start();
    }
}

class Philosopher2 extends Thread {
    private Lock firstChopstick, secondChopstick;
    private static int sushiCount = 500;
    public Philosopher2(String name, Lock firstChopstick, Lock secondChopstick) {
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
                    System.out.println(this.getName() + " took a piece. Sushi remaining " + sushiCount);
                }
                if (sushiCount == 10) {
                    System.out.println(1 / 0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                firstChopstick.unlock();
                secondChopstick.unlock();
            }
        }
    }
}
