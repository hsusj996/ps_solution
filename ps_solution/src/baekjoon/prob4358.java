package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class prob4358 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        Map<String, Integer> treeMap = new HashMap<>();
        PriorityQueue<String> pq = new PriorityQueue<>();
        int cnt = 0;

        while(true){
            String input = br.readLine();
            if(input == null || input.length() == 0) break;
            cnt++;
            if(!treeMap.containsKey(input)){
                treeMap.put(input, 1);
                pq.add(input);
                continue;
            }

            treeMap.replace(input, treeMap.get(input) + 1);
        }

        while(!pq.isEmpty()){
            String tree = pq.poll();
			int count = treeMap.get(tree);
			double per = (double)(count * 100.0) / cnt;
			
			result.append(tree + " " + String.format("%.4f", per) + "\n");
        }

        System.out.println(result);
    }
}
