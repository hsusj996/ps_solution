package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class prob2800 {
    static String input;
    static int[][] bracket = new int[10][2]; // [순서][괄호 종류]
    static Stack<Integer> stk = new Stack<>();
    static int size = 0; // bracket size
    static boolean[] visited = new boolean[200]; // 문자열 방문 여부
    static Set<String> resultSet = new HashSet<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input = br.readLine();

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                stk.push(i);
            } else if (input.charAt(i) == ')') {
                bracket[size][0] = stk.pop();
                bracket[size][1] = i;
                size++;
            }
        }

        PowerSet(0);
        String[] resultArr = resultSet.toArray(new String[0]);
        Arrays.sort(resultArr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2){
                return o1.compareTo(o2);
            }
        });

        for (int i = 0; i < resultArr.length; i++) {
            System.out.println(resultArr[i]);
        }
    }

    private static void PowerSet(int depth) {
        if (depth == size) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < input.length(); i++) {
                if (visited[i]) {
                    continue;
                }
                sb.append(input.charAt(i));
            }
            if(sb.toString().equals(input)){
                return;
            }
            resultSet.add(sb.toString());
            return;
        }

        visited[bracket[depth][0]] = true;
        visited[bracket[depth][1]] = true;
        PowerSet(depth + 1);

        visited[bracket[depth][0]] = false;
        visited[bracket[depth][1]] = false;
        PowerSet(depth + 1);
    }
}
