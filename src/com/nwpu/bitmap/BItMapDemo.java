package com.nwpu.bitmap;

import java.util.Arrays;
import java.util.Random;

/**
 * 作业： 随机产生1~100的数字存入数组
 * @author nwpu
 */
public class BItMapDemo {

    static int[] a = new int[100];
    static int[] b = new int[100];
    static int size = 0;//a数组中的数据量
    static int sizeB = 0; //b数组中数据量
    static Random ran = new Random();
    static long targetFirst = 0;//映射数组是否有值
    static long targetSecond = 0;

    public static void main(String[] args) {
        /*
         * 开启一个线程
         */
        new Thread(()->{
            loopTest();
        }).start();
        /*
         * 开启一个线程
         */
        new Thread(()->{
            bitTest();
        }).start();

    }

    public static void loopTest() {
        long s1 = System.nanoTime();
        while (size <a.length) {
            int r = ran.nextInt(100) + 1;
            if (!hasNum(r)) {
                a[size++] = r;
            }
        }
        long s2 = System.nanoTime();
        System.out.println("循环： " + (s2-s1)+"ns");
        System.out.println(Arrays.toString(a));
    }


    public static void bitTest() {
        long s1 = System.nanoTime();
        while (sizeB <b.length) {
            int r = ran.nextInt(100) + 1;
            if (!hasNumS(r)) {
                b[sizeB++] = r;
            }
        }
        long s2 = System.nanoTime();
        System.out.println("bitmap： " + (s2-s1)+"ns");
        System.out.println(Arrays.toString(b));
    }

    /**
     * 题目中要求生成1-100的数，那么就需要两个long型共16个字节的地方来存储。即 xFirst 和 xSecond
     * @param r 判断r是否已经在bitmap中
     * @return {@link boolean}
     * @author Yushun Xiang
     */
    public static boolean hasNumS(int r) {//r = 4
        long xFirst = 1L;
        long xSecond = 0L;
        if(r <= 63) { // 当 r <= 63 时，用 xFirst 来记录 0 - 63 的数字的有无

            xFirst = xFirst<<r;
        } else { // 当 r >= 64 时，用 xSecond 来记录 64 - 100 的数字的有无

            xFirst = 0L;
            xSecond = 1L;
            xSecond = xSecond << (r-64);
        }
        if((xFirst & targetFirst)==0 && (xSecond & targetSecond)==0) {//如果当前位置是1，位与后结果为0
            targetFirst = targetFirst | xFirst;
            targetSecond = targetSecond | xSecond;
            return false;
        }else {
            return true;
        }
    }


    public static boolean hasNum(int r) {
        for (int i = 0; i < size; i++) {
            if (r == a[i]) {
                return true;
            }
        }
        return false;
    }

}
