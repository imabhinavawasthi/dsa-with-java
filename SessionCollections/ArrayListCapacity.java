package SessionCollections;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListCapacity {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>(10);

        names.add("Abhinav");
        names.add("Aman");
        names.add("Rahul");
        names.add("Yash");
        names.add(1,"Yuvraj");
        // 1000000 store
        // 999999 remove
        names.trimToSize();
        System.out.println(names);

        // using indexes
        for(int i=0;i<names.size();i++) {
            System.out.print(i + ": " + names.get(i) + " ");
        }
        System.out.println();

        // for each loop
        for (String name : names) {
            System.out.print(name + " ");
        }
        System.out.println();

        // iterator
        Iterator<String> it = names.iterator();
        while(it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();
    }
}
