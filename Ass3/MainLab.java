package Ass3;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainLab {
    public static void main(String[] args) {
        //ExecutorService thpool = Executors.newFixedThreadPool(20);
        int studenti = new Random().nextInt(50);
        int prof = new Random().nextInt(50);
        int tesisti = new Random().nextInt(50);

        Tutor tutor = new Tutor(studenti, prof, tesisti);
        tutor.tutor();
    }
}
