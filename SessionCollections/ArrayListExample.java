package SessionCollections;

import java.util.ArrayList;

public class ArrayListExample {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();

        arrayList.add(5);
        arrayList.add(7);
        arrayList.add(1);
        arrayList.add(10);

        arrayList.set(2,3);
        arrayList.remove(3);

        System.out.println(arrayList);
        System.out.println(arrayList.get(2));
        System.out.println(arrayList.getFirst());
        System.out.println(arrayList.getLast());

        int n = arrayList.size();
        for (Integer i : arrayList) {
            System.out.print(i + " ");
        }
        for(int i=0;i<n;i++) {
            System.out.print(arrayList.get(i) + " ");
        }
    }
}
