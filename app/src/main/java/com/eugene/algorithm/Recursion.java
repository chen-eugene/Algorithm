package com.eugene.algorithm;

/**
 * 递归算法
 * 不要陷入程序递归的内部去思考递归算法，要从递归思维的本质(复杂问题简单化)出发去理解递归算法，
 * 千万不要去通过试图解析程序执行的每一个步骤来理解递归（解析程序的执行是指给函数一个真实值，
 * 然后自己一步步去推出结果，这样的思考方式是错误的！），那样只会让自己得到伪理解(没有真正理解)的结果。
 * <p>
 * 归必须满足以下两个条件:
 * ①边界条件：至少有一条初始定义是非递归的，如汉诺塔的H(0)=0，阶乘的0!=1。
 * ②递归通式：由已知函数值逐步计算出未知函数值，如汉诺塔的H(0)=0，可以推算出H(1)=H(0)+1+H(0)。
 */
public class Recursion {

    /**
     * 计算汉诺塔移动的次数
     * <p>
     * F(n) = {0  n = 0 ; F(n-1) + 1 + F(n-1)  n >= 1}
     *
     * @param n 汉诺塔的层数x,x,y,z表示的是汉诺塔的柱子，这里不需要使用
     */
    public int hanoi(int n) {
        int movCount = 0;
        if (n == 0) {
            return 0;
        } else {
            //计算将n-1层移动到中转柱子的次数
            movCount += hanoi(n - 1);

            //将最后一层移动到目标柱子
            movCount += 1;

            //将n-1层移动到目标柱子的次数
            movCount += hanoi(n - 1);

        }
        return movCount;
    }

    /**
     * 斐波那契数列
     * <p>
     * F(n) = { 0  n = 0 ; 1  n = 1 ; F(n -1)+F(n-2)  n >= 2 }
     *
     * @return
     */
    public long fibonacci(int n) {

        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }

    }
}
