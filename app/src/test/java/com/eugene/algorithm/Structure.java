package com.eugene.algorithm;

import com.eugene.datastructure.ArrayQueue;
import com.eugene.datastructure.LinkedQueue;

import org.junit.Test;

public class Structure {


    @Test
    public void testQueue() {

        ArrayQueue<String> arrayQueue = new ArrayQueue<>();
        arrayQueue.push("1");
        arrayQueue.push("2");
        arrayQueue.push("3");
        int size = arrayQueue.getSize();
        for (int i = 0; i < size; i++) {
            System.out.println(arrayQueue.poll());
        }

        LinkedQueue<String> linkedQueue = new LinkedQueue<>();
        linkedQueue.push("1");
        linkedQueue.push("2");
        linkedQueue.push("3");
        int len = linkedQueue.getSize();
        for (int i = 0; i < len; i++) {
            System.out.println(linkedQueue.poll());
        }

    }


}
