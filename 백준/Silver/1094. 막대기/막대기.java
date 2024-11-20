import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int X = sc.nextInt();

        List<Integer> sticks = new ArrayList<>();
        int min = 64;
        sticks.add(64);


        while(true){
            int sum = 0;
            for(int i=0;i<sticks.size();i++){
                sum += sticks.get(i);
            }
            if(sum == X){
                System.out.println(sticks.size());
                return;
            }

            for(int i=0;i<sticks.size();i++){
                if(min == sticks.get(i)){
                    sticks.remove(i);
                    sum -= min;
                    break;
                }
            }

            int tmp = min / 2;
            sum += tmp;

            if(sum >= X){
                sticks.add(tmp);
            } else{
                sticks.add(tmp);
                sticks.add(tmp);
            }
            min = tmp;
        }
    }
}