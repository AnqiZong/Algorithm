package SecondPractice;

import java.util.*;

public class OddNumArr {
    static int count=0;//用来保存数据处理次数
    public static void Odd(List<Integer> list){
       // list.sort(int);
        if(list.size()==0){//list列表为0时，表示数据处理完毕
            return;
        }
        count++;             //数据处理次数加一
        Collections.sort(list);//对list中的数进行排序
        int tailnum=list.get(list.size()-1);//每次取最大的数进行处理
        while (list.contains(tailnum)){  //删除数组中出现的跟最大数重复的数
            list.remove(list.indexOf(tailnum));
        }
        //list.remove(list.indexOf(tailnum));
       if ((tailnum/2)%2==0){  //如果最大数除2之后仍是偶数，则把处理后的数据重新加入list
            list.add(tailnum/2);
        }
        Odd(list);
    }
    public static void  main(String args[]){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int []arr=new int[n];
        List<Integer> list=new ArrayList<>();   //保存偶数的种类
        for (int i=0;i<n;i++){
            arr[i]=sc.nextInt();
            if(arr[i]%2==0 && !list.contains(arr[i])) {//当输入的数为偶数且在list中没有时，加入
                list.add(arr[i]);
            }
        }
        for(int i: list){
            System.out.print(i);
        }
        Odd(list);
        System.out.print("需要的次数为:");
        System.out.print(count);
    }
}









