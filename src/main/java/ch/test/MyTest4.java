package ch.test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Description: 集合的测试
 *
 * @author cy
 * @date 2019年05月08日 11:47
 * version 1.0
 */
public class MyTest4 {

    public static void main(String[] args) {
        Object o = new Object();
        try {
            o.wait();
            o.notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
