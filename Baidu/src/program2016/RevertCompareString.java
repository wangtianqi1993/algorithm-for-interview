package program2016;

/**
 * Created by wtq on 4/4/16.
 */

import java.util.Scanner;

/**
 * input the ith of string and output the string
 *
 */
public class RevertCompareString {

    static int charLength = 12; //the length of string
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextInt()){
            int n = scanner.nextInt();
            int lines[] = new int[n];
            String res[] = new String[n];
            for(int i = 0; i<n; i++){
                lines[i] = scanner.nextInt();
                res[i] = calculate(lines[i] - 1);
            }
            for(String s:res){
                System.out.println(s);
            }
        }
    }
    //计算某个字符序列位次
    private static String calculate(int line){
        char alpha[] = {'a','b','c','d','e','f','g','h'
        ,'i','j','k','l'};
        StringBuilder sb = new StringBuilder();
        for(int i = charLength - 1; i>=0; i--){
            int temp = line/factorial(i);
            line = line % factorial(i);
            sb.append(String.valueOf(alpha[temp]));
            for (int j = temp; j < alpha.length - 1;j++){
                alpha[j] = alpha[j+1];
            }
        }
        return sb.toString();
    }
    private static int factorial(int n){
        if(n >1){
            return n*factorial(n-1);
        }else{
            return 1;
        }
    }
}
