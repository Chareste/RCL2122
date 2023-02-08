package Ass5;

import java.io.File;

public class Consumer implements Runnable{
    PathList list;
    public Consumer(PathList list) {
        this.list = list;
    }

    @Override
    public void run() {
        String path = list.get();
        File filePath = new File(path);
        File[] fileList = filePath.listFiles(); //ottengo lista dei file contenuti in dir

        if(fileList!=null){
            for(File curr:fileList){ //scorro lista di file in dir
                if(curr.isFile())System.out.println(curr.getPath()); //stampo path per file
            }
        }
    }
}
