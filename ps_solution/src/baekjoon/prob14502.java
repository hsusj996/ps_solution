package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class prob14502 {
    static int N;
    static int M;
    static int[][] map;
    static boolean[][] wallVisited;
    static int max = Integer.MIN_VALUE;
    static int tmp;
    static int[] d_row = { -1, 0, 1, 0 };
    static int[] d_col = { 0, 1, 0, -1 };
    static List<cdnt> virusList = new ArrayList<>();

    static class cdnt {
        int x;
        int y;
        int depth;

        public cdnt(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public cdnt(int x, int y, int depth) {
            this(x, y);
            this.depth = depth;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        wallVisited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2){
                    virusList.add(new cdnt(i,j));
                }
            }
        }

        BuildWall(0);
        System.out.println(max);
    }

    static void BuildWall(int depth) {
        if (depth == 3) {
            CheckVirus();
            max = Math.max(max, tmp);
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0 || wallVisited[i][j]) {
                    continue;
                }
                map[i][j] = 1;
                wallVisited[i][j] = true;
                BuildWall(depth + 1);
                map[i][j] = 0;
                wallVisited[i][j] = false;
            }
        }
    }

    static void CheckVirus() {
        tmp = 0;
        int map_copied[][] = new int[N][M];
        for(int i=0;i<N;i++){
            map_copied[i] = map[i].clone();
        }

        // 바이러스 덮기
        for(int i=0;i<virusList.size();i++){
            BFS_virusProced(virusList.get(i), map_copied);
        }

        // 빈 공간 계산
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map_copied[i][j] == 0){
                    tmp += BFS_checkSpace(new cdnt(i, j), map_copied);
                }
            }
        }
    }

    static void BFS_virusProced(cdnt virus, int[][] new_map){
        boolean[][] visited = new boolean[N][M];
        Queue<cdnt> q = new LinkedList<>();
        q.add(virus);
        visited[virus.x][virus.y] = true;

        while(!q.isEmpty()){
            cdnt now = q.poll();

            for(int i=0;i<4;i++){
                int new_x = now.x + d_row[i];
                int new_y = now.y + d_col[i];

                if(!isOnBoard(new_x, new_y) || new_map[new_x][new_y] != 0 || visited[new_x][new_y]){
                    continue;
                }

                q.add(new cdnt(new_x, new_y));
                visited[new_x][new_y] = true;
                new_map[new_x][new_y] = 2;
            }
        }

    }

    static int BFS_checkSpace(cdnt c, int[][] new_map) {
        boolean[][] visited = new boolean[N][M];
        int cnt = 0;
        Queue<cdnt> q = new LinkedList<>();
        q.add(c);
        visited[c.x][c.y] = true;
        new_map[c.x][c.y] = 3;
        cnt++;

        while (!q.isEmpty()) {
            cdnt now = q.poll();

            for (int i = 0; i < 4; i++) {
                int new_x = now.x + d_row[i];
                int new_y = now.y + d_col[i];

                if (!isOnBoard(new_x, new_y) || map[new_x][new_y] != 0 || visited[new_x][new_y]) {
                    continue;
                }

                q.add(new cdnt(new_x, new_y));
                visited[new_x][new_y] = true;
                new_map[new_x][new_y] = 3;
                cnt++;
            }

        }

        return cnt;
    }

    static boolean isOnBoard(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < M;
    }
}