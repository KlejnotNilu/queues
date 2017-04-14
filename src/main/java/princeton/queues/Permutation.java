package princeton.queues;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class Permutation {

    public static void main(String[] args) { // this program uses command argument
        // TODO Auto-generated method stub

        RandomizedQueue<String> test = new RandomizedQueue<String>();

        String nameOfFile = "permutation10.txt"; // pass there the name of file from resources folder

        if(nameOfFile.charAt(0) != '/') {
            nameOfFile = "/" + nameOfFile;
        }

        File file = new File(Permutation.class.getResource(nameOfFile).getFile());

        try {
            Scanner scaner = new Scanner(file);

            while (scaner.hasNext()) {
                String toAdd = scaner.next();
                System.out.println(toAdd); // print input stream
                test.enqueue(toAdd);
            }

            System.out.println();

            int i = Integer.parseInt(args[0]); // get the number of items to display
            Iterator<String> iterator = test.iterator();

            while (i > 0) {
                System.out.println(iterator.next());
                i--;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Incorrect file");
        }


    }

}
