package Ass3;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Professore implements Runnable{
    private final ArrayList<ReentrantLock> l = Tutor.computers;
    private final ArrayList<Boolean> visited = new ArrayList<>(20);
    private int visitedNr, curr;
    private Random rand = new Random(); //0-19

    public Professore(){
        for(int i=0;i<20;i++)visited.add(false); visitedNr=0;
    }

    @Override
    public void run() {
        ReentrantLock currLock;

        while(visitedNr<20){
            curr = rand.nextInt(19);
            currLock = l.get(curr);
            if(!visited.get(curr)){
                if(currLock.tryLock()){
                    System.out.println("Professore"+Thread.currentThread().getId()+"Sta controllando pc"+curr);
                    try {Thread.sleep(rand.nextInt(1000));
                    } catch (InterruptedException e){}
                    currLock.unlock();
                    visited.set(curr, true);
                    visitedNr++;
                }

            }
        }
    }
}

//initialize array to 0, se pc controllato, == 1
