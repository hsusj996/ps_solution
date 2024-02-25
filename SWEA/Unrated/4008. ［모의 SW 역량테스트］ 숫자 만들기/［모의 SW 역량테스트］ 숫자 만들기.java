import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static StringBuilder result = new StringBuilder();
    static StringTokenizer st = null;
    static int[] operandArr;
    static int[] operatorCntArr; // 0, 1, 2, 3 : +, -, *, /
    static char[] orderOperator;
    static int max;
    static int min;
    static int T, N;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            result.append("#").append(test_case).append(" ");
            N = Integer.parseInt(br.readLine());

            operandArr = new int[N];
            operatorCntArr = new int[4];
            orderOperator = new char[N - 1];
            Arrays.fill(orderOperator, '0');
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) {
                operatorCntArr[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                operandArr[i] = Integer.parseInt(st.nextToken());
            }

            // +, -, *, / 순으로 자리조합
            CombiPlus(0, 0);

            result.append(max - min).append("\n");
        }

        System.out.println(result);
    }

    private static void CombiPlus(int prev, int depth) {
        if (depth == operatorCntArr[0]) {
            CombiMinus(0, 0);

            return;
        }

        for (int i = prev; i < N - 1; i++) {
            if (orderOperator[i] != '0') {
                continue;
            }

            orderOperator[i] = '+';
            CombiPlus(i + 1, depth + 1);
            orderOperator[i] = '0';
        }
    }

    private static void CombiMinus(int prev, int depth) {
        if (depth == operatorCntArr[1]) {
            CombiMultiple(0, 0);

            return;
        }
        for (int i = prev; i < N - 1; i++) {
            if (orderOperator[i] != '0') {
                continue;
            }

            orderOperator[i] = '-';
            CombiMinus(i + 1, depth + 1);
            orderOperator[i] = '0';
        }
    }

    private static void CombiMultiple(int prev, int depth) {
        if (depth == operatorCntArr[2]) {
            CombiDivide(0, 0);

            return;
        }
        for (int i = prev; i < N - 1; i++) {
            if (orderOperator[i] != '0') {
                continue;
            }

            orderOperator[i] = '*';
            CombiMultiple(i + 1, depth + 1);
            orderOperator[i] = '0';
        }
    }

    private static void CombiDivide(int prev, int depth) {
        if (depth == operatorCntArr[3]) {
            UpdateResult();

            return;
        }
        for (int i = prev; i < N - 1; i++) {
            if (orderOperator[i] != '0') {
                continue;
            }

            orderOperator[i] = '/';
            CombiDivide(i + 1, depth + 1);
            orderOperator[i] = '0';
        }
    }

    private static void UpdateResult() {
        int v = operandArr[0];

        for (int i = 1; i < N; i++) {
            char operator = orderOperator[i - 1];

            switch (operator) {
                case '+':
                    v += operandArr[i];
                    break;
                case '-':
                    v -= operandArr[i];
                    break;
                case '*':
                    v *= operandArr[i];
                    break;
                case '/':
                    v /= operandArr[i];
                    break;

                default:
                    break;
            }
        }

        min = Math.min(min, v);
        max = Math.max(max, v);
    }
}