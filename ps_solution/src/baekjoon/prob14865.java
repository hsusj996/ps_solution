package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class prob14865 {
    static class Side implements Comparable<Side> {
        int x;
        int direction;

        public Side(int x, int direction) {
            this.x = x;
            this.direction = direction;
        }

        @Override
        public int compareTo(Side o) {
            return this.x - o.x;
        }

    }

    static StringTokenizer st = null;
    static int N;
    static List<Side> sideList;
    static Stack<Side> sideStack;
    static Queue<int[]> tmpQ;
    static int[] prev;
    static int xLeft, xRight;
    static int typeA = 0, typeB = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        sideList = new ArrayList<>();
        sideStack = new Stack<>();
        tmpQ = new ArrayDeque<>();
        prev = new int[2];

        for (int i = 0; i < N; i++) {
            String[] s1 = br.readLine().split(" ");

            prev[0] = Integer.parseInt(s1[0]);
            prev[1] = Integer.parseInt(s1[1]);

            tmpQ.add(new int[] { prev[0], prev[1] });

            if (prev[1] < 0) {
                break;
            }
        }

        for (int i = tmpQ.size(); i < N; i++) {
            String[] s1 = br.readLine().split(" ");
            int x = Integer.parseInt(s1[0]);
            int y = Integer.parseInt(s1[1]);

            if (prev[1] < 0 && y > 0) {
                xLeft = x;
            }
            if (prev[1] > 0 && y < 0) {
                xRight = x;

                if (xLeft > xRight) {
                    int tmp = xLeft;
                    xLeft = xRight;
                    xRight = tmp;
                }

                sideList.add(new Side(xLeft, 1));
                sideList.add(new Side(xRight, 0));
            }

            prev[0] = x;
            prev[1] = y;
        }

        while (!tmpQ.isEmpty()) {
            int[] input = tmpQ.poll();
            int x = input[0];
            int y = input[1];

            if (prev[1] < 0 && y > 0) {
                xLeft = x;
            }

            if (prev[1] > 0 && y < 0) {
                xRight = x;

                if (xLeft > xRight) {
                    int tmp = xLeft;
                    xLeft = xRight;
                    xRight = tmp;
                }

                sideList.add(new Side(xLeft, 1));
                sideList.add(new Side(xRight, 0));
            }

            prev[0] = x;
            prev[1] = y;
        }

        Collections.sort(sideList);

        List<Boolean> innerCheck = new ArrayList<>();
        for (Side cur : sideList) {
            if (sideStack.isEmpty()) {
                sideStack.push(cur);
                typeA++;
                innerCheck.add(false);
                continue;
            }

            if(cur.direction == 1){
                innerCheck.add(false);
                innerCheck.set(sideStack.size() - 1, true);
                sideStack.push(cur);
            }

            if(cur.direction == 0){
                if(!innerCheck.get(sideStack.size() - 1)){
                    typeB++;
                }
                innerCheck.remove(sideStack.size() - 1);
                sideStack.pop();
            }
        }

        System.out.println(typeA + " " + typeB);
    }
}
