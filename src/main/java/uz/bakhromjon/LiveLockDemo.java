package uz.bakhromjon;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LiveLockDemo {
    public static void main(String[] args) {
        Lock chopStickA = new ReentrantLock();
        Lock chopStickB = new ReentrantLock();
        Lock chopStickC = new ReentrantLock();
        new Philosopher4("Olivia", chopStickA, chopStickB).start();
        new Philosopher4("Barron", chopStickB, chopStickC).start();
        new Philosopher4("Steve", chopStickA, chopStickC).start();
    }
}

class Philosopher4 extends Thread {
    private Lock firstChopstick, secondChopstick;
    private static int sushiCount = 500;

    public Philosopher4(String name, Lock firstChopstick, Lock secondChopstick) {
        super(name);
        this.firstChopstick = firstChopstick;
        this.secondChopstick = secondChopstick;
    }


    @Override
    public void run() {
        while (sushiCount > 0) {
            firstChopstick.lock();
            if (!secondChopstick.tryLock()) {
                System.out.println(this.getName() + " released firstChopstick.");
                firstChopstick.unlock();
            } else {
                try {
                    if (sushiCount > 0) {
                        sushiCount--;
                        System.out.println(this.getName() + " took a piece. Sushi remaining " + sushiCount);
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
}
