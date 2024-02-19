package swea;

import java.util.ArrayList;
import java.util.List;

public class test2 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) <= 2) {
                list.remove(i);
            }
        }

        System.out.println(list);
    }
}
