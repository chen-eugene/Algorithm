package com.eugene.algorithm;

import java.util.Arrays;

public class Sort {


    /*********************非线性时间比较类排序*******************

     /*******************交换排序****************************

     /**
     * 冒泡排序
     * 从后向前冒泡，
     * 对5,3,8,6,4这个无序序列进行冒泡排序。
     * 首先从后向前冒泡，4和6比较，把4交换到前面，序列变成5,3,8,4,6。
     * 同理4和8交换，变成5,3,4,8,6,3和4无需交换。5和3交换，变成3,5,4,8,6,3.这样一次冒泡就完了，
     * 把最小的数3排到最前面了
     *
     * @param arr
     */
    public static void bubbleSortFromTail(int arr[]) {
        if (arr == null || arr.length <= 0)
            return;

        int len = arr.length;

        for (int i = 0; i < len - 1; i++) {
            for (int j = len - 1; j > i; j--) {
                if (arr[j] < arr[i]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

    }

    /**
     * 冒泡排序 从前向后冒泡
     *
     * @param arr
     */
    public static void bubbleSortFromHead(int arr[]) {
        if (arr == null || arr.length <= 0)
            return;

        int len = arr.length;

        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[i]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

    }


    /**
     * 快速排序   递归调用  挖坑+分治
     *
     * @param arr
     * @param l
     * @param r
     */
    public static void quickSort(int[] arr, int l, int r) {
        //递归的基准条件
        if (l >= r) return;
        int i = l;
        int j = r;
        //选择第一个数为基准
        int temp = arr[i];

        while (i < j) {

            //从后向前查找小于arr[i]的数来填a[i]
            while (i < j && arr[j] >= temp) {
                j--;
            }

            if (i < j) {
                arr[i] = arr[j];
                i++;
            }

            //从后向前查找大于arr[i]的数来填a[j]
            while (i < j && arr[i] <= temp) {
                i++;
            }
            if (i < j) {
                arr[j] = arr[i];
                j--;
            }
        }

        arr[i] = temp;

        quickSort(arr, l, i - 1);
        quickSort(arr, i + 1, r);

    }


    /**********************插入排序**********************


     /**
     * 插入排序  从小到大
     * <p>
     * 平均时间复杂度：O(n²)  	空间复杂度：O(1)
     *
     * @param arr
     */
    public static void insertionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            //一次取出一个元素和前面的进行比较
            int temp = arr[i];
            for (int j = i; j >= 0; j--) {
                //j > 0 表示当为第一个元素时直接插入
                if (j > 0 && arr[j - 1] > temp) {
                    // 如果该元素大于取出的元素temp，将该元素后移一位
                    arr[j] = arr[j - 1];
                } else {
                    //将去除的元素插入到合适的位置
                    arr[j] = temp;
                    break;
                }
            }
        }

    }

    /**
     * 插入排序  交换次数较多的实现
     */
    public static void insertionSort02(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i; j >= 0; j--) {
                //和后一个元素进行比较
                if (arr[j + 1] >= arr[j])
                    break;

                int temp = arr[j + 1];
                arr[j + 1] = arr[j];
                arr[j] = temp;
            }
        }

    }

    /**
     * 希尔排序
     *
     * @param arr
     */
    public static void shellSort(int[] arr) {

        //第一次排序的步长，表示分成多少各组，然后依次对每个组进行插入排序
        //第二次步长 = 第一次步长 / 2 ； 以此类推
        int gap = arr.length / 2;

        //计算每一次的步长
        for (; gap > 0; gap /= 2) {
            //遍历所有的分组
            for (int i = 0; i < gap; i++) {
                //根据步长遍历每个分组的元素,使用插入排序算法
                for (int j = i + gap; j < arr.length; j += gap) {

                    if (arr[j - gap] > arr[j]) {

                        int temp = arr[j];
                        int k = j - gap;
                        while (k >= 0 && arr[k] > temp) {
                            arr[k + gap] = arr[k];
                            k -= gap;
                        }
                        arr[k + gap] = temp;
                    }
                }
            }
        }
    }


    /************************选择排序***************


     /**
     * 选择排序
     *
     * @param arr
     */
    public static void selectionSort(int[] arr) {
        //i < arr.length - 1 最后一位不用重复遍历
        for (int i = 0; i < arr.length - 1; i++) {

            int min = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }

            if (min != i) {
                int temp = arr[min];
                arr[min] = arr[i];
                arr[i] = temp;
            }
        }
    }


    /***************************线性时间比较类排序************************/

    //region 计数排序
    /**
     * 计数排序
     */
    public static void countingSort(int[] arr) {

        if (arr.length <= 1) return;

        //取得最大值
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }

        //申请一个桶空间，存放0...max的所有值
        int bucketLen = max + 1;
        int[] bucket = new int[bucketLen];

        //遍历数组，记录每个元素出现的次数
        for (int value : arr) {
            bucket[value]++;
        }

        //遍历桶，依次取出所有元素
        int sortedIndex = 0;
        for (int j = 0; j < bucketLen; j++) {
            while (bucket[j] > 0) {
                arr[sortedIndex++] = j;
                bucket[j]--;
            }
        }
    }
    //endregion

    //region 桶排序
    /**
     * 桶排序
     */
    public static void bucketSort(int[] arr) {

        if (arr.length <= 1) return;

        //桶的深度，当桶的深度为1时，相当于计数排序
        int bucketSize = 5;

        int min = 0, max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];
            }
            if (max < arr[i]) {
                max = arr[i];
            }
        }

        //计算出桶的数量
        int bucketCount = (int) (Math.floor((max - min) / bucketSize) + 1);
        int[][] buckets = new int[bucketCount][0];

        // 利用映射函数将数据分配到各个桶中
        for (int j = 0; j < arr.length; j++) {
            int index = (int) Math.floor(arr[j] / bucketSize);
            buckets[index] = append(buckets[index], arr[j]);
        }

        //遍历所有的桶，对每个桶的数据进行排序
        int sortIndex = 0;
        for (int[] bucket : buckets) {
            if (bucket.length < 1)
                continue;

            insertionSort02(bucket);

            //排序之后取出每个桶的元素
            for (int value : bucket) {
                arr[sortIndex++] = value;
            }
        }
    }
    //endregion


    //region 基数排序
    /**
     * 基数排序适用于：
     * (1)数据范围较小，建议在小于1000
     * (2)每个数值都要大于等于0
     * <p>
     * ①. 取得数组中的最大数，并取得位数；
     * ②. arr为原始数组，从最低位开始取每个位组成radix数组；
     * ③. 对radix进行计数排序（利用计数排序适用于小范围数的特点）；
     *
     * @param arr
     */
    public static void radixSort(int[] arr) {

        if (arr.length <= 1) return;

        //取得数组中的最大数，并取得位数
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }

        //取得最大数的位数
        int maxDigit = 1;
        while (max / 10 > 0) {
            maxDigit++;
            max = max / 10;
        }
        System.out.println("maxDigit: " + maxDigit);

        //申请一个桶空间
        int[][] buckets = new int[10][arr.length];
        int base = 10;

        //从低位到高位，对每一位遍历，将所有元素分配到桶中
        for (int i = 0; i < maxDigit; i++) {
            //存储各个桶中存储元素的数量
            int[] bktLen = new int[10];

            //分配：将所有元素分配到桶中
            for (int j = 0; j < arr.length; j++) {
                //取得所在桶的位置，和HashMap计算index有点类似
                int whichBucket = (arr[j] % base) / (base / 10);
                buckets[whichBucket][bktLen[whichBucket]] = arr[j];
                bktLen[whichBucket]++;
            }

            //收集：将不同桶里数据挨个捞出来,为下一轮高位排序做准备,由于靠近桶底的元素排名靠前,因此从桶底先捞
            int k = 0;
            for (int p = 0; p < buckets.length; p++) {
                for (int q = 0; q < bktLen[p]; q++) {
                    arr[k++] = buckets[p][q];
                }
            }

            System.out.println("Sorting: " + Arrays.toString(arr));
            //对下一位进行遍历
            base *= 10;
        }

    }
    //endregion

    private static int[] append(int[] arr, int value) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = value;
        return arr;
    }

}
