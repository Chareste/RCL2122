package Ass3;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Studente implements Runnable{
    private final ArrayList<ReentrantLock> l = Tutor.computers;
    private int curr;
    private Random rand = new Random(); //0-19

    @Override
    public void run() {
        ReentrantLock currLock;
        while(true){
            curr = rand.nextInt(19);
            currLock = l.get(curr);
            if(currLock.tryLock()){
                System.out.println("Studente"+Thread.currentThread().getId()+"Sta usando pc"+curr);
                try {Thread.sleep(rand.nextInt(1000));
                } catch (InterruptedException e){}
                currLock.unlock();
                break;
            }
        }
    }
}
