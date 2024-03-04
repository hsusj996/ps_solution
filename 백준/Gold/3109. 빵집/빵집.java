import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static char[][] map;
	static boolean[][][] visit;
	static int result = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		visit = new boolean[N][M][3];
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		for(int i=0; i<N; i++) {
			if( dfs(i, 0) ) {
				result++;
			}
		}
		
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static boolean dfs(int i, int j) {
		if(j == M-1) {
			return true;
		}
		
		int[][] direct = {{-1,1}, {0,1} , {1,1}};
			
		for(int k=0; k<3; k++) {
			int row = i + direct[k][0];
			int col = j + direct[k][1];
			
			if(row >= 0 && row<N && col >=0 && col<M) {
				if(map[row][col] != 'x') {
					if(visit[row][col][k]) return false;
					map[row][col] = 'x';
					if(!dfs(row, col)) {
						map[row][col] = '.';
						visit[row][col][k] = true;
					}
					else return true;
				}
			}
		}
		return false;	
	}
}