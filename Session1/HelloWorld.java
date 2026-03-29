package Session1;

public class HelloWorld {
    public static void main(String[] args){
        String name = args[0];
        int age = Integer.parseInt(args[1]);
        if(age>=18) {
            System.out.println(name + " is Eligible");
        }
        else {
            System.out.println(name + " is not Eligible");
        }
    }
}


// write a java program to take persons age and name as args,
// and decide whether person is eligible for driving license
// if age>=18 -> Abhinav is Eligible
// else -> Abhinav is not eligible
