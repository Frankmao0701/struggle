package com.frank.struggle;

import android.text.format.Time;

import org.junit.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private static int count = 0;
    private AtomicInteger atomicCount = new AtomicInteger(0);
    @Test
    public void addition_isCorrect() {
        atomicTest();
//        assertEquals(4, 2 + 2);
    }

    /**
     * CAS操作处理线程安全问题。
     */
    public void atomicTest() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
//                    synchronized (ExampleUnitTest.class){
                        for (int j = 0; j < 1000; j++) {
//                            System.out.println("thread ==" + Thread.currentThread().getName());
//                            count++;
                            atomicCount.getAndIncrement();
                        }
//                    }

                }
            });
        }
        executorService.shutdown();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("count ==" + atomicCount);
    }
}