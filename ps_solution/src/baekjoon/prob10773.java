package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class prob10773 {
    public static void main(String [] args) throws NumberFormatException, IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stk = new Stack<>();

        int ans = 0;
        int K = Integer.parseInt(br.readLine());

        while(K --> 0){
            int buf = Integer.parseInt(br.readLine());

            if(buf != 0){               //0이 아니라면 삽입
                stk.push(buf);
            }else{
                if(!stk.isEmpty()){     //0이라면 비어있는 지 판단하고 제거 혹은 넘김
                    stk.pop();
                }
            }
        }

        while(true){                    //스택 요소 꺼내면서 합산
            if(!stk.isEmpty()){
                ans += stk.pop();
            }else{
                break;
            }
        }
        System.out.println(ans);
    }
}
