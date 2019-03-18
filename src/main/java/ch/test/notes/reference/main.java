package ch.test.notes.reference;

import ch.test.notes.copy.Student;
import sun.security.util.BitArray;

import java.lang.ref.*;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: java 中引用的使用
 *
 * @author cy
 * @date 2019年03月08日 17:11
 * version 1.0
 */
public class main {

    static Map<String,WeakReference<String>> map = new HashMap<String,WeakReference<String>>();


    public static void main(String[] args) throws InterruptedException {
        // java 中把对象生成四种引用，先测试一种弱用软引用
        // 如果一个对象具有软引用，如果内存空间足够，垃圾回收机制不会进行回收，如果不够就会进行回收
        // 软引用可以和一个引用队列referenceQueue联合进行使用

        // 在java.lang.ref包中提供了几个类：SoftReference类、WeakReference类和PhantomReference类，
        // 它们分别代表软引用、弱引用和虚引用。ReferenceQueue类表示引用队列，它可以和这三种引用类联合使用，以便跟踪Java虚拟机回收所引用的对象的活动。

        // 虚引用和前面的软引用、弱引用不同，它并不影响对象的生命周期。在java中用java.lang.ref.PhantomReference类表示。如果一个对象与虚引用关联，则跟没有引用与之关联一样，在任何时候都可能被垃圾回收器回收。

        // 如果将hello 定义出去就就不能进行回收了
        WeakReference<String> sr = new WeakReference<String>(new String("hello"));
        map.put("sr",sr);
        System.out.println((String)map.get("sr").get());
        System.gc();
        System.out.println((String)map.get("sr").get());

        ReferenceQueue<String> queue = new ReferenceQueue<String>();
        PhantomReference<String> pr = new PhantomReference<String>(new String("hello1"), queue);
        System.out.println(pr.get());

        // 添加队列的使用
        System.out.println("==============================");
        ReferenceQueue<Student> queue1 = new ReferenceQueue<Student>();
        WeakReference<Student> studentSoftReference = new WeakReference<Student>(new Student(),queue1);


        Thread thread = new Thread(() -> {
            try {
                int cnt = 0;
                WeakReference<byte[]> k;
                while((k = (WeakReference) queue1.remove()) != null) {
                    System.out.println((cnt++) + "回收了:" + k);
                }
            } catch(InterruptedException e) {
                //结束循环
            }
        });
        thread.setDaemon(true);
        thread.start();


    }

}
