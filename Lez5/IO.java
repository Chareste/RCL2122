package Lez5;

import java.io.*;
import java.util.LinkedList;
//copiato da professoressa soluzione am lazy uwu owo uwu
public class IO {
        public static void main(String[] args) throws IOException {
            String basedir = "path/to/dir";

            File startDirectory = new File(basedir);

            if(!startDirectory.exists()) {
                System.out.println("Il file iniziale non esiste");
                System.exit(-1);
            }

            if(!startDirectory.isDirectory()) {
                System.out.println("Il file iniziale non è una directory");
                System.exit(-1);
            }

            LinkedList<File> directories = new LinkedList<>();
            directories.add(startDirectory);

            try(
                    DataOutputStream d = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("directories"))));
                    DataOutputStream f = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("files"))));
            ){
                while (directories.size() > 0) {
                    File[] filesInCurrentDirectory = directories.poll().listFiles();
                    /*
                     * questa condizione risulterà sempre falsa perché la lista directories
                     * contiente SOLO file di tipo directory (a scopo di debugging)
                     */
                    if (filesInCurrentDirectory == null){
                        System.out.println("Il file contenuto nella lista non è una directory");
                        System.exit(-1);
                    }

                    for (File curr_f : filesInCurrentDirectory) {
                        String towrite = curr_f.getName() + "\n";
                        if (curr_f.isDirectory()) {
                            // scrivo il nome della directory in "directories"
                            d.write(towrite.getBytes());
                            // aggiungo la directory alla lista
                            directories.add(curr_f);
                        } else {
                            // scrivo il nome del file in "files"
                            f.write(towrite.getBytes());
                        }
                    }
                }
            }
        }
}
