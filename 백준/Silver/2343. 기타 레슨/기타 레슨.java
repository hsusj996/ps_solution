import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringTokenizer st = null;
    static int N, M;
    static int[] arr;
    static int low = 0, high = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());

        int sum = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
            low = Math.max(low, arr[i]);
        }
        high = sum;

        binarySearch();

        System.out.println(low);
    }

    private static void binarySearch() {
        while (low <= high) {
            int mid = (low + high) / 2;

            int cnt = 0;
            int sum = 0;
            for (int i = 0; i < N; i++) {
                if (sum + arr[i] > mid) {
                    sum = 0;
                    cnt++;
                }
                sum += arr[i];
            }
            cnt++;

            if (cnt <= M) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
    }
}