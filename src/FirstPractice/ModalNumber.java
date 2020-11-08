package FirstPractice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ModalNumber {
   // private static int maxRepeat;
    public static int maxRepeat=0;  //记录最大重数
    public static  int num=0;//记录众数
    public static void Mode(int []A,int left,int right){
        if(left>=right){
            return;
        }
      int r,l,currnetRepart=0;//r,l记录当前中间值在数组中的范围，currentRepart记录当前重数
        int mid=(right+left)/2;
        for( l=left;l<=right;l++){  //找出中间值最左边开始的下标
            if(A[l]==A[mid]){
                break;
            }
        }
        for (r=l+1;r<=right;r++){  //找出中间值右边结束的下标
            if(A[r]!=A[mid]){
                break;
            }
        }
        currnetRepart=r-l;           //当前的重数
        if(currnetRepart>maxRepeat){
            maxRepeat=currnetRepart;
            num=A[mid];
        }
        if(l-left >maxRepeat){       //左边个数如果大于重数，对左边求众数
            Mode(A,left,l-1);
        }
        if(right-r>maxRepeat){    //右边个数大于重数，对右边求众数
            Mode(A,r+1,right);
        }
   }
    public static void main(String[] args) {
        Random df=new Random();
        int n = df.nextInt(100);
        System.out.print(n);
        int []arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]= df.nextInt(100);
        }
        Arrays.sort(arr);    //对生成的数组进行排序
        Mode(arr,0,arr.length-1);
        System.out.print("重数为"+maxRepeat+"众数为"+num);
    }
}
