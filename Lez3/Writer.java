package Lez3;

public class Writer implements Runnable{
    Counter ctr;

   public Writer(Counter ctr){
        this.ctr = ctr;
    }
    @Override
    public void run() {ctr.increment();}
}
