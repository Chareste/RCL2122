package Ass4;

import java.util.Random;

public class Tesista implements Runnable{
   //il pc richiesto è pc[0]
   private final Tutor t;
   private final int id;
   private final Random rand = new Random();

   public Tesista(int id, Tutor t){
      this.id=id;
      this.t=t;
   }
   public int getId(){return this.id;}

   @Override
   public void run() {
      System.out.println("Tesista "+this.getId()+" richiede accesso al pc 0");
      t.requestComputerTesista(this);
      System.out.println("Tesista "+this.getId()+": pc 0 in uso");
      try { Thread.sleep(rand.nextInt(100));
      } catch (InterruptedException e) {}
      t.releaseComputer(1,0);
   }
}
