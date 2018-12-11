package com.eugene.algorithm;

/**
 * 经典查找算法
 */
public class Search {


    //region 循序查找算法

    /**
     * 顺序查找适合于存储结构为顺序存储或链接存储的线性表。
     * <p>
     * 时间复杂度为O(n)
     * @return 返回index -1表示没有
     */
    public int seqSearch(int[] arr, int key) {

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
     */
    public int binarySearch(int[] arr, int key) {

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
    public int binarySearch(int[] arr, int key, int low, int high) {

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


}
