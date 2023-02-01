package Lez2;

import java.util.concurrent.Callable;

public class Power implements Callable<Integer> {

    private int n;
    int e;
    public Power(int n, int e){
       this.n = n;
       this.e = e;
    }

    @Override
    public Integer call() throws Exception {
        System.out.printf("Esecuzione %d^%d in %d", n, e, Thread.currentThread().getId());

        return (int)Math.pow(this.n, this.e);
    }
}
