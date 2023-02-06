package Lez4;

public class Main {
    public static void main(String[] args) {
        Dropbox drop = new Dropbox();
        Thread t = new Thread(new Consumer(true,drop));
        Thread f = new Thread(new Consumer(false, drop));
        Thread p = new Thread(new Producer(drop));

        t.start();
        f.start();
        p.start();

    }

}
