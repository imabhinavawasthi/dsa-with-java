package SessionCollections;

public class StringBuilderExample {
    public static void main(String[] args) {
        String str = "java"; // immutable
        StringBuilder s = new StringBuilder(str);

        for(int i=1;i<=5;i++) {
            s.append(i);
        }
        s.insert(2,"hello");

        s.delete(3,7);

        s.replace(2,4,"abc");

        s.reverse();

        s.setCharAt(2,'0');

        System.out.println(s.indexOf("a"));
        System.out.println(s.lastIndexOf("a"));
        System.out.println(s.lastIndexOf("021ac"));

        System.out.println(s.substring(5));
        System.out.println(s.substring(5,9));

        System.out.println(s.charAt(2));
        System.out.println(s.length());
        System.out.println(s.capacity());
        s.ensureCapacity(100);
        System.out.println(s.capacity());
        System.out.println(s.toString());
    }
}
