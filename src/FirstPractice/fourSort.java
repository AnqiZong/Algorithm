package FirstPractice;

import java.lang.reflect.Array;
import java.util.Random;

public class fourSort {
    //插入排序
    public static void IntertSort(int []A){
        int i,j,tmp;
        for(i=1;i<A.length;i++){   //记录有序列表的结尾
            for(j=i-1;j>=0;j--){    //新插入的数据从有序列表后往前比较，寻找插入位置。
                if(A[j+1]<A[j]){  //如果后面的数比前面小，进行交换
                    tmp=A[j+1];
                    A[j+1]=A[j];
                    A[j]=tmp;
                }
                else{    //否则数据的位置确定了，不需要继续查找。
                    break;
                }
            }
        }
    }
    //快速排序
    public static void QuickSort(int []A,int left,int right){
        if(left >= right){
           return;
        }
        int tmp = A[left];  //把第一个数据暂存，空出第一个位置
        int lp=left,rp=right;
        while(lp<rp){
            while(A[rp]>=tmp&&lp<rp){  //从后往前找，如果比要第一个数大，继续向前，直到找到比第一个数小为止。
                rp--;
            }
            if(lp<rp){
                A[lp]=A[rp];  //找到比第一个数小的放到数组前面
                lp++;
            }
            while (A[lp]<=tmp&lp<rp){ //从前往后比较，如果比第一个数小继续向后，直到找到比第一个数大。
                lp++;
            }
            if(lp<rp){
                A[rp]=A[lp];  //找到比数组大的放到数据组后面
                rp--;
            }
        }
        A[rp]=tmp;   //找到中间位置，把第一个数放进去
        for(int i=0;i<10;i++){
            System.out.print(A[i]+" ");
        }
        System.out.print("\n");
        QuickSort(A,left,rp-1);  //左部分数组递归
        QuickSort(A,rp+1,right);//右部分数组递归
    }
    public static void merge(int A[],int left,int mid,int right){
        if (left>=right){  //判断数组下标的正确性
            return;
        }
        int[] result= new int[right-left+1]; //暂存排好序的数据
        int lp=left,rp=mid+1,flag=0;  //lp指向左侧数组的下标，rp指向右侧数组下标
        while (lp<=mid&&rp<=right){
            if(A[lp]<=A[rp]){           //lp，rp指向的数据进行比较，把小的数据存入临时数组中
                result[flag++]=A[lp++];
            }else {
                result[flag++]=A[rp++];
            }
        }
        while(lp<=mid){     //如果左侧数据没有遍历完，把剩余数据添加到数组中
            result[flag++]=A[lp];
            lp++;
        }
        while (rp<=right) {  //如果左侧遍历完，右侧没有，把右侧剩余数据加入数组
            result[flag++]=A[rp];
            rp++;
        }
        for (int i=0;i<result.length;i++){
            A[i+left]=result[i];   //把排好序的暂存序列写回到原数组中
        }
    }
    //二路归并排序
    public static void mergeSort(int A[],int left,int right){
        if(left<right){  //判断数组下标范围是否正确
            int mid=(left+right)/2; //把数组分成两部分
            mergeSort(A,left,mid);   //对左边部分继续划分
            mergeSort(A,mid+1,right); //对右边部分划分
            merge(A,left,mid,right);   //对左右两部分数组进行合并操作
        }
    }
    //堆排序
    public static void HeapSort(int arr[]){
        //构建大顶堆
        for(int i=arr.length/2-1;i>=0;i--){
            adjustHeap(arr,i,arr.length);  //从第一个非叶子节点从下至上，从右到左开始调整
        }
        for(int j=arr.length-1;j>0;j--){//把堆顶元素与末尾元素交换，继续构建大顶堆，直到整个数组调整完
            int tp=arr[0]; //将堆顶元素与末尾元素进行交换
            arr[0]=arr[j];
            arr[j]=tp;
            adjustHeap(arr,0,j);//重新对堆进行调整
        }
    }
    public static void adjustHeap(int []arr,int i,int length){
        int temp=arr[i];  //取出当前元素
        for(int k=i*2+1;k<length;k=k*2+1){//从i结点的左子结点开始
            if(k+1<length && arr[k]<arr[k+1]){//比较左右子结点的大小，k指向大的子结点
                k++;
            }
            if (arr[k]>temp){//如果子结点大于父节点，将子结点赋值给父节点
                arr[i]=arr[k];
                i=k;
            }else {
                break;
            }
        }
        arr[i] =temp;//将temp放到最终位置
    }
    public static void main(String []args){
        int []A={4,6,8,7,3,11,54,22,66,10};
       // int []A=new int[100000];
        /*Random df=new Random();
        for(int i=0;i<100000;i++) {
            A[i]= df.nextInt(1000);
        }*/

        long startTime=System.currentTimeMillis();
        //IntertSort(A);
        //HeapSort(A);
        //mergeSort(A,0,99999);
        QuickSort(A,0,9);
        long endTime=System.currentTimeMillis();
       // System.out.println("10000个数时，快速排序耗时:"+(endTime-startTime)+"毫秒");
       // startTime=System.currentTimeMillis();
        //endTime=System.currentTimeMillis();
        //System.out.println("堆排序耗时:"+(startTime-endTime));
        //mergeSort(A,0,9);
        //HeapSort(A);
     /*   for (int i=0;i<10;i++){
            System.out.println(A[i]+' ');
        }*/
        //long endTime=System.currentTimeMillis();
        //System.out.println(endTime);
        //System.out.println("归并排序运行时间:"+(endTime-startTime));
        //QuickSort(A);

    }
}
