package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class prob4949 {
    public static void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            String str = br.readLine();
            Stack<Character> stk = new Stack<>();

            //온점이면 종료
            if(str.compareTo(".") == 0){
                break;
            }

            //문자열 탐색
            for(int i=0;i<str.length();i++){
                char tmp = str.charAt(i);
                
                if(tmp == '(' || tmp == '['){   //tmp 여는 괄호라면 push
                    stk.push(tmp);
                }
                else if(tmp == ')'){
                    if(stk.isEmpty()){          //닫는 괄호지만 스택이 비어있는 경우 그냥 삽입
                        stk.push(tmp);
                    }
                    if(stk.peek() == '('){      //닫는 괄호라면 peek확인 후 pop
                        stk.pop();
                    }else{
                        stk.push(tmp);
                    }
                }else if(tmp == ']'){
                    if(stk.isEmpty()){          
                        stk.push(tmp);
                    }
                    if(stk.peek() == '['){
                        stk.pop();
                    }else{
                        stk.push(tmp);
                    }
                }
            }
            if(stk.isEmpty()){
                System.out.println("yes");
            }else{
                System.out.println("no");
            }
        }

    }
}
