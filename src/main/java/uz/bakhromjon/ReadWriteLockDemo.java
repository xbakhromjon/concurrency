package uz.bakhromjon;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new CalendarUser("Reader-" + i).start();
        }
        for (int i = 0; i < 2; i++) {
            new CalendarUser("Writer-" + i).start();
        }
    }
}

class CalendarUser extends Thread {
    private static String[] WEEKDAYS = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
    private static int today = 0;
    private static ReentrantReadWriteLock marker = new ReentrantReadWriteLock();
    private static Lock readMarker = marker.readLock();
    private static Lock writeMarker = marker.writeLock();

    public CalendarUser(String name) {
        this.setName(name);
    }

    @Override
    public void run() {
        while (today < WEEKDAYS.length - 1) {
            if (this.getName().contains("Writer")) {
                writeMarker.lock();
                today++;
                try {
                    System.out.println(this.getName() + " update date to " + WEEKDAYS[today]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                writeMarker.unlock();
            } else {
                readMarker.lock();
                try {
                    System.out.println(this.getName() + " sees that today is " + WEEKDAYS[today]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                readMarker.unlock();
            }
        }
    }
}
