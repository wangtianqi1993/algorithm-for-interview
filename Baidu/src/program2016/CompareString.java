package program2016;

/**
 * Created by wtq on 4/2/16.
 */
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
public class CompareString {
    /**
     * 有abcdefghigkl 12个字符串，将其所有的排序按字典排序
     * 给出任意一种排序，计算这个排序在所有的排序中是第几小的
     */
    static int charLength = 12;
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextInt()){
            int n = scanner.nextInt();
            String lines[] = new String[n];
            int res[] = new int[n]; //存储比较结果数组
            for(int i=0; i<n; i++){
                lines[i] = scanner.next();
                res[i] = calculate(lines[i]);
            }
            for (int s : res){
                System.out.println(s);
            }
        }
    }
    //计算某个字符序列的位次
    private static int calculate(String line){
        Set<Character> s = new TreeSet<Character>();
        for(char c : line.toCharArray()){
            s.add(c);
        }
        int counts[] = new int[s.size()];
        char[] chars = line.toCharArray();
        for(int i=0; i<chars.length; i++){
            Iterator<Character> iterator = s.iterator();
            int temp = 0;
            Character next;
            //iterator中存储有顺序的字符串，依次与char[]中的比较来确定字母的顺序
            while(iterator.hasNext()){
                next = iterator.next();
                if (next == chars[i]){
                    counts[i] = temp;
                    //查到匹配的元素后就将Set中的该数据删除
                    s.remove(next);
                    break;
                }else{
                    temp++;
                }
            }

        }
        int sum = 1;
        for (int i = 0;i<counts.length;i++){
            sum = sum + counts[i]*factorial(charLength - i -1);
        }
        return sum;
    }
    private static int factorial(int n){
        if(n>1){
            return n*factorial(n-1);
        }else{
            return 1;
        }
    }
}
