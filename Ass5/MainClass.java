package Ass5;


import java.io.File;
import java.util.Random;

public class MainClass {
    public static void main(String[] args) {
        String directory = "./";
        File dir = new File(directory);

        if(!dir.exists()){
            System.out.println("Il file iniziale non esiste");
            System.exit(-1);
        }
        if(!dir.isDirectory()) {
            System.out.println("Il file iniziale non è una directory");
            System.exit(-1);
        }

        Random rand = new Random();
        PathList list = new PathList();

        int k = rand.nextInt(15); //nr consumers
        new Thread(new Producer(list, dir)).start();
        for(int i=0;i<k;i++) new Thread(new Consumer(list)).start();

    }
}
