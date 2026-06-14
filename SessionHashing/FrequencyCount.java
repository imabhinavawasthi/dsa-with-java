package SessionHashing;

import java.util.HashMap;
import java.util.Map;

public class FrequencyCount {
    public static void main(String[] args) {
        String[] names = {"abhinav", "aman", "abhinav", "rahul", "aman", "abhinav"};

        // frequency- abhinav:3, aman:2, rahul:1
        HashMap<String,Integer> freq = new HashMap<>();

        for(String name: names) {
            freq.put(name,freq.getOrDefault(name,0)+1);
        }

        System.out.print(freq);
    }
}
