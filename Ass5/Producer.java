package Ass5;

import java.io.File;
import java.util.LinkedList;

public class Producer implements Runnable {
    PathList list;
    File dir;

    public Producer(PathList list, File dir){
        this.dir = dir;
        this.list = list;
    }

    @Override
    public void run() {
        list.put(dir.getPath());
        LinkedList<File[]> directories = new LinkedList<>();
        directories.add(dir.listFiles());

        File[] subdir = directories.poll();
        while (subdir != null) {
            for (File curr : subdir) {
                if (curr.isDirectory()) {
                    list.put(curr.getPath());
                    directories.add(curr.listFiles());
                }
            }
            subdir = directories.poll();
        }
    }
}
