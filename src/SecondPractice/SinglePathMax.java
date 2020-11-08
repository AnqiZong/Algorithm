package SecondPractice;

import java.util.Scanner;

public class SinglePathMax {
    public static void Max(int [][]map,int [][]path,int n,int m){
        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                if(i==0 && j==0){   //(0,0)点时的值
                    path[i][j]=map[i][j];
                }else if(i==0){   //最左边一列只能从上面过来
                    path[i][j]=path[i][j-1]+map[i][j];
                }else if(j==0){//最上边的一行只能才左边过来
                    path[i][j]=path[i-1][j]+map[i][j];
                } else{//其余点选取从上面与从左边的最大值
                    path[i][j]=Math.max(path[i-1][j],path[i][j-1])+map[i][j];
                }
            }
        }
    }
    public static void main(String []args){
        Scanner sc=new Scanner(System.in);
        int n= sc.nextInt();
        int m=sc.nextInt();
        int [][]map=new int[n][m];
        int [][]sum =new int[n][m];
        for (int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                map[i][j]=sc.nextInt();
            }
        }
        Max(map,sum,n,m);
        System.out.print("最大值为:"+sum[n-1][m-1]);
    }
}
