import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import javax.print.attribute.standard.MediaSize.ISO;

public class prob17144 {
    static int R;
    static int C;
    static int T;

    static class dust {
        int size;
        int tmp;

        public dust(int size) {
            this.size = size;
        }

        public void commit() {
            size += tmp;
            tmp = 0;
        }
    }

    static dust[][] map;

    static class cdnt {
        int row;
        int col;

        public cdnt(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static class Cleaner {
        cdnt upCycle;
        cdnt downCycle;
    }

    static int[] d_row = { 0, -1, 0, 1 };
    static int[] d_col = { 1, 0, -1, 0 };
    static Cleaner cleaner = new Cleaner();
    static int sum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new dust[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = new dust(Integer.parseInt(st.nextToken()));
                if (map[i][j].size == -1) {
                    if (cleaner.upCycle == null) {
                        cleaner.upCycle = new cdnt(i, j);
                    } else {
                        cleaner.downCycle = new cdnt(i, j);
                    }
                }
            }
        }

        while (T-- > 0) {
            Dispersion();

            Clean();
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j].size == -1) {
                    continue;
                }
                sum += map[i][j].size;
            }
        }

        System.out.println(sum);
    }

    static void Dispersion() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int n = map[i][j].size / 5;
                if(n == 0){
                    continue;
                }
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int new_row = i + d_row[k];
                    int new_col = j + d_col[k];

                    if (IsOutBound(new_row, new_col) || map[new_row][new_col].size == -1) {
                        continue;
                    }
                    cnt++;
                    map[new_row][new_col].tmp += n;
                }
                map[i][j].size -= (n * cnt);
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j].commit();
            }
        }
    }

    static void Clean() {
        cdnt upCdnt = new cdnt(cleaner.upCycle.row, cleaner.upCycle.col);
        cdnt downCdnt = new cdnt(cleaner.downCycle.row, cleaner.downCycle.col);
        int directionUp = 0;
        int directionDown = 0;
        int buf1 = 0;
        int buf2 = 0;
        while (true) {
            int new_row = upCdnt.row + d_row[directionUp];
            int new_col = upCdnt.col + d_col[directionUp];

            if (IsOutBound(new_row, new_col)) {
                directionUp++;
                directionUp %= 4;
                continue;
            }

            if (map[new_row][new_col].size == -1) {
                break;
            }

            buf1 = map[new_row][new_col].size;
            map[new_row][new_col].size = buf2;
            buf2 = buf1;
            upCdnt.row = new_row;
            upCdnt.col = new_col;
        }

        while (true) {
            int new_row = downCdnt.row + d_row[directionDown];
            int new_col = downCdnt.col + d_col[directionDown];

            if (IsOutBound(new_row, new_col)) {
                directionDown--;
                if (directionDown < 0) {
                    directionDown += 4;
                }
                continue;
            }

            if (map[new_row][new_col].size == -1) {
                break;
            }

            buf1 = map[new_row][new_col].size;
            map[new_row][new_col].size = buf2;
            buf2 = buf1;
            downCdnt.row = new_row;
            downCdnt.col = new_col;
        }
    }

    static boolean IsOutBound(int row, int col) {
        return row < 0 || row >= R || col < 0 || col >= C;
    }
}
