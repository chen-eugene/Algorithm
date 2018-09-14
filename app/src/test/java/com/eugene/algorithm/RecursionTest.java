package com.eugene.algorithm;

import org.junit.Test;

public class RecursionTest {

    @Test
    public void testRecursion() {

        int count = new Recursion().hanoi(3);
        System.out.println(count);

        long result = new Recursion().fibonacci(5);
        System.out.println(result);

    }
}
