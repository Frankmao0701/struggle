package com.frank.struggle;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
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
//        eyeTracking();
        getCountFromTable();
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
        HashMap<Integer, Integer> countMap = new HashMap<>();
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

        for (int i = 0; i < array.length; i++) {
            if (countMap.get(array[i]) == null) {
                countMap.put(array[i], 1);
            } else {
                countMap.put(array[i], countMap.get(array[i]) + 1);
            }
        }

        System.out.println("map == " + countMap.toString());


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

    /**
     * 求数组中元素出现的最大次数 空间换时间
     */
    private void getCountFromTable() {
        int[] arr = new int[]{0, 3, 0, 3, 2, 0, 1, 0, 2, 3};
        int n = arr.length;
        int[] count = new int[4]; // count数组的大小理论上是第一个数组值的最大值+1
        int res = 0;
        int max_count = 0;
        for (int i = 0; i < n; i++) {
            // 获取第一个数组的值，在第二个数组值自增 第二个数组index为第一个数组的值，第二个数组的值为出现的最大次数。空间换时间
            count[arr[i]]++;
        }
        for (int i = 0; i < count.length; i++) {
            if (count[i] >= max_count) { // 如果这个地方是大于，若出现了两个数字出现次数相同的情况则取前面的值，若改为>=则取后面的值
                max_count = count[i];
                res = i;
            }
        }
        System.out.println("max count == " + res);
    }
}