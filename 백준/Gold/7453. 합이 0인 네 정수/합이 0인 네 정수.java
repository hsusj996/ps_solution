import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] A = new int[4001];
    static int[] B = new int[4001];
    static int[] C = new int[4001];
    static int[] D = new int[4001];
    static int[] arr1;
    static int[] arr2;
    static int n;
    static StringTokenizer st = null;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr1 = new int[n * n];
        arr2 = new int[n * n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr1[idx] = A[i] + B[j];
                arr2[idx] = C[i] + D[j];
                idx++;
            }
        }

        // 정렬
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        System.out.println(searchZero());
    }

    private static long searchZero() {
        long ret = 0;
        int p1 = 0;
        int p2 = n * n - 1;

        while (p1 < n * n && p2 >= 0) {
            int sum = arr1[p1] + arr2[p2];

            if (sum == 0) {
                long cnt1 = 1;
                long cnt2 = 1;

                while (p1 < n * n - 1 && arr1[p1] == arr1[p1 + 1]) {
                    cnt1++;
                    p1++;
                }

                while (p2 > 0 && arr2[p2] == arr2[p2 - 1]) {
                    cnt2++;
                    p2--;
                }

                ret += (cnt1 * cnt2);
                p1++;
                p2--;

            } else if (sum < 0) {
                p1++;
            } else {
                p2--;
            }
        }

        return ret;
    }
}