import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
    static StringBuilder result = new StringBuilder();
    static StringTokenizer st = null;
    static int T, N, K;
    static Queue<Character> chestQ;
    static Set<Long> pwSet;
    static List<Long> pwList;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            result.append("#").append(test_case).append(" ");

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            String s = br.readLine();

            pwSet = new HashSet<>();
            pwList = new ArrayList<>();
            chestQ = new ArrayDeque<>(); // chestQ에 삽입
            for (int i = 0; i < N; i++) {
                chestQ.add(s.charAt(i));
            }

            CollectNumber();
            // 0 ~ N회전
            for (int i = 0; i < (N / 4) - 1; i++) {
                // 회전
                chestQ.add(chestQ.poll());
                CollectNumber();
            }

            // K번째 뽑기
            for (Iterator<Long> iter = pwSet.iterator(); iter.hasNext();) {
                pwList.add(iter.next());
            }
            pwList.sort((Long o1, Long o2) -> Long.compare(o2, o1));

            result.append(pwList.get(K - 1)).append("\n");
        }

        System.out.println(result);
    }

    private static void CollectNumber() {
        for (int i = 0; i < 4; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < N / 4; j++) {
                char c = chestQ.poll();
                sb.append(c);
                chestQ.add(c);
            }

            pwSet.add(Long.parseLong(sb.toString(), 16));
        }
    }

    // private static long HexToDecimal(String num){
    // long ret = 0;

    // for(int i=0;i<num.length();i++){
    // char c = num.charAt(num.length() - 1 - i);
    // ret += Math.pow(16, i) * Integer.;
    // }
    // }
}