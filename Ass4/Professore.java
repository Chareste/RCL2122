package Ass4;

import java.util.ArrayList;
import java.util.Random;

public class Professore implements Runnable{
   // private final ArrayList<ReentrantLock> l = Tutor.computers;
    public final ArrayList<Boolean> visited = new ArrayList<>(20);
    private int visitedNr;
    private final Tutor t;
    private final int id;
    private final Random rand = new Random(); //0-19

    public Professore(int id, Tutor t){
        for(int i=0;i<20;i++)visited.add(false);
        visitedNr=0;
        this.id=id;
        this.t=t;
    }
    public int getId(){return this.id;}

    @Override
    public void run() {
        System.out.println("Professore "+this.getId()+" richiede controllo del laboratorio");
        while(visitedNr!=20){
            int curr = t.requestComputerProfessore(this);
            visited.set(curr,true);
            System.out.println("Professore "+this.getId()+": controllo computer "+ curr);
            visitedNr++;
            try { Thread.sleep(rand.nextInt(100));
            } catch (InterruptedException e) {}
            t.releaseComputer(2,curr);
        }
        System.out.println("fine professore "+this.getId());
    }
}

//initialize array to 0, se pc controllato, == 1
