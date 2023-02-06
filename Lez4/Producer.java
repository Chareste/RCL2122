package Lez4;

import java.util.Random;

public class Producer implements Runnable{
    Random rand = new Random();
    Dropbox drop;

    public Producer(Dropbox drop){
        this.drop = drop;
    }

    @Override
    public void run() {
        int r = rand.nextInt(100);
        drop.put(r);
    }
}
