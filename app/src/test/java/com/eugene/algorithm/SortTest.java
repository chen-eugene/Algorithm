package com.eugene.algorithm;

import org.junit.Test;

public class SortTest {

    private int[] arr = {4, 5, 6, 1, 8, 2, 3, 7};

    @Test
    public void bubbleSortTest() {


//        Sort.bubbleSortFromTail(arr);

        Sort.bubbleSortFromHead(arr);

        print(arr);

    }

    @Test
    public void insertionSortTest() {

        Sort.insertionSort(arr);

//        Sort.insertionSort02(arr);

        print(arr);
    }

    @Test
    public void shellSortTest() {

        Sort.shellSort(arr);

        print(arr);
    }

    @Test
    public void selectionSortTest() {

        Sort.selectionSort(arr);

        print(arr);
    }

    @Test
    public void quickSortTest() {

        Sort.quickSort(arr, 0, arr.length - 1);

        print(arr);
    }

    @Test
    public void radixSortTest() {

        Sort.radixSort(arr);

        print(arr);
    }


    private void print(int[] arr) {

        for (int i : arr) {
            System.out.print(i + "");
        }

    }


}
