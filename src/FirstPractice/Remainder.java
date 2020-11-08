package FirstPractice;

public class Remainder {
    public static long divice(long x, long p){
        if(p==0){
            return 1;
        }
        long re=divice(x,p/2);  //求x^(p/2)%(1e9+7)的模
        long ans =(long)(re*re%(1e9+7));//求x^p的模
        if (p%2==1){   //若p为奇数，需要余数与x相乘再取模
            ans=(long)(ans*x%(1e9+7));
        }
        return ans;
    }
    public static void main(String []args){
        System.out.print(divice((long) 1e9, (long) 1e18));
    }
}
