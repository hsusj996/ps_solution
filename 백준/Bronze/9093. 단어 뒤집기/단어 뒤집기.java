import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            StringBuilder sb = new StringBuilder();

            String input = br.readLine();
            String[] arr = input.split(" ");

            for (int i = 0; i < arr.length; i++) {
                for (int j = arr[i].length() - 1; j >= 0; j--) {
                    sb.append(arr[i].charAt(j));
                }
                sb.append(" ");
            }
            System.out.println(sb.toString());
        }
    }
}