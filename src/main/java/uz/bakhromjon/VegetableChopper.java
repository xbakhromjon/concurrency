package uz.bakhromjon;

public class VegetableChopper extends Thread {
    public int vegetableCount = 0;
    public static boolean chopping = true;

    public VegetableChopper(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (chopping) {
            System.out.println(this.getName() + " chopped a vegetable!");
            vegetableCount++;
        }
    }
}

class ExecutionSchedullingDemo {
    public static void main(String[] args) throws InterruptedException {
        VegetableChopper olivia = new VegetableChopper("Olivia");
        VegetableChopper barron = new VegetableChopper("Barron");
        olivia.start();
        barron.start();
        Thread.sleep(1000);
        VegetableChopper.chopping = false;

        olivia.join();
        barron.join();
        System.out.format("Barron chopped %d vegetables. \n", barron.vegetableCount);
        System.out.format("Olivia chopped %d vegetables. \n", olivia.vegetableCount);
    }
}
