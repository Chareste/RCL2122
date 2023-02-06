package Lez3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Counter {
    private int ctr = 0;
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock rLock = rwLock.readLock();
    private final Lock wLock = rwLock.writeLock();
    public void increment(){
        wLock.lock();
        ctr++;
        wLock.unlock();
    }
    public int get(){
        /*rLock.lock();
        rLock.unlock();*/
        return ctr;
    }

}
