package Session4;

public class TerminateLoop {
    public static void main(String[] args) {

        for(int i = 1;i<=100;i++) {
            System.out.print(i + " ");
            if(i%3==0) continue;
            if(i%10==0) break;
        }

    }
}
