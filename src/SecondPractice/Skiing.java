package SecondPractice;

import java.util.Scanner;

public class Skiing {
    private int row; //行数
    private int col;//列数
    private int [][]map;//保存坐标信息
    private int maxLength=0;//保存最大长度
    private int [][]flag;//存放到达点的最大长度
    //private int []path=new int[1000];//保存最长路径上的点
    public Skiing(int i, int j)
    {//构造函数
        this.row=i;
        this.col=j;
        map=new int[row][col];
        flag=new int[row][col];
        creat();
    }
    public void creat()
    {//创建数组
        Scanner scan= new Scanner(System.in);
        for(int i=0; i<row; i++)
            for(int j=0; j<col; j++)
            {
                map[i][j]=(int)scan.nextInt();//初始化各个点的高度值
                flag[i][j]=-1; //初始化 flag 数组
            }
    }
    public int max_Len(int i, int j) {//递归求在点(i,j)的最大值
        int max = 0;
        int[] temp = {1, 1, 1, 1}; //temp 存放点 height[i][j]的上下左右四个方向的最大长度
        if (flag[i][j] != -1) {//flag！=-1 说明从该点出发的最长路径已经求出
            return flag[i][j];
        } else {
            if (j > 0 && map[i][j - 1] <= map[i][j]) {
                temp[0] = max_Len(i, j - 1) + 1;// 当 可 以 向 左 滑 动 时 temp[0]=max[i][j-1]+1，否则 temp[0]=0；下面类似
            }
            if (j < col - 1 && map[i][j + 1] <= map[i][j]) {
                temp[1] = max_Len(i, j + 1) + 1;//向右
            }
            if(i>0 && map[i-1][j]<=map[i][j]) { //向上
                temp[2] = max_Len(i - 1, j) + 1;
            }
            if(i<row-1 && map[i+1][j]<=map[i][j]) {//向下
                temp[3] = max_Len(i + 1, j) + 1;
            }
        }
        for( int k=0; k<temp.length; k++) { //for 循环找出从 height[i][j]出发的最大 长度
            if (max < temp[k]) {
                max = temp[k];
            }
        }
        flag[i][j]=max;
        return max;
    }
    /**
     * 默认取 temp[i]=1 是因为当该点不能继续滑动时，应返回 1
     */
    public void maxLength()
    {//返回最大长度
        int x=0;
        int y=0;//x,y 记录坐标位置
        for(int i=0; i<row; i++)
            for(int j=0; j<col; j++)
                if(maxLength<max_Len(i,j))
                {
                    maxLength=max_Len(i,j);
                    x=i;
                    y=j;
                }
        System.out.println("最大长度为："+(maxLength));
        System.out.print("最大路径：");
        printPath(x,y);
    }
    public void printPath(int a,int b)
    {//打印最长路径
        int i=a;
        int j=b;
        int x=0;
        int y=0;//x y 记录.坐标
        int max=0;
        while(maxLength>0)//循环输出路径上的点
        {
            int [][]temp=new int[row][col];//暂存前后左右四个方向滑行的长度以及坐标
            System.out.print(map[i][j]+" ");//输出最长路径上的点
            if(j>0 && map[i][j-1]<map[i][j])//向左试探
                temp[i][j-1]=flag[i][j-1];
            if(j<col-1 && map[i][j+1]<map[i][j])//向右试探
                temp[i][j+1]=flag[i][j+1];
            if(i>0 && map[i-1][j]<map[i][j])//向前试探
                temp[i-1][j]=flag[i-1][j];
            if(i<row-1 && map[i+1][j]<map[i][j])//向后试探
                temp[i+1][j]=flag[i+1][j];
            for(int m=0; m<row; m++) //求前后左右四个方向滑雪最大长度点
                for(int n=0; n<col; n++)
                    if(max < temp[m][n])
                    {
                        max=temp[m][n];
                        x=m;
                        y=n;
                    }
            i=x;
            j=y;
            max=0;//i j max 重新赋值
            maxLength--;
        }
    }
    public static void main(String [] args)
    {
        int i;
        int j;
        Scanner scanner= new Scanner(System.in);
        i=scanner.nextInt();   //地图的长和宽
        j=scanner.nextInt();
        Skiing m= new Skiing(i,j);
        m.maxLength();
    }
}

