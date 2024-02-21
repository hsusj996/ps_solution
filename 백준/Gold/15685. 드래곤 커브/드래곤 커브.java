import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static boolean[][] map = new boolean[101][101];
    static int[] d_y = { 0, -1, 0, 1 };
    static int[] d_x = { 1, 0, -1, 0 };
    static int cnt = 0;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            List<Integer> routeList = new ArrayList<>();
            routeList.add(d);
            int endX = startX + d_x[d];
            int endY = startY + d_y[d];
            map[endY][endX] = true;
            map[startY][startX] = true;
            for (int j = 1; j <= g; j++) {
                int dX = startX - endX;
                int dY = startY - endY;

                int tmpX = endX -= dY;
                int tmpY = endY += dX;
                map[tmpY][tmpX] = true;

                List<Integer> tmpList = new ArrayList<>();
                for (int direction : routeList) {
                    int newDirection = (direction + 3) % 4;
                    tmpList.add(newDirection);
                    tmpX += d_x[newDirection];
                    tmpY += d_y[newDirection];

                    map[tmpY][tmpX] = true;
                }

                for (int k = tmpList.size() - 1; k >= 0; k--) {
                    routeList.add((tmpList.get(k) + 2) % 4);
                }
            }
        }

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (!map[i][j]) {
                    continue;
                }
                if (!map[i + 1][j]) {
                    continue;
                }
                if (!map[i][j + 1]) {
                    continue;
                }
                if (!map[i + 1][j + 1]) {
                    continue;
                }
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}