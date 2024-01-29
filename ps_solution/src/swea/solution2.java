package swea;

import java.util.Arrays;
import java.util.Scanner;

public class solution2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
			int D = sc.nextInt();
			
			int[] map = new int[D + 1];
			
			Arrays.fill(map, 1);
			 
			for(int i=0;i<N;i++) {
				int pos = sc.nextInt();
				int time = sc.nextInt();
				
				map[pos] += time;
			}
			
			int left_pos = 0;
			int right_pos = D;
			
			while(left_pos != right_pos) {
				map[left_pos]--;
				map[right_pos]--;
				
				if(map[left_pos] == 0) {
					left_pos++;
				}
				if(map[right_pos] == 0) {
					right_pos--;
				}
			}
			
			System.out.println("#" + test_case + " " + left_pos);
		}

		sc.close(); // 사용이 끝난 스캐너 객체를 닫습니다.
	}

}
