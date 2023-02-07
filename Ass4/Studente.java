package Ass4;

import java.util.Random;

public class Studente implements Runnable{
    private final int id;
    private final Tutor t;
    private final Random rand =new Random();

    public Studente(int id, Tutor t){
        this.id=id;
        this.t=t;
    }
    public int getId(){return this.id;}



    @Override
    public void run() {
        System.out.println("Studente "+this.getId()+" richiede accesso a un pc");
        int curr = t.requestComputerStudente(this);
        System.out.println("Studente "+this.getId()+": pc "+ curr +" in uso");
        try { Thread.sleep(rand.nextInt(100));
        } catch (InterruptedException e) {}
        t.releaseComputer(0,curr);
    }
}
