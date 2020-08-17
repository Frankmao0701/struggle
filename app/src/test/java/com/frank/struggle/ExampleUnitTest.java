package com.frank.struggle;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

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
//        atomicTest();
//        jsonTest();
        eyeTracking();
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

    private void jsonTest() {
        String content = "拿上推车和菜";
        String json = "{\"content\":\"" + content + "\"}";
        System.out.println("json == " + json);
    }

    private void eyeTracking() {
        int[] array = new int[5];
        for (int i = 0; i < 10; i++) {
            int pos = i % 5;
            System.out.println(" i % 5 == " + pos);
            if (pos == 0) {
                array[pos] = 0;
            } else if (pos <= 2) {
                array[pos] = 1;
            } else if (pos <= 3) {
                array[pos] = 2;
            } else {
                array[pos] = 3;
            }
        }
        System.out.println("array == " + Arrays.toString(array));

        int index = 0;
        while (index < array.length) {
            //因为数组都是从0开始的，所以arr[index]得减1才可以找到对应的元素，否则会数组越界
            int temp = array[index] - 1;
            if (temp < 0) {
                index++;
                continue;
            }
            if (array[temp] > 0) {
                array[index] = array[temp];
                array[temp] = -1;
            } else {
                array[temp]--;
                array[index] = 0;
            }
        }


        int start = 1;
        for (int countResult : array
        ) {
            System.out.println(start++ + "出现了" + (-1) * countResult + "次");
        }

    }
}