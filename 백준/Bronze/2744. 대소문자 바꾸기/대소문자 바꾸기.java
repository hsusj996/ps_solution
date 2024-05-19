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
            if (c >= 'a' && c <= 'z') {
                sb.append(Character.toUpperCase(c));
            }
            if (c >= 'A' && c <= 'Z') {
                sb.append(Character.toLowerCase(c));
            }
        }

        System.out.println(sb.toString());
    }
}