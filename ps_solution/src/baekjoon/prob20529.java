package baekjoon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class prob20529 {

    // class MBTI {
    //     boolean mbti[] = new boolean[4];

    //     public MBTI(String str) {
    //         if (str.charAt(0) - 'I' == 0) {
    //             mbti[0] = false;
    //         } else
    //             mbti[0] = true;
    //         if (str.charAt(1) - 'N' == 0) {
    //             mbti[1] = false;
    //         } else
    //             mbti[1] = true;
    //         if (str.charAt(2) - 'F' == 0) {
    //             mbti[2] = false;
    //         } else
    //             mbti[2] = true;
    //         if (str.charAt(3) - 'P' == 0) {
    //             mbti[3] = false;
    //         } else
    //             mbti[3] = true;
    //     }
    // }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        while (T-- > 0) {
            int N = sc.nextInt();
            HashSet<String> mbti = new HashSet<>();
            ArrayList<String> mbti_list = new ArrayList<>();

            while (N-- > 0) {
                String input = sc.next();

                mbti.add(input);
            }
            for (String tmp : mbti) {
                mbti_list.add(tmp);
            }
            int min_distance = get_Min(mbti_list);

            System.out.println(min_distance);
        }

        sc.close();
    }

    public static int get_Min(ArrayList<String> mbti_list) {
        int min = 100;

        String[] selected_mbti = new String[3];

        for (int i = 0; i < mbti_list.size(); i++) {
            for (int j = i + 1; j < mbti_list.size(); j++) {
                for (int k = j + 1; k < mbti_list.size(); k++) {
                    selected_mbti[0] = mbti_list.get(i);
                    selected_mbti[1] = mbti_list.get(j);
                    selected_mbti[2] = mbti_list.get(k);

                    int sum = 0;
                    sum += get_Distance(selected_mbti[0], selected_mbti[1]);
                    sum += get_Distance(selected_mbti[1], selected_mbti[2]);
                    sum += get_Distance(selected_mbti[0], selected_mbti[2]);

                    if(sum <= min)
                        min = sum;
                }
            }
        }

        return min;
    }

    public static int get_Distance(String A, String B){
        int distance = 0;
        for(int i=0;i<4;i++){
            if(A.charAt(i) != B.charAt(i))
                distance++;
        }
        return distance;
    }
}
