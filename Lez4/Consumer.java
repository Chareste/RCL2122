package Lez4;

public class Consumer implements Runnable{
    boolean b;
    Dropbox drop;
    public Consumer(boolean b, Dropbox drop){
        this.b = b;
        this.drop = drop;
    }

    @Override
    public void run() {
        drop.take(b);
    }
}
