package baekjoon;

import java.util.*;
import java.math.*;

public class prob13305 {
    static int[] price;
    static int[] distance;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        price = new int[N];
        distance = new int[N - 1];

        for (int i = 0; i < N - 1; i++) {
            distance[i] = sc.nextInt();
        }
        for (int i = 0; i < N; i++) {
            price[i] = sc.nextInt();
        }

        int cur_min = Integer.MAX_VALUE;
        BigInteger sum = new BigInteger("0");

        for (int i = 0; i < N - 1; i++) {
            BigInteger price_B = new BigInteger(Integer.toString(price[i]));
            BigInteger distance_B = new BigInteger(Integer.toString(distance[i]));
            BigInteger cur_min_B = new BigInteger(Integer.toString(cur_min));

            if (price[i] < cur_min) {
                sum = sum.add(distance_B.multiply(price_B));
                cur_min = price[i];
            } else {
                sum = sum.add(distance_B.multiply(cur_min_B));
            }
        }

        System.out.println(sum);
    }
}
