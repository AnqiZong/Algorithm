package SecondPractice;

import java.util.HashSet;
import java.util.Set;

public class LCS {
    static HashSet<String> set=new HashSet<>();
    static String lcs;
    static int LCS_Length(int[][] dp,char[] X,char[] Y,int i ,int j) {//c记录二维数组LCS的长度
        if( (i == 0) || (j == 0) ) { //递归结束条件
            dp[i][j] = 0;
        }
        else if(X[i-1] == Y[j-1]) {//当前字符相同时，LCS等于X[i-1]与Y[j-1]的LCS的长度
            dp[i][j] = LCS_Length(dp, X, Y, i - 1, j - 1) + 1;
        } else {
            int p=LCS_Length(dp,X,Y,i-1,j);//p为X下标到i-1，Y到j的LCS长度
            int q=LCS_Length(dp,X,Y,i,j-1);//q为X下标到i，Y下标到j-1的LCS长度
            if(p >= q) {//比较X前一个字符与Y以及Y的前一个字符与X的LCS大小
                dp[i][j] = p;
            } else{
                dp[i][j]=q;
            }
        }
        return dp[i][j];//返回当前LCS
    }
    public static void LCS_path(int [][]dp,char[]a,char []b,int i ,int j,int num) {
        while (i>0 && j>0){
            if(a[i-1]==b[j-1]){//对两个字符串从后向前遍历，元素相等加入c中
                lcs+=a[--i];
                j--;
                if (lcs.length()==num){   //z的长度与LCS相等时，把c逆序输出
                   if(!set.contains(lcs)){
                       set.add(new StringBuffer(lcs).reverse().toString());
                   }
                    return;
                }
            }
            else if(dp[i-1][j]<dp[i][j-1]){//根据dp[i-1][j]与dp[i][j-1]的大小，来确定前一个字符是左边还是上面
                j--;//前一个字符在左边
            }
            else if(dp[i][j-1]<dp[i-1][j]){
                i--;//前一个字符在上面
            }else  {
                i--;
                String temp=lcs;       //暂存lcs
                LCS_path(dp,a,b,i,j,num);  //求左分支对应的lcs
                j--;
                lcs=temp;
                LCS_path(dp,a,b,i+1,j,num); //求右分支对应的lcs
            }
        }
    }
    public static void main(String[] args) {
        String X = "ABCD";
        String Y = "ACBD";
        char[] x = X.toCharArray();
        char[] y = Y.toCharArray();
        int MAX = x.length>y.length?x.length+1:y.length+1;
        int[][] dp = new int[MAX][MAX];
        int lcs_length=LCS_Length(dp,x,y,x.length,y.length);
        System.out.println(lcs_length);
        for(int i=0;i<MAX;i++){
            for(int j=0;j<MAX;j++){
                if(lcs_length==dp[i][j]){
                    if(dp[i][j]!=dp[i-1][j] &&dp[i][j]!=dp[i][j-1]){
                        lcs="";
                        LCS_path(dp,x,y,i,j,lcs_length);
                    }
                }
            }
        }
        for(String str:set){
            System.out.print(str);
            System.out.print('\n');
    }
    }
}