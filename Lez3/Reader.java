package Lez3;

public class Reader implements Runnable{
    Counter ctr;

   public Reader(Counter ctr){
        this.ctr=ctr;
    }
    @Override
    public void run() {System.out.println(ctr.get());}
}
