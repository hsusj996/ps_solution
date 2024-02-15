import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) % 10;
            int b = Integer.parseInt(st.nextToken());
            int result = a;

            for (int i = 0; i < b - 1; i++) {
                result *= a;
                result %= 10;
            }

            System.out.println(result == 0 ? 10 : result);
        }
    }
}