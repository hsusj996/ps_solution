package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class prob17140 {
    static class cntObj implements Comparable<cntObj> {
        int num;
        int cnt;

        public cntObj(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(cntObj o) {
            if (this.cnt == o.cnt) {
                return this.num - o.num;
            }
            return this.cnt - o.cnt;
        }
    }

    static int R, C, K;
    static int[][] board1 = new int[103][103];
    static int[][] board2 = new int[103][103];
    static int curRowMax;
    static int curColMax;
    static StringTokenizer st = null;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 3; j++) {
                int input = Integer.parseInt(st.nextToken());
                board1[i][j] = input;
                board2[j][i] = input;
            }
        }

        curRowMax = 3;
        curColMax = 3;

        int time = 0;
        while (time <= 100) {
            if (board1[R][C] == K) {
                break;
            }
            time++;

            if (curRowMax >= curColMax) {
                calR();
            } else {
                calC();
            }

            // printForDebug();
        }

        if (time > 100) {
            System.out.println(-1);
        } else {
            System.out.println(time);
        }
    }

    private static void calC() {
        int maxTmp = 0;
        for (int i = 1; i <= curColMax; i++) {
            TreeSet<Integer> keySet = new TreeSet<>();
            int[] dosuArr = new int[101];
            for (int j = 1; j <= curRowMax; j++) {
                if (board2[i][j] == 0) {
                    continue;
                }
                keySet.add(board2[i][j]);
                dosuArr[board2[i][j]]++;
            }

            PriorityQueue<cntObj> pq = new PriorityQueue<>();
            for (Iterator<Integer> iter = keySet.iterator(); iter.hasNext();) {
                int n = iter.next();
                pq.add(new cntObj(n, dosuArr[n]));
            }

            int idx = 0;
            while (!pq.isEmpty()) {
                cntObj curObj = pq.poll();

                board2[i][++idx] = curObj.num;
                board1[idx][i] = curObj.num;
                board2[i][++idx] = curObj.cnt;
                board1[idx][i] = curObj.cnt;

                if (idx > 100) {
                    break;
                }
            }

            if (idx > 100) {
                curRowMax = 100;
            } else {
                maxTmp = Math.max(maxTmp, idx);
                for (int k = idx + 1; k <= curRowMax; k++) {
                    board2[i][k] = 0;
                    board1[k][i] = 0;
                }
            }
        }
        curRowMax = maxTmp;
    }

    private static void calR() {
        int maxTmp = 0;
        for (int i = 1; i <= curRowMax; i++) {
            TreeSet<Integer> keySet = new TreeSet<>();
            int[] dosuArr = new int[101];
            for (int j = 1; j <= curColMax; j++) {
                if (board1[i][j] == 0) {
                    continue;
                }
                keySet.add(board1[i][j]);
                dosuArr[board1[i][j]]++;
            }

            PriorityQueue<cntObj> pq = new PriorityQueue<>();
            for (Iterator<Integer> iter = keySet.iterator(); iter.hasNext();) {
                int n = iter.next();
                pq.add(new cntObj(n, dosuArr[n]));
            }

            int idx = 0;
            while (!pq.isEmpty()) {
                cntObj curObj = pq.poll();

                board1[i][++idx] = curObj.num;
                board2[idx][i] = curObj.num;
                board1[i][++idx] = curObj.cnt;
                board2[idx][i] = curObj.cnt;

                if (idx > 100) {
                    break;
                }
            }

            if (idx > 100) {
                curColMax = 100;
            } else {
                maxTmp = Math.max(maxTmp, idx);
                for (int k = idx + 1; k <= curColMax; k++) {
                    board1[i][k] = 0;
                    board2[k][i] = 0;
                }
            }
        }
        curColMax = maxTmp;
    }

    private static void printForDebug() {
        StringBuilder sb1 = new StringBuilder();
        sb1.append("\n debugPrint : board1 \n");
        for (int i = 1; i <= curRowMax; i++) {
            for (int j = 1; j <= curColMax; j++) {
                sb1.append(board1[i][j] + " ");
            }
            sb1.append("\n");
        }

        System.out.println(sb1.toString());

        StringBuilder sb2 = new StringBuilder();
        sb2.append("\n debugPrint : board2 \n");
        for (int i = 1; i <= curColMax; i++) {
            for (int j = 1; j <= curRowMax; j++) {
                sb2.append(board2[i][j] + " ");
            }
            sb2.append("\n");
        }

        System.out.println(sb2.toString());
    }
}
