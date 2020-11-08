package SecondPractice;

import java.util.Scanner;

class ReturnPathMax {
    public static int max(int a,int b,int c,int d){
        if(a<b){
            a=b;
        }
        if(a<c){
            a=c;
        }
        if(a<d){
            a=d;
        }
        return a;
    }
    public static int PathMax(int[][][][] pathValue, int[][] map, int m, int n){
        int y2;
        for (int x1 = 1; x1 <= m; x1++) {
            for (int y1 = 1; y1 <= n; y1++) {
                for (int x2 = 1; x2 <= m; x2++) {
                    if (x1 + y1 - x2 > 0 &&x1 + y1 - x2<=n) { //让两点移动的步数相等并判断点是否在规定范围内
                         y2 = x1 + y1 - x2;
                    } else continue;
                    //求(x1,y1)、(x2,y2)从左和从上方过来的最大值
                    pathValue[x1][y1][x2][y2] = max(pathValue[x1-1][y1][x2-1][y2], pathValue[x1][y1-1][x2-1][y2], pathValue[x1][y1-1][x2][y2-1], pathValue[x1-1][y1][x2][y2-1]) + map[x1][y1] + map[x2][y2];
                    if (x1 == x2) {//当x1=x2时，经过的是同一点。
                        pathValue[x1][y1][x2][y2] -= map[x1][y1];
                    }
                }
            }
        }
        return pathValue[m][n][m][n];
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int m=sc.nextInt();
        int n=sc.nextInt();
        int [][]map=new int[m+1][n+1];      //保存位置信息
        for (int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                map[i][j]=sc.nextInt();
            }
        }
        int [][][][]pathValue=new int[m+1][n+1][m+1][n+1];  //保存点(x1,y1)到(x2,y2)经过点的最大和
        int maxValue=PathMax(pathValue,map,m,n);
        System.out.print("最大值为"+maxValue);
    }
}
