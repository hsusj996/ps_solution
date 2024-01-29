package baekjoon;

import java.util.*;
import java.io.*;

public class prob1865 {
    static class edge {
        int node1;
        int node2;
        int sec;

        public edge(int node1, int node2, int sec) {
            this.node1 = node1;
            this.node2 = node2;
            this.sec = sec;
        }
    }

    final static int MAX = Integer.MAX_VALUE;
    static int T;
    static int N;
    static int M;
    static int W;
    static edge[] arr_road;
    static edge[] arr_wormhole;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            flag = false;
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            arr_road = new edge[M];
            W = Integer.parseInt(st.nextToken());
            arr_wormhole = new edge[W];

            //도로 입력
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int node1 = Integer.parseInt(st.nextToken());
                int node2 = Integer.parseInt(st.nextToken());
                int sec = Integer.parseInt(st.nextToken());
                arr_road[i] = new edge(node1, node2, sec);
            }
            //웜홀 입력
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int node1 = Integer.parseInt(st.nextToken());
                int node2 = Integer.parseInt(st.nextToken());
                int sec = Integer.parseInt(st.nextToken());
                arr_wormhole[i] = new edge(node1, node2, sec);
            }

            for (int i = 1; i <= N; i++) {
                if (Bellman_Ford(i)) {
                    flag = true;
                    break;
                }
            }

            if (flag) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    static boolean Bellman_Ford(int start) {
        int[] min_sec = new int[N + 1];
        Arrays.fill(min_sec, MAX);
        min_sec[start] = 0;

        //모든 간선의 개수 + 1 만큼 반복
        for (int i = 0; i <= M + W; i++) {
            //수정 여부 변수
            boolean update = false;

            //road edge relaxation(no direction)
            for (int j = 0; j < M; j++) {
                int node1 = arr_road[j].node1;
                int node2 = arr_road[j].node2;
                if (min_sec[node1] == MAX && min_sec[node2] == MAX) {
                    continue;
                }
                if (min_sec[node1] != MAX) {
                    int sec = min_sec[arr_road[j].node1] + arr_road[j].sec;
                    if (sec < min_sec[arr_road[j].node2]) {
                        min_sec[arr_road[j].node2] = sec;
                        update = true;
                    }
                }
                if (min_sec[node2] != MAX) {
                    int sec = min_sec[arr_road[j].node2] + arr_road[j].sec;
                    if (sec < min_sec[arr_road[j].node1]) {
                        min_sec[arr_road[j].node1] = sec;
                        update = true;
                    }
                }
            }

            //wormhole edge relaxation(direction)
            for (int j = 0; j < W; j++) {
                int node1 = arr_wormhole[j].node1;
                if (min_sec[node1] == MAX) {
                    continue;
                }
                int sec = min_sec[arr_wormhole[j].node1] - arr_wormhole[j].sec;
                if (sec < min_sec[arr_wormhole[j].node2]) {
                    min_sec[arr_wormhole[j].node2] = sec;
                    update = true;
                }
            }

            //수정이 없다면 음수 사이클이 없음
            if (!update) {
                return false;
            }
        }

        //지속적으로 수정이 발생하여 M+W+1만큼 조사했을 때 음수 사이클 발생
        return true;
    }
}
