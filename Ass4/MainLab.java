package Ass4;


import java.util.Random;

public class MainLab {
    public static void main(String[] args) {
        int s = 15;//new Random().nextInt(50);
        int p = 15;//new Random().nextInt(50);
        int t= 15;//new Random().nextInt(50);

        Tutor tutor = new Tutor(s,p,t);

        //inizializzo thread per tutti gli users
        for(int i=0;i<s;i++) new Thread(new Studente(i,tutor)).start();
        for(int i=0;i<p;i++) new Thread(new Professore(i,tutor)).start();
        for(int i=0;i<t;i++) new Thread(new Tesista(i,tutor)).start();

        System.out.println("fine threads");
    }
}
