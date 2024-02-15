package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class prob2417 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        
        BigInteger ret = BinarySearch(input);
        System.out.println(ret);
    }

    private static BigInteger BinarySearch(String input) {
        BigInteger n = new BigInteger(input);
        BigInteger start = new BigInteger("0");
        BigInteger end = new BigInteger(input);
        BigInteger min = new BigInteger("0");

        while(start.compareTo(end) != 1){
            BigInteger mid = start.add(end).divide(BigInteger.valueOf(2));
            
            if(mid.pow(2).compareTo(n) == 0){
                return mid;
            }
            else if(mid.pow(2).compareTo(n) == -1){
                start = mid.add(BigInteger.valueOf(1));
            } else{
                min = mid;
                end = mid.subtract(BigInteger.valueOf(1));
            }
        }
        return min;
    }
}
