import java.util.*;
import java.io.*;

public class Main {
    
	static int A, B, C;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	A = Integer.parseInt(st.nextToken());
    	B = Integer.parseInt(st.nextToken());
    	C = Integer.parseInt(st.nextToken());
    	
    	System.out.println(bfs(A, B, C) ? 1 : 0);
    }
    
    static boolean bfs(int a, int b, int c) {
    	if((a + b + c) % 3 != 0) return false;
    	
    	Queue<Stone> q = new LinkedList<>();
    	boolean[][] isChecked = new boolean[1501][1501];
    	
    	q.add(new Stone(a, b, c));
    	isChecked[a][b] = true;
    	int maxqsize = 0;
    	while(!q.isEmpty()) {
			maxqsize = Math.max(maxqsize, q.size());
    		Stone s = q.poll();
    		
    		a = s.a;
    		b = s.b;
    		c = s.c;
    		
    		// 세 개가 모두 같아지면 true 반환
    		if(a == b && b == c) {
    			return true;
    		}
    		
    		// 갯수가 다른 두 개 골라서 연산
    		if(a != b) {
    			int na = a > b ? a-b : a+a;
    			int nb = a > b ? b+b : b-a;
    			
    			if(!isChecked[na][nb]) {
    				q.add(new Stone(na, nb, c));
    				isChecked[na][nb] = true;
    			}
    		}
    		
    		if(b != c) {
    			int nb = b > c ? b-c : b+b;
    			int nc = b > c ? c+c : c-b;
    			
    			if(!isChecked[nb][nc]) {
    				q.add(new Stone(a, nb, nc));
    				isChecked[nb][nc] = true;
    			}
    		}
    		
    		if(a != c) {
    			int na = a > c ? a-c : a+a;
    			int nc = a > c ? c+c : c-a;
    			
    			if(!isChecked[na][nc]) {
    				q.add(new Stone(na, b, nc));
    				isChecked[na][nc] = true;
    			}
    		}
    		
    	}
    	
    	// Queue가 끝나도 3개가 모두 같아지지 않으면 false 반환
    	return false;
    }
    
    static class Stone{
    	int a, b, c;
    	
    	Stone(int a, int b, int c){
    		this.a = a;
    		this.b = b;
    		this.c = c;
    	}
    }
  
}