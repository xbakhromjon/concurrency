package uz.bakhromjon;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockDemo {
    public static void main(String[] args) {
        Lock chopStickA = new ReentrantLock();
        Lock chopStickB = new ReentrantLock();
        Lock chopStickC = new ReentrantLock();
        new Philosopher("Olivia", chopStickA, chopStickB).start();
        new Philosopher("Barron", chopStickB, chopStickC).start();
        new Philosopher("Steve", chopStickA, chopStickC).start();
    }
}

class Philosopher extends Thread {
    private Lock firstChopstick, secondChopstick;
    private static int sushiCount = 500_000;

    public Philosopher(String name, Lock firstChopstick, Lock secondChopstick) {
        super(name);
        this.firstChopstick = firstChopstick;
        this.secondChopstick = secondChopstick;
    }


    @Override
    public void run() {
        while (sushiCount > 0) {
            firstChopstick.lock();
            secondChopstick.lock();
            if (sushiCount > 0) {
                sushiCount--;
                System.out.println(this.getName() + " took a piece. Sushi remaining " + sushiCount);
            }
            firstChopstick.unlock();
            secondChopstick.unlock();
        }
    }
}
