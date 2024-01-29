package swea;

import java.util.Scanner;
import java.lang.Math;

public class solution {
	static int T;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		while(T-->0) {
			int ans = 0;
			int N = sc.nextInt();
			
			int[] letter = new int[N];
			
			for(int i=0;i<N;i++) {
				letter[i] = sc.nextInt();
			}
			
			boolean[] visited = new boolean[N];
			
			for(int i=0;i<N;i++) {
				if(visited[i]) {
					continue;
				}
				
				int receive = i + letter[i];
				
				if(receive < 0 || receive >= N) {
					continue;
				}else {
					if(Math.abs(letter[i]) == Math.abs(letter[receive]) && letter[i] * letter[receive]< 0) {
						ans++;
						visited[i] = true;
						visited[receive] = true;
					}
				}
			}
			System.out.println(ans);
		}
		sc.close();
		return;
	}

}
