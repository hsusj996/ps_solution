import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            boolean flag = true;

            if(s.length() >= 10 || s.length() <= 5){
                sb.append("no").append("\n");
            } else{
                sb.append("yes").append("\n");
            }
        }

        System.out.println(sb.toString());
    }
}