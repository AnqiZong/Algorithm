package FirstPractice;

import java.util.HashMap;
import java.util.Map;

public class TwoNumSum {
    public static int[] Count(int []A,int S){
        Map<Integer,Integer> map =new HashMap<>();
        for(int i=0;i<A.length;i++){  //遍历数组，只把第一次出现的值和坐标放入
            if(!map.containsKey(A[i])){
                map.put(A[i],i);
            }
        }
        for (int i=0;i<A.length;i++){
            if(map.containsKey(S-A[i])){//遍历数组，查看是否存在两数和为给定的值
                return new int[]{i,map.get(S-A[i])};
            }
        }
        return new int[-1];
    }
    public static void main(String args[]){
        int A[]={1,2,3,4,5,6,7,8,9};
        int []b=Count(A,11);
        for(int i=0;i<b.length;i++){
            System.out.print(b[i]);
        }
    }
}
