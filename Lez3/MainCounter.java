package Lez3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainCounter {
    public static void main(String[] args) {
        Counter ctr = new Counter();
        ExecutorService thrPool = Executors.newCachedThreadPool();
        for (int i=0;i<=20;i++) thrPool.execute(new Reader(ctr));
        for (int i=0;i<=20;i++) thrPool.execute(new Writer(ctr));


    }
}
