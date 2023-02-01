package Ass2;


import java.util.concurrent.*;

public class Office {
    static int nrEmettitrici=4;
    static int queueLength=10; //si assume k = 10
    private final ExecutorService threadpoolEmettitrici;
    BlockingQueue q = new ArrayBlockingQueue<>(queueLength);
    public Office(){
        this.threadpoolEmettitrici = new ThreadPoolExecutor(nrEmettitrici,nrEmettitrici,0L, TimeUnit.MILLISECONDS,q);
    }
    public void manageClient(OfficeClient t){
        this.threadpoolEmettitrici.execute(t);
    }

    public OfficeClient peek(){
        return (OfficeClient)q.peek();
    }
    public void chiudiSala(){
        threadpoolEmettitrici.shutdown();
    }

}
