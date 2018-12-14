package com.eugene.algorithm;

import java.util.Arrays;

/**
 * 经典查找算法
 */
public class Search {


    //region 循序查找算法

    /**
     * 顺序查找适合于存储结构为顺序存储或链接存储的线性表。
     * <p>
     * 时间复杂度为O(n)
     *
     * @return 返回index -1表示没有
     */
    public static int seqSearch(int[] arr, int key) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key)
                return i;
        }
        return -1;

    }
    //endregion

    //region 二分查找

    /**
     * 元素必须是有序的，如果是无序的则要先进行排序操作。
     * <p>
     * 最坏情况下，关键词比较次数为log2(n+1)，且期望时间复杂度为O(log2n)
     * <p>
     * 折半查找的前提条件是需要有序表顺序存储，对于静态查找表，一次排序后不再变化，折半查找能得到不错的效率。
     * 但对于需要频繁执行插入或删除操作的数据集来说，维护有序的排序会带来不小的工作量，那就不建议使用
     */
    public static int binarySearch(int[] arr, int key) {

        int low = 0;
        int high = arr.length - 1;
        int mid;

        while (low < high) {

            mid = (high - low) / 2;
            if (arr[mid] < key) {
                high = mid - 1;
            } else if (arr[mid] > key) {
                low = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    /**
     * 二分查找算法递归实现
     */
    public static int binarySearch(int[] arr, int key, int low, int high) {

        int mid = (high - low) / 2;

        if (arr[mid] == key)
            return mid;
        else if (arr[mid] < key)
            return binarySearch(arr, key, low, mid - 1);
        else if (arr[mid] > key)
            return binarySearch(arr, key, mid + 1, high);

        return -1;
    }

    //endregion


    //region 差值查找

    /**
     * 基本思想：基于二分查找算法，将查找点的选择改进为自适应选择，可以提高查找效率。当然，差值查找也属于有序查找。
     * <p>
     * 对于表长较大，而关键字分布又比较均匀的查找表来说，插值查找算法的平均性能比折半查找要好的多。
     * 反之，数组中如果分布非常不均匀，那么插值查找未必是很合适的选择。
     * <p>
     * 复杂度分析：查找成功或者失败的时间复杂度均为O(log2(log2n))。
     */
    public static int dSearch(int[] arr, int key) {

        int low = 0;
        int high = arr.length - 1;
        int mid;

        while (low < high) {

            mid = low + (key - arr[low]) / (arr[high] - arr[low]) * (high - low);

            if (mid < key)
                high = mid - 1;
            if (mid > key)
                low = mid + 1;
            else
                return mid;

        }

        return -1;
    }

    /**
     * 差值查找递归实现
     */
    public static int dSearch(int[] arr, int key, int low, int high) {

        int mid = low + (key - arr[low] / arr[high] - arr[low]) * (high - low);

        if (arr[mid] < key)
            return dSearch(arr, key, low, mid - 1);
        if (arr[mid] > key)
            return dSearch(arr, key, mid + 1, high);
        else
            return mid;
    }
    //endregion


    //region 斐波那契查找

    /**
     * 斐波那契查找
     * <p>
     * 复杂度分析：最坏情况下，时间复杂度为O(log2n)，且其期望复杂度也为O(log2n)。
     * <p>
     * 近似的使用黄金分割
     */
    public static int FibonacciSearch(int[] arr, int key) {
        int fSize = 20;
        int[] F = new int[20];
        F[0] = 0;
        F[1] = 1;

        //构建裴波那切数列
        for (int i = 2; i < fSize; i++) {
            F[i] = F[i - 1] + F[i - 2];
        }

        //计算n位于斐波那契数列的位置
        int n = arr.length;
        int k = 0;
        while (n > F[k]) {
            k++;
        }

        //补充查询数组的长度，使arr的长度满足n = F[k] - 1
        int[] temp = Arrays.copyOf(arr, F[k] - 1);
        for (int j = n; j < F[k] - 1; j++) {
            temp[j] = arr[n - 1];
        }

        int low = 0;
        int high = arr.length - 1;
        int mid;

        while (low < high) {

            mid = low + F[k - 1] - 1;

            if (temp[mid] > key) {
                low = mid + 1;
                //k-=2 说明范围[mid+1,high]内的元素个数为n-F(k-1)= F(k)-1-F(k-1)=Fk-F(k-1)-1=F(k-2)-1个，
                // 所以可以递归的应用斐波那契查找。
                k -= 2;
            } else if (temp[mid] < key) {
                high = mid - 1;
                //k-=1 说明范围[low,mid-1]内的元素个数为F(k-1)-1个，所以可以递归 的应用斐波那契查找。
                k -= 1;
            } else {
                if (mid < n)
                    //若相等则说明mid即为查找到的位置
                    return mid;
                else
                    //若mid>=n则说明是扩展的数值,返回n-1
                    return n - 1;
            }

        }

        return -1;
    }
    //endregion

    //region 二叉查找树算法
    /**
     * 二叉查找树算法 #BinarySearchTree
     */
    //endregion


}
