import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int r, c;
    static char[][] board;
    static boolean[][] vis;
    static int mx;
    static int cnt;
    static int[] dy = {-1, 0, 1};
    static int[] dx = {1, 1, 1};
    static boolean isConnect;
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s[] = br.readLine().split(" ");
        r = Integer.parseInt(s[0]);
        c = Integer.parseInt(s[1]);
        board = new char[r+1][c+1];
        vis = new boolean[r+1][c+1];
        for(int i=0; i<r; i++) {
            String row = br.readLine();
            for(int j=0; j<c; j++) {
                board[i][j] = row.charAt(j);
            }
        }
        
        for(int i=0; i<r; i++) {
            isConnect = false;
            vis[i][0] = true;
            dfs(i, 0);
        }
        System.out.println(cnt);
    }
    private static void dfs(int i, int j) {
        if(j == c-1) {
            cnt++;
            isConnect = true;
            return;
        }
        for(int dir=0; dir<3; dir++) {
            int ny = i + dy[dir];
            int nx = j + dx[dir];
            if(ny < 0 || ny >= r || nx >= c)    continue;
            if(vis[ny][nx] || board[ny][nx] == 'x')    continue;
            vis[ny][nx] = true;
            dfs(ny, nx);
            if(isConnect)    return;
        }
    }
}