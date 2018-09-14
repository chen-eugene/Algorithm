package com.eugene.algorithm;

public class Sort {

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


    public static void selectSort(int arr[]){
        if (arr == null || arr.length <= 0)
            return;




    }


}
