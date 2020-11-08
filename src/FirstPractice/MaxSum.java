package FirstPractice;


import java.util.Random;
public class MaxSum {
    int []index=new int[2];   //用来记录最长子序列的下标
    public static int queueMax(int []A,int left,int right){
        if(left == right){
            return A[left];
        }
        int mid =(left+right)/2;
        int lmax=queueMax(A,left,mid);   //求出左侧最长连续子序列
        int rmax=queueMax(A,mid+1,right); //求出右侧最长连续子序列
        int sum=0,rcount=A[mid+1],lcount=A[mid];//rcount保存横跨A[mid]的右侧子序列的和，lcount保存横跨A[mid]左侧子序列和
        for(int l=mid;l>=left;l--){             //sum用来分别保存横跨A[mid]的左侧子序列最大值和右侧子序列最大值
            sum+=A[l];                          //求横跨A[mid]的左侧序列最大值
            if(sum>lcount){
                lcount=sum;
            }
        }
        sum=0;
        for(int r=mid+1;r<=right;r++){  //求横跨A[mid]的右侧序列最大值
            sum+=A[r];
            if(sum>rcount){
                rcount=sum;
            }
        }
        int midmax=rcount+lcount;
        //比较A[mid]左侧，右侧以及横跨A[mid]序列最大值
        return (rmax>=midmax&&rmax>=lmax)?rmax:(lmax>=midmax&&lmax>=rmax)?lmax:midmax;
    }
    public static void main(String []args){
        //int []A={4,-3,5,-2,-1,2,6,-2};
        //System.out.print(queueMax(A,0,7));
        Random ra=new Random();
        int n =ra.nextInt(1000);
        int []A=new int[n];
        for(int i=0;i<n;i++){
            A[i]= ra.nextInt(1)>0.5?ra.nextInt(1)*100:ra.nextInt(1)*(-100);
        }
        System.out.print(queueMax(A,0,n-1));
    }

}
