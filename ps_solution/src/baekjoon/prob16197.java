package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class prob16197 {
    static final int[][] d = { { -1, 0, 1, 0 }, { 0, 1, 0, -1 } };

    static class xy {
        int x;
        int y;

        public xy(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + x;
            result = prime * result + y;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof xy)) {
                return false;
            }

            xy o = (xy) obj;
            return this.x == o.x && this.y == o.y;
        }
    }

    static class CoinState {
        xy coin1;
        xy coin2;

        public CoinState() {
        }

        public CoinState(xy coin1, xy coin2) {
            this.coin1 = coin1;
            this.coin2 = coin2;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((coin1 == null) ? 0 : coin1.hashCode());
            result = prime * result + ((coin2 == null) ? 0 : coin2.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof CoinState)) {
                return false;
            }

            CoinState o = (CoinState) obj;
            return (this.coin1.equals(o.coin1) && this.coin2.equals(o.coin2)) ||
                    (this.coin1.equals(o.coin2) && this.coin2.equals(o.coin1));
        }

    }

    static int N, M;
    static char[][] board;
    static HashSet<CoinState> coinStateSet = new HashSet<>();
    static StringTokenizer st = null;
    static CoinState init = new CoinState();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = input.charAt(j);
                if (board[i][j] == 'o') {
                    if (init.coin1 == null) {
                        init.coin1 = new xy(i, j);
                    } else {
                        init.coin2 = new xy(i, j);
                    }
                }
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<CoinState> q = new ArrayDeque<>();
        q.add(init);
        coinStateSet.add(init);

        int depth = 0;
        while (++depth <= 10) {
            int qSize = q.size();

            while (qSize-- > 0) {
                CoinState cur = q.poll();

                for (int i = 0; i < 4; i++) {
                    int x1 = cur.coin1.x + d[0][i];
                    int y1 = cur.coin1.y + d[1][i];
                    int x2 = cur.coin2.x + d[0][i];
                    int y2 = cur.coin2.y + d[1][i];

                    boolean b1 = IsOutBound(x1, y1);
                    boolean b2 = IsOutBound(x2, y2);
                    if(b1 && b2){
                        continue;
                    } else if(b1 ^ b2){
                        return depth;
                    }

                    if(board[x1][y1] == '#'){
                        x1 = cur.coin1.x;
                        y1 = cur.coin1.y;
                    }
                    if(board[x2][y2] == '#'){
                        x2 = cur.coin2.x;
                        y2 = cur.coin2.y;
                    }

                    CoinState next = new CoinState(new xy(x1, y1), new xy(x2, y2));
                    if(coinStateSet.contains(next)){
                        continue;
                    }

                    coinStateSet.add(next);
                    q.add(next);
                }
            }
        }
        return -1;
    }

    private static boolean IsOutBound(int x, int y) {
        return x < 0 || x >= N ||y < 0 || y >= M;
    }
}
