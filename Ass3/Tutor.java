package Ass3;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Tutor {
    static ArrayList<ReentrantLock> computers = new ArrayList<>(20);
    int s; int p; int t;
    public Tutor(int s, int p, int t){
        this.s=s;
        this.p=p;
        this.t=t;
        for(int i=0;i<20;i++)computers.add(new ReentrantLock());
    }

    public void tutor(){
        ExecutorService thpool = Executors.newCachedThreadPool();
        int i=0, j=0, k=0;
        while(i+j+k !=s+p+t){
           if(i<p){thpool.execute(new Professore());i++;}
           if(j<t){thpool.execute(new Tesista());j++;}
           if(k<s){thpool.execute(new Studente());k++;}
        }
        thpool.shutdown();


    }


}
