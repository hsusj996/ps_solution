import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    static StringBuilder result = new StringBuilder();
    static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    static int N;
    static int cnt;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());

            if(maxHeap.size() == minHeap.size()){
                maxHeap.add(n);
            } else{
                minHeap.add(n);
            }
            
            if(maxHeap.size() > 0 && minHeap.size() > 0){
                if(maxHeap.peek() > minHeap.peek()){
                    int i1 = maxHeap.poll();
                    int i2 = minHeap.poll();
                    
                    maxHeap.add(i2);
                    minHeap.add(i1);
                }
            }
            
            result.append(maxHeap.peek()).append("\n");
        }

        System.out.println(result.toString());
    }

}