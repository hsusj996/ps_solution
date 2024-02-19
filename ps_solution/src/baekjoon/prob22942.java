package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class prob22942 {
    static class Circle {
        int pos;
        int radius;

        Circle(int pos, int radius) {
            this.pos = pos;
            this.radius = radius;
        }
    }

    static int N;
    static Stack<Circle> stk = new Stack<>();
    static Circle[] circleArr;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        circleArr = new Circle[N];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            circleArr[i] = new Circle(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
        }

        Arrays.sort(circleArr, (o1, o2) -> o1.pos - o2.pos);

        for (int i = 0; i < N; i++) {
            Circle circle = circleArr[i];

            while (!stk.isEmpty()) {
                Circle circle2 = stk.peek();

                int d = circle.pos - circle2.pos;
                int rDiff = Math.abs(circle.radius - circle2.radius);
                int rSum = circle.radius + circle2.radius;

                if (rDiff <= d && rSum >= d) {
                    System.out.println("NO");
                    return;
                }

                if (rSum < d) {
                    stk.push(circle);
                    break;
                }

                if (circle.radius < circle2.radius) {
                    break;
                } else {
                    stk.pop();
                }
            }

            if (stk.isEmpty()) {
                stk.push(circle);
            }
        }

        System.out.println("YES");
    }
}
