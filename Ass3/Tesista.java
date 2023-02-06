package Ass3;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Tesista implements Runnable{
   //il pc richiesto è pc[0]
   private final ArrayList<ReentrantLock> l = Tutor.computers;
   private Random rand = new Random();

   @Override
   public void run() {
      ReentrantLock currLock = l.get(0);
      while(true){
         if(currLock.tryLock()){
            System.out.println("Tesista"+Thread.currentThread().getId()+"Sta usando pc 0");
            try {Thread.sleep(rand.nextInt(1000));
            } catch (InterruptedException e){}
            currLock.unlock();
            break;
         }
         else{
            try {Thread.sleep(50);
            } catch (InterruptedException e) {}
         }
      }


   }
}
