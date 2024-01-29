package baekjoon;

import java.io.*;
import java.util.*;

public class prob1987 {
	static class xy {
		int x;
		int y;

		public xy(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int R;
	static int C;
	static char[][] board;
	static int[] d_row = { -1, 0, 1, 0 };
	static int[] d_col = { 0, 1, 0, -1 };
	static boolean[] alpha_visited;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		board = new char[R + 1][C + 1];
		alpha_visited = new boolean[26];
		for (int i = 1; i <= R; i++) {
			String input = br.readLine();
			for (int j = 0; j < C; j++) {
				board[i][j + 1] = input.charAt(j);
			}
		}

		alpha_visited[board[1][1] - 'A'] = true;
		dfs(new xy(1, 1), 1);

		System.out.println(ans);
	}

	static void dfs(xy cdnt, int depth) {
		for (int i = 0; i < 4; i++) {
			int new_x = cdnt.x + d_row[i];
			int new_y = cdnt.y + d_col[i];

			if (new_x <= 0 || new_x > R || new_y <= 0 || new_y > C) {
				continue;
			}

			if(alpha_visited[board[new_x][new_y] - 'A']) {
				continue;
			}
			
			alpha_visited[board[new_x][new_y] - 'A'] = true;
			dfs(new xy(new_x, new_y), depth + 1);
			alpha_visited[board[new_x][new_y] - 'A'] = false;
		}
		ans = Math.max(ans, depth);
	}

}