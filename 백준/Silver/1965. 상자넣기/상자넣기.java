import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static StringTokenizer st = null;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0;
        dp[0] = 1;

        for (int i = 1; i < N; i++) {
            dp[i] = dpFunc(i);
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }

    private static int dpFunc(int idx) {
        if (dp[idx] != 0) {
            return dp[idx];
        }

        for (int i = 0; i < idx; i++) {
            if (arr[i] < arr[idx]) {
                dp[idx] = Math.max(dp[idx], dp[i]);
            }
        }

        return ++dp[idx];
    }
}