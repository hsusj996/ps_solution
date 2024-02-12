import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static StringBuilder result = new StringBuilder();
    static int T;
    static int N;
    static int[] treeArr;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int max = 0;
            int needDay = 0;
            treeArr = new int[N];
            for (int i = 0; i < N; i++) {
                treeArr[i] = Integer.parseInt(st.nextToken());
                max = Math.max(max, treeArr[i]);
            }

            int needHeight1 = 0;
            int needHeight2 = 0;

            for (int i = 0; i < N; i++) {
                treeArr[i] = max - treeArr[i];
                needHeight2 += treeArr[i] / 2;
                needHeight1 += treeArr[i] % 2;
            }

            while (needHeight2 - needHeight1 >= 3) {
                needHeight2--;
                needHeight1 += 2;
            }

            if(needHeight2 > needHeight1){
                needDay += needHeight1 * 2;
                if(needHeight2 - needHeight1 == 2){
                    needDay += 3;
                } else if(needHeight2 - needHeight1 == 1){
                    needDay += 2;
                }
            } else if(needHeight2 < needHeight1){
                needDay += needHeight2 * 2;
                needDay += (needHeight1 - needHeight2) * 2 - 1;
            } else{
                needDay += needHeight1 * 2;
            }

            result.append("#" + test_case + " " + needDay + "\n");
        }

        System.out.println(result);
    }
}