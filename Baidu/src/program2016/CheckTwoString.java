package program2016;
import java.util.HashMap;
import java.util.Map;
import java.util.PrimitiveIterator;
import java.util.Scanner;

/**
 * Created by wtq on 4/5/16.
 */

/**
 * 判断字符串b的所有字符是否在字符串a中出现过，a b都可能
 * 包含汉字的字符串，b中重复出现的汉字a中至少重复相同的次数
 */
public class CheckTwoString {
    public static void main(String[] args){
        Map<Integer, Integer> aMap = new HashMap<Integer,Integer>();

        Scanner input = new Scanner(System.in);
        while(input.hasNextLine()){
            String a = input.nextLine();
            String b = input.nextLine();
            char[] chars = a.toCharArray();
            for (char c : chars) {
                if (aMap.keySet().contains((int)c)){
                    int temp = aMap.get((int)c);
                        aMap.put((int)c,(temp + 1));
                    }else{
                        aMap.put((int)c, 1);
                    }
                }

            char[] chars1 = b.toCharArray();
            boolean flag = true;
            for (char c: chars1){
                if(aMap.keySet().contains((int)c)){
                    int temp = aMap.get((int)c);
                    if(temp == 1){
                        aMap.remove((int)c);
                    }else{
                        aMap.put((int)c, (temp-1));
                    }
                }else{
                    flag = false;
                    break;
                }
            }
            if(flag){
                System.out.println(1);
            }else{
                System.out.println(0);
            }
            aMap.clear();
        }
    }
}
