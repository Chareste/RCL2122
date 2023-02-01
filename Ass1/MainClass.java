package Ass1;

import java.util.*;



public class MainClass {
    public static void main(String[] args){
        //ricevo argomenti da input
        double acc; long timeout;
        Scanner in=new Scanner(System.in);
        System.out.println("Insert accuracy value:");
            try {acc = in.nextDouble();}
            catch (InputMismatchException e){throw e;}
        System.out.println("Insert timeout value:");
            try{timeout = in.nextInt();}
            catch (InputMismatchException e){throw e;}
            finally {
                in.close();
            }

            PiCalc pi = new PiCalc(acc);
            pi.start();

        try{pi.join(timeout);}
        catch(InterruptedException e){
            System.out.println("thread interrupted");
        }
        if(pi.isAlive()){
            pi.interrupt();
            System.out.println("timeout exceeded");
        }
        else{ System.out.printf("Result: %f", pi.getPi());}

    }
}
