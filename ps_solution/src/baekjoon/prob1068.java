package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class prob1068 {
  static class Node {
    int num;
    Node parent;
    List<Node> childrenList;

    public Node(int num) {
      this.num = num;
      this.childrenList = new ArrayList<>();
    }
  }

  static int N;
  static StringTokenizer st = null;
  static Node[] nodeArr;
  static int root;
  static int leafCnt;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    nodeArr = new Node[N];
    for (int i = 0; i < N; i++) {
      nodeArr[i] = new Node(i);
    }

    leafCnt = 0;
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      int parent = Integer.parseInt(st.nextToken());
      if (parent == -1) {
        root = i;
        continue;
      }

      nodeArr[i].parent = nodeArr[parent];
      nodeArr[parent].childrenList.add(nodeArr[i]);
    }

    // 삭제노드와 부모노드 간 연결 끊기
    int deleteNode = Integer.parseInt(br.readLine());
    if (deleteNode == root) {
      System.out.println(0);
      return;
    }

    Node deleteParent = nodeArr[deleteNode].parent;
    for (int i = 0; i < deleteParent.childrenList.size(); i++) {
      if (deleteParent.childrenList.get(i).num == deleteNode) {
        deleteParent.childrenList.remove(i);
        break;
      }
    }

    // 루트에서부터 DFS 수행하여 리프노드 카운트하기
    DFS(root);

    System.out.println(leafCnt);

  }

  private static void DFS(int n) {
    if (nodeArr[n].childrenList.size() == 0) {
      leafCnt++;
      return;
    }

    for (int i = 0; i < nodeArr[n].childrenList.size(); i++) {
      DFS(nodeArr[n].childrenList.get(i).num);
    }
  }
}
