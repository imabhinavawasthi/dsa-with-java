package Session9;

import Session10.Area;
import SessionOOPs.ModifierClass;

public class Strings extends ModifierClass {
    public static void main(String[] args) {
        String str1 = "abc";
        String str2 = "abc";

        System.out.println(str1==str2);
        System.out.println(str1.equals(str2));

        String str3 = new String("abc");
        System.out.println(str1==str3);
        System.out.println(str1.equals(str3));

        String a = "hello";
        a += " world";

        System.out.println(a);
        System.out.println(a.length());

        Area area1 = new Area();
        area1.area(5);

        ModifierClass obj = new ModifierClass();
        obj.checkProtected();
    }
}
