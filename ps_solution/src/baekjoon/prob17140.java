package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class prob17140 {
    static int R, C, K;
    static int[][] board1 = new int[100][100];
    static int[][] board2 = new int[100][100];
    static int curRowMax;
    static int curColMax;
    static StringTokenizer st = null;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                board1[i][j] = Integer.parseInt(st.nextToken());
                board2[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        curRowMax = 2;
        curColMax = 2;

        int time = 0;
        while (++time < 100) {
            if (curRowMax >= curColMax) {
                calR();
            } else {
                calC();
            }
        }

        if (time == 100) {
            System.out.println(-1);
        } else {
            System.out.println(time);
        }
    }

    private static void calC() {

    }

    private static void calR() {
        for (int i = 0; i <= curRowMax; i++) {
            TreeSet<Integer> keySet = new TreeSet<>();
            int[] dosuArr = new int[101];
            for (int j = 0; j < 100 || board1[i][j] == 0; j++) {
                keySet.add(board1[i][j]);
                dosuArr[board1[i][j]]++;
            }

            TreeMap<Integer, Integer> treeMap = new TreeMap<>();
            for(Iterator<Integer> iter = keySet.iterator();iter.hasNext();){
                int n = iter.next();
                treeMap.put(dosuArr[n], n);
            }

            
        }
    }
}
