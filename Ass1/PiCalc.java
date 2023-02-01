package Ass1;

public class PiCalc extends Thread{

    private int pi;
    private final double acc;

    public PiCalc(double acc){
        this.pi=0;
        this.acc=acc;
    }

    public void run(){
        int div=1;
        int i=0;
        do{
            if(i%2==0){ pi+= 4/div;}
            else{pi -= 4/div;}

            i++; div+=2;
        } while(Math.abs(Math.PI-pi)>=acc && !Thread.interrupted());
    }
    public double getPi(){return pi;}
}
