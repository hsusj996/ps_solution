package baekjoon;

import java.util.*;
import java.io.*;

public class prob5639 {
  static class Node {
    int num;
    Node l_child, r_child;

    Node(int num) {
      this.num = num;
    }

    Node(int num, Node l_child, Node r_child) {
      this.num = num;
      this.l_child = l_child;
      this.r_child = r_child;
    }

    void AddChild(int n) {
      if (n < this.num) {
        if (this.l_child == null) {
          this.l_child = new Node(n);
        } else
          this.l_child.AddChild(n);
      } else {
        if (this.r_child == null) {
          this.r_child = new Node(n);
        } else
          this.r_child.AddChild(n);
      }
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    Node root = new Node(Integer.parseInt(br.readLine()));

    while (true) {
      String input = br.readLine();

      if (input == null || input.equals("")) {
        break;
      }

      int node = Integer.parseInt(input);
      root.AddChild(node);
    }

    PostOrder(root);
  }

  static void PostOrder(Node node) {
    if (node == null) {
      return;
    }
    PostOrder(node.l_child);
    PostOrder(node.r_child);
    System.out.println(node.num);
  }
}
