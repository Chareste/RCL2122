package Lez2;


import java.util.concurrent.RejectedExecutionException;

import static java.lang.Thread.sleep;

public class MainBiglietteria {
    static int nrTravelers=50;
    static int maxWait=50;

    public static void main(String[] args){
        SalaBiglietteria sala = new SalaBiglietteria();

        for(int i=0;i<=nrTravelers;i++){
            Traveler t = new Traveler();

            try{sala.manageTraveler(t);}
            catch(RejectedExecutionException e){
                System.out.println("Traveler" + t.getId()+ ": sala esaurita");
            }
            try {
                sleep(maxWait);
            } catch (InterruptedException e) {
                System.out.println("Sleep interrupted");
            }
        }

       sala.chiudiSala();

    }


}
