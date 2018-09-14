package com.eugene.algorithm;

import org.junit.Test;

public class SortTest {

    @Test
    public void sort() {

        int[] arr = {2, 5, 6, 1, 8, 2, 3, 7};

//        Sort.bubbleSortFromTail(arr);

        Sort.bubbleSortFromHead(arr);


        print(arr);

    }

    private void print(int[] arr) {

        for (int i : arr) {
            System.out.print(i + "");
        }

    }


}
