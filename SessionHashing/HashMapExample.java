package SessionHashing;

import java.util.HashMap;
import java.util.Map;

public class HashMapExample {
    public static void main(String[] args) {
        HashMap<Integer, String> mp1 = new HashMap<>();
        // unordered
        // keys unique, values can be duplicate

        // add/put
        mp1.put(1,"Abhinav");
        mp1.put(2,"Rahul");
        mp1.put(4542,"Aman");
        mp1.put(101,"Abhinav");
        mp1.put(null,"NULL");

        mp1.put(4542,"Yuvraj"); // update
        mp1.replace(101,"Awasthi");

        System.out.println(mp1.get(101));
        System.out.println(mp1.getOrDefault(1000, "Y"));
        System.out.println(mp1);

        mp1.remove(4542);

        System.out.println(mp1);

        System.out.println(mp1.keySet());
        System.out.println(mp1.values());
        System.out.println(mp1.entrySet());

        for(Integer x: mp1.keySet()) {
            System.out.print(x + ":" + mp1.get(x) + ", ");
        }
        System.out.println();

        for(String x: mp1.values()) {
            System.out.print(x + ", ");
        }
        System.out.println();

        for(Map.Entry<Integer, String> x: mp1.entrySet()) {
            System.out.print(x.getKey() + ", ");
            System.out.print(x.getValue() + ", ");
        }
        System.out.println();
    }
}
