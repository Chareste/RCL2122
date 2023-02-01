package Lez2;

import java.util.concurrent.*;

public class SalaBiglietteria {
    static int nrEmettitrici=5;
    static int queueLength=10;
    private final ExecutorService threadpoolEmettitrici;
    public SalaBiglietteria(){
        this.threadpoolEmettitrici =new ThreadPoolExecutor(nrEmettitrici,nrEmettitrici,0L,TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(queueLength));
    }
    public void manageTraveler(Traveler t){
        this.threadpoolEmettitrici.execute(t);
    }

    public void chiudiSala(){
        threadpoolEmettitrici.shutdown();
    }

}
