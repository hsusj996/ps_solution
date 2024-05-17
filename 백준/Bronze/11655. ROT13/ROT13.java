import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input = br.readLine();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c >= 'A' && c <= 'Z') {
                int n = c + 13;
                if (n > 'Z') {
                    n = 'A' + n - 'Z' - 1;
                }
                c = (char) n;
            }
            if (c >= 'a' && c <= 'z') {
                int n = c + 13;
                if (n > 'z') {
                    n = 'a' + n - 'z' - 1;
                }
                c = (char) n;
            }

            sb.append(c);
        }
        System.out.println(sb.toString());
    }
}