package Ass5;

import java.util.LinkedList;

public class PathList {
    LinkedList<String> q = new LinkedList<>();
    boolean inUse;

    public PathList(){
        this.inUse=false;
    }

    public synchronized String get() {
        String res;
        while(inUse) {
            try {wait();
            } catch (InterruptedException e) {}
        }
        inUse = true;

        if(!q.isEmpty()) res= q.poll();
        else res= "Lista vuota";

        inUse = false;
        notify();
        return res;
    }

    public synchronized void put(String path) {
        while(inUse) {
            try {wait();
            } catch (InterruptedException e) {}
        }

        inUse = true;
        q.push(path);
        inUse = false;
        notify();
    }

}
