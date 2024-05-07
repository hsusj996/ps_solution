import java.util.LinkedList;

public class test {
    public static void main(String[] args) {
        LinkedList<Integer> list1 = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();

        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(5);

        list2 = list1;

        list1 = new LinkedList<>();

        System.out.println(list2.toString());
    }
}
