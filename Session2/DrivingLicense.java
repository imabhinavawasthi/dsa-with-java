package Session2;

public class DrivingLicense {
    public static void main(String[] args) {
        int age = 30;
        boolean isTestPass = true;

        if(age>=18 && isTestPass) {
            System.out.println("Eligible");
        }
        else {
            System.out.println("Notm Eligible");
        }

        int a = 100;
        int b = 50;
        int c = 200;
        int d = -100;

        System.out.println(a>b && c==b);
        System.out.println(a>b || c==b || b<c);
        System.out.println(a>b || c==b && b<c);
        System.out.println(a/b*5-d<100);
        System.out.println(d+c*10/b%4);
    }
}
