import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        long a = (long)sc.nextInt();
        long b = (long)sc.nextInt();
        
        System.out.println((a + b) * (a - b));
    }
}