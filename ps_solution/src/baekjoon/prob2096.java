package baekjoon;

import java.util.*;
import java.io.*;

public class prob2096 {
	static int N;
	static int[] d_col = { -1, 0, 1 };
	static int[][] board;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		board = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

    for(int i=0;i<N;i++){
      max = Math.max(max, dp_max(0, i, board[0][i]));
      min = Math.min(min, dp_min(0, i, board[0][i]));
    }

		
		System.out.println(max + " " + min);
	}

  static int dp_max(int row, int col, int sum){
    if(row == N - 1){
      return sum;
    }

    if(col == 0){
      return Math.max(dp_max(row + 1, col, sum + board[row + 1][col]), dp_max(row + 1, col + 1, sum + board[row + 1][col + 1]));
    } else if(col == N - 1){
      return Math.max(dp_max(row + 1, col, sum + board[row + 1][col]), dp_max(row + 1, col - 1, sum + board[row + 1][col - 1]));
    }
    return Math.max(Math.max(dp_max(row + 1, col - 1, sum + board[row + 1][col - 1]), dp_max(row + 1, col, sum + board[row + 1][col])), dp_max(row + 1, col + 1, sum + board[row + 1][col + 1]));
  }

  static int dp_min(int row, int col, int sum){
    if(row == N - 1){
      return sum;
    }

    if(col == 0){
      return Math.min(dp_min(row + 1, col, sum + board[row + 1][col]), dp_min(row + 1, col + 1, sum + board[row + 1][col + 1]));
    } else if(col == N - 1){
      return Math.min(dp_min(row + 1, col, sum + board[row + 1][col]), dp_min(row + 1, col - 1, sum + board[row + 1][col - 1]));
    }
    return Math.min(Math.min(dp_min(row + 1, col - 1, sum + board[row + 1][col - 1]), dp_min(row + 1, col, sum + board[row + 1][col])), dp_max(row + 1, col + 1, sum + board[row + 1][col + 1]));
  }
}
