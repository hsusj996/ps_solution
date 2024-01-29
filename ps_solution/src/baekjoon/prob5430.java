package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class prob5430 {
    static int T;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();

        while (T-- > 0) {
            String command = sc.next();
            int N = sc.nextInt();
            String str_arr = sc.next();
            int[] arr = new int[N];
            int cnt = 0;
            boolean error = false;
            for (int i = 0; i < str_arr.length(); i++) {
                char c = str_arr.charAt(i);

                if (c == '[')
                    continue;
                else if (c == ',' || c == ']') {
                    if(sb.length() == 0){
                        break;
                    }
                    int tmp = Integer.parseInt(sb.toString());
                    sb.setLength(0);

                    arr[cnt++] = tmp;
                } else {
                    sb.append(c);
                }
            }

            for (int i = 0; i < command.length(); i++) {
                char cmd = command.charAt(i);

                if (cmd == 'R') {
                    if(arr.length <= 1){
                        continue;
                    }
                    reverse(arr);

                } else if (cmd == 'D') {
                    if(arr.length == 0){
                        error = true;
                        break;
                    }
                    arr = Arrays.copyOfRange(arr, 1, arr.length);
                } else {
                    error = true;
                }
            }

            if(error){
                System.out.println("error");
            }else{
                printArr(arr);
            }
        }

        sc.close();
        return;
    }

    public static void reverse(int[] arr) {
        int bias = 0;

        while(true){
            int left = bias;
            int right = arr.length - bias - 1;

            int tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;

            if(right - left <= 1){
                break;
            }
            bias++;
        }
        return;
    }
    public static void printArr(int[] arr){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<arr.length;i++){
            if(i==0){
                sb.append("[" + arr[i] + ",");
            }else if(i== arr.length - 1){
                sb.append(arr[i] + "]");
            }else{
                sb.append(arr[i] + ",");
            }
        }
        System.out.println(sb.toString());
    }
}
