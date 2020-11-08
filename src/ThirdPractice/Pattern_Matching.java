package ThirdPractice;

import java.util.Random;

public class Pattern_Matching {
    //判断是否为素数
    static public boolean isPrime(int num){
        for(int i=2;i<Math.sqrt(num);i++){
            if(num %i==0){
                return false;
            }
        }
        return true;
    }
    //产生素数
    static public int random_prime(int min,int max){
        //获取系统当前时间设置随机数种子产生素数。
        long t=System.currentTimeMillis();
        Random ra=new Random(t);
        int num=ra.nextInt()%(max-min)+min;  //获取一个一定范围内的数并判断是否为素数
        while (num>=min){
            if(isPrime(num)){
                break;
            }
            num--;  //不是素数时，对数减一，继续判断
        }
        return num;
    }
    //求指纹
    static public int getIP(char []str,int len,int p,int pos){
        int ip=0;
        for (int i=0+pos;i<len;i++){
            ip=(2*ip+str[i]-'0')%p;
        }
        return ip;
    }
    static public int MonteCarlo(char []x,char []y,int pos,int p){
     int j=0;
     int Ipx,Ipy,wp;      //Ipx为字符串x产生的指纹，Ipy为字符串y的指纹，wp为2^m,m为y的长度
     int x_len = x.length;
     int y_len = y.length;

     Ipx = getIP(x,y_len,p,pos);  //对x从X[0]位置产生指纹
     Ipy = getIP(y,y_len,p,0);//产生模式串Y的指纹
     wp = 1;
     for(int i=0;i<y_len;i++){  //计算wp，用来通过Ip(x(j))求出Ip(x(j+1))
         wp = (wp * 2) % p;
     }
     while (j<=x_len-y_len-pos){
         if(Ipx == Ipy){  //模式串和主串指纹相等，返回主串的下标j
             return j;
         }else{
             if(j==x_len-y_len){//防止j超过主串的长度
                 return -1;
             }
             //利用Ip(x(j))求出Ip(x(j+1))
             Ipx = ( 2 * Ipx - wp * ( x[j] - '0' ) + ( x[j+y_len] - '0' ) ) % p;
             if( Ipx < 0 ) Ipx += p;
             if( Ipx >= p ) Ipx -= p;
             j++;
         }
     }
        return -1;
    }
    static public int LasVegas(char []x,char y[],int pos,int p){
        int j = 0,l;
        int Ipx, Ipy, wp;   //Ipx为字符串x产生的指纹，Ipy为字符串y的指纹，wp为2^m,m为y的长度
        int x_len = x.length;
        int y_len=y.length;
        Ipx = getIP(x,y_len,p,pos);    //对x从X[0]位置产生指纹
        Ipy = getIP(y,y_len,p,0);//产生模式串Y的指纹
        wp = 1;
        for(int i = 0; i < y_len; i++)   //计算wp，用来通过Ip(x(j))求出Ip(x(j+1))
            wp = (wp * 2) % p;
        while (j<=x_len - y_len -pos){
            if(Ipx == Ipy){   //当x(j)与y的指纹相等时，从x[j]位置开始与模式串Y进行比较，当两者完全相等时，返回j
                for (l=0;l<y_len;l++){
                    if (x[j+l]!=y[l]){
                        break;
                    }
                }
                if(l==y_len){
                    return j;
                }
            }
            if(j==x_len-y_len){   //防止j越过字符串的长度
                return -1;
            }
            //当指纹不相等时，根据IP(x(j))计算出IP(x(j+1));
            Ipx = ( 2 * Ipx - wp * ( x[j] - '0' ) + (x[j + y_len] - '0') ) % p;
            if(Ipx < 0) Ipx += p;
            if(Ipx >= p) Ipx -= p;
            j++;
        }
        return -1;
    }
    static public int KMP(char []str,char []substr,int []next){
        int i=0,j=0;
        while (i<str.length&&j<substr.length){
            if( j == -1 ||str[i]==substr[j]){ //当主串与模式串指针指向字符相等时，分别后移一步
                i++;
                j++;
            }else{
                j=next[j];  //当指向的字符不相等时，对模式串的指针回溯
            }
        }
        if(j>=substr.length){  //模式串遍历完一遍，主串中存在模式串
            return i-substr.length;
        }else {
            return -1;
        }
    }
    static public void getNextval(char []substr,int []next){
        int i=0,j=-1;
        next[0]=-1;
        while(i<substr.length-1){
            if(j == -1 || substr[i] == substr[j]){
                i++;
                j++;
                next[i]=j;  //当i,j相等时，对next[i]赋值j
            }
            else{
                j = next[j];  //当i，j指向的字符不相等时，j需要回溯
            }
        }
    }
    static public void main(String args[]){
        Random ra =new Random();
        int MAXSIZE=5000;
        long t_start,t_end,KMPtime = 0,LVtime=0,MCtime=0;
        int m,n,count=0,miss=0;
        int []index_KMP=new int[MAXSIZE];
        int []index_MC=new int[MAXSIZE];
        int []index_LV=new int[MAXSIZE];
        int minlen,maxlen,tmp;  //主串和子串的长度
        while (count<5000){
            minlen = ra.nextInt(5000);
            while (minlen ==0){
                minlen = ra.nextInt(5000);
            }
            while(true) {
                maxlen = ra.nextInt(50000) ;
                if (maxlen > minlen) {
                    break;
                }
            }
            char []str = new char[maxlen];
            char []substr = new char[minlen];

            for(int i=0;i<minlen;i++){
                tmp=ra.nextInt();
                if(tmp>0.5){
                    substr[i]='1';
                }else {
                    substr[i]='0';
                }
            }
            for(int i=0;i<maxlen;i++){
                tmp=ra.nextInt();
                if(tmp>0.5){
                    str[i]='1';
                }else {
                    str[i]='0';
                }
            }
            int []prime=new int[MAXSIZE];
            for(int i=0;i<MAXSIZE;i++){
                prime[i]=random_prime(minlen*minlen,Integer.MAX_VALUE);
            }
            //KMP
            int []next=new int[minlen];
            t_start=System.currentTimeMillis();
            getNextval(substr,next);
            index_KMP[count]=KMP(str,substr,next);
            t_end=System.currentTimeMillis();
            KMPtime += t_end-t_start;

            //MonteCarlo 算法
            t_start=System.currentTimeMillis();
            index_MC[count]=MonteCarlo(str,substr,0,prime[count]);
            t_end=System.currentTimeMillis();
            MCtime+=t_end-t_start;

            //Las Vrgas 算法
            t_start=System.currentTimeMillis();
            index_LV[count]=LasVegas(str,substr,0,prime[count]);
            t_end= System.currentTimeMillis();
            LVtime+=t_end-t_start;
            /*if(index_KMP[count]!=index_LV[count]){
                System.out.print("错误\n");
                System.out.print(index_KMP[count]+"   "+index_LV[count]+"\n");
                for (int i=0;i<minlen;i++){
                    System.out.print(substr[i]);
                }
                System.out.print("\n");
                for (int i=0;i<maxlen;i++){
                    System.out.print(str[i]);
                }
                System.out.print("\n");
            }
            if(index_MC[count]!=index_LV[count]){
                System.out.print("不一样/n");
            }*/
            count++;

        }
        for(int i=0;i<MAXSIZE;i++){
            if(index_KMP[i]!=index_MC[i]){
                miss++;
            }
        }
        System.out.print("KMP执行时间:"+KMPtime+"ms"+"\n");
        System.out.print("MonteCarlo执行时间:"+MCtime+"ms"+"\n");
        System.out.print("LasVegas执行时间:"+LVtime+"ms"+"\n");
        System.out.print("MonteCarlo出错率:"+((double)miss/MAXSIZE)*100+"%"+"\n");
    }
}
