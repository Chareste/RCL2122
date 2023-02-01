package Lez2;

import java.util.Random;

public class Traveler implements Runnable {
    Random rand = new Random();
    int wait;
    private int id;

    public long getId() {
        return this.id;
    }

    public void run() {
        System.out.printf("Viaggiatore %s: sto acquistando un biglietto\n", this.id);
        wait = rand.nextInt(1000);
        try {
            Thread.sleep(wait);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("Viaggiatore %s: ho acquistato un biglietto\n", this.id);
    }
}


