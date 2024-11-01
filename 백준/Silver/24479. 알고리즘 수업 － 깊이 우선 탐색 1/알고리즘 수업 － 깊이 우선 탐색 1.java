import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>(); // 정점들의 정보를 기록할 그래프
    static int[] check; // 방문한 정점을 기록할 배열
    static int count; // 방문 순서

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());

        int vertex = Integer.parseInt(st.nextToken());
        int edge = Integer.parseInt(st.nextToken());
        int startVertex = Integer.parseInt(st.nextToken());

        check = new int[vertex+1];

        for(int i =0; i < vertex+1; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine());
            int fromVertex = Integer.parseInt(st.nextToken());
            int toVertex = Integer.parseInt(st.nextToken());

            graph.get(fromVertex).add(toVertex);
            graph.get(toVertex).add(fromVertex);
        }
		
        for(int i = 1; i < graph.size(); i++) {
            Collections.sort(graph.get(i));
        }

        count = 1;
        
        dfs(startVertex);

        for(int i = 1; i < check.length; i++) {
            sb.append(check[i]).append("\n");
        }
        System.out.println(sb);
    }
    
	// 깊이 우선 탐색 메서드
    private static void dfs(int vertex) {
        check[vertex] = count; // 현재 방문하고있는 정점에 순서 저장

            for(int i = 0; i < graph.get(vertex).size(); i++) {
                int newVertex = graph.get(vertex).get(i);
                
                if(check[newVertex] == 0){
                    count++;
                    dfs(newVertex);
                }
            }
    }
}
