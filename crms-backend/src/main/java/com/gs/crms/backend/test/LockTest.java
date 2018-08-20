package com.gs.crms.backend.test;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by PEAR on 2018/3/25.
 */
public class LockTest {
    public static void main(String[] args) {

        new LockTest().init();
    }

    private void init() {
        final Outputer outputer = new Outputer();
        // 线程1打印：duoxiancheng
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(20000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    //System.out.print(new Date().toString()+"duoxiancheng");
                    outputer.output("duoxiancheng");
                }

            }
        }).start();
        ;

        // 线程2打印：eson15
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(20000);
                       // System.out.print(new Date().toString());
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    outputer.output("eson15");
                }

            }
        }).start();
        ;
    }

    // 自定义一个类，保存锁和待执行的任务
    static class Outputer {
        Lock lock = new ReentrantLock(); //定义一个锁，Lock是个接口，需实例化一个具体的Lock
        //字符串打印方法，一个个字符的打印
        public void output(String name) {

            int len = name.length();
            lock.lock();
            try {
//                for (int i = 0; i < len; i++) {
//                    System.out.println(new Date().toString()+name.charAt(i));
//                }
                System.out.println(new Date().toString()+name);
                System.out.println("");
            } finally {
                lock.unlock(); //try起来的原因是万一一个线程进去了然后挂了或者抛异常了，那么这个锁根本没有释放
            }
        }
    }
}
