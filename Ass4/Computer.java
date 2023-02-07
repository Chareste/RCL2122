package Ass4;


public class Computer {
    private boolean inUse;

    public Computer(){
        this.inUse = false;
    }

    public synchronized boolean isUsing() {
        return inUse;
    }

    public synchronized void setUse(boolean inUse) {
        this.inUse = inUse;
    }




}
