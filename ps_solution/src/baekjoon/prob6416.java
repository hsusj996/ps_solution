package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

public class prob6416 {
 
    static StringTokenizer st = null;
    static HashMap<Integer, Integer> nodeMap;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int caseCnt = 1;
        while (true) {
            boolean treeFlag = true;
            nodeMap = new HashMap<>();
            int edgeCnt = 0;
            int rootCnt = 0;
            boolean inputFlag = true;
            while(inputFlag){
                st = new StringTokenizer(br.readLine());
                while(st.hasMoreTokens()){
                    int nodeA = Integer.parseInt(st.nextToken());
                    int nodeB = Integer.parseInt(st.nextToken());

                    if(nodeA == 0 && nodeB == 0){
                        inputFlag = false;
                        continue;
                    }
                    if(nodeA == -1 && nodeB == -1){
                        return;
                    }

                    nodeMap.put(nodeA, nodeMap.getOrDefault(nodeA, 0));
                    nodeMap.put(nodeB, nodeMap.getOrDefault(nodeB, 0) + 1);
                    edgeCnt++;
                }
            }

            for(Iterator<Integer> iter = nodeMap.keySet().iterator(); iter.hasNext();){
                int node = iter.next();
                int inEdgeCnt = nodeMap.get(node);

                if(inEdgeCnt == 0){
                    rootCnt++;
                }

                if(inEdgeCnt > 1){
                    treeFlag = false;
                    break;
                }
            }

            if(nodeMap.size() == 0){
                System.out.println("Case " + caseCnt + " is a tree.");
            } else if(treeFlag && rootCnt == 1 && edgeCnt == nodeMap.size() - 1){
                System.out.println("Case " + caseCnt + " is a tree.");
            } else{
                System.out.println("Case " + caseCnt + " is not a tree.");
            }
            caseCnt++;
        }
    }
}
