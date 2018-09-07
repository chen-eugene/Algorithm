package com.eugene.algorithm;

public class Array {

    private String[] values = new String[10];
    /**
     * 当前元素个数
     */
    private int size;

    public void growCapacity() {

        String[] temp = new String[values.length * 2];
        for (int i = 0; i < values.length; i++) {
            temp[i] = values[i];
        }
        values = temp;

    }


    public void add(int index, String e) {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (index >= values.length) {
            growCapacity();
        }

        //从index位置开始整体后移一位
        for (int i = size - 1; i >= index; i++) {
            values[i + 1] = values[i];
        }

        values[index] = e;

        size++;
    }


}
