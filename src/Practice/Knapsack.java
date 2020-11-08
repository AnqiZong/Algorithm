package Practice;

import java.util.Random;
import java.util.Scanner;

public class Knapsack {
    static public void Zero_One(int []weight,int []value,int capacity){
        int i,j;
        int []sum=new int[capacity+1];
        for(i=1;i<weight.length;i++){
            for(j=capacity;j>=weight[i];j--){
                sum[j]=Math.max(sum[j],sum[j-weight[i]]+value[i]);
            }
        }
        System.out.print(sum[capacity]);
        //System.out.print(weight.length);
       /* for(i=1;i<weight.length;i++){
            for(j=1;j<=capacity;j++){
                if(weight[i]>j){
                    sum[i][j]=sum[i-1][j];
                }else {
                    sum[i][j]=Math.max(sum[i-1][j],sum[i-1][j-weight[i]]+value[i]);
                }
            }
        }*/

    }
    static public void Complete(int []weight,int []value,int capacity){
        //int [][]sum = new int[weight.length+1][capacity+1];
        int []sum =new int[capacity+1];
        for(int i=1;i<weight.length;i++){
            for(int j=weight[i];j<=capacity;j++){
                    sum[j]=Math.max(sum[j],sum[j-weight[i]]+value[i]);
            }
        }
        System.out.print(sum[capacity]);
    }
    static public  void Multiple(){
        Scanner sc = new Scanner(System.in);
        int n,m; //物品种类  书包容量
        n = sc.nextInt();
        m = sc.nextInt();
        int v,w,s;//体积、价值、数量
        int num = 1; //拆分计数
        int []value = new int[100];
        int []weight = new int[100];
        int []sum = new int[100];
        for(int i=0;i<n;i++){
            v = sc.nextInt();
            w = sc.nextInt();
            s = sc.nextInt();
            for(int j=1;j<=s;j=j*2){//二进制拆分
                value[num]=v*j;
                weight[num++]=w*j;
                s-=j;
            }
            if(s!=0){//如果还有剩余
                value[num]=v*s;
                weight[num++]=w*s;
            }
        }
        for(int i=1;i<num;i++){
            for(int j=m;j>=weight[i];j--){
                sum[j] = Math.max(sum[j],sum[j-weight[i]]+value[i]);
            }
        }
        System.out.print(sum[m]);
    }
    static public void main(String args[]){
       /* int num,capacity;
        Scanner ra=new Scanner(System.in);
        num=ra.nextInt();
        capacity= ra.nextInt();

        int []value= new int[num+1];
        int []weight=new int[num+1];
        int [][]sum = new int[num+1][capacity+1];
        for(int i=1;i<=num;i++){
            value[i] = ra.nextInt();
            weight[i] = ra.nextInt();
        }*/
        //Zero_One(weight,value,capacity);
       // Complete(weight,value,capacity);
        //System.out.print(sum[num][capacity]);
        Multiple();
    }
}
