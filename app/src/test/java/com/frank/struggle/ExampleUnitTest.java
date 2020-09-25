package com.frank.struggle;

import com.frank.struggle.designmode.observer.ConcreteObsevale;
import com.frank.struggle.designmode.observer.ConcreteObsever;
import com.frank.struggle.designmode.proxy.DynamicProxy;
import com.frank.struggle.designmode.proxy.ILawsuit;
import com.frank.struggle.designmode.proxy.XiaoMao;

import org.junit.Test;

import java.io.FileInputStream;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
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
//        getCountFromTable();
//        testArraryList();
//        getCountFromQueue();


//        Node head = initLinkedList(10);
//
//        printLinkedList(head);
//
//        System.out.println("**************");
//
//        //反转链表
//        Node node = reveseNode(head);
//        printLinkedList(node);


//        buddleSort();
//
//        int[] arr = new int[]{5, 2, 3, 6, 1, 4, 8, 7, 9, 11, 10, 15, 12, 13, 14};
//        for (int value : arr) {
//            System.out.println(value + "");
//        }
//        System.out.println("------------------------");
//        quickSort(arr, 0, arr.length - 1);
//        for (int value : arr) {
//            System.out.println(value + "");
//        }

        ConcreteObsever obsever1 = new ConcreteObsever();
        ConcreteObsever obsever2 = new ConcreteObsever();
        ConcreteObsevale obsevalle = new ConcreteObsevale();
        obsevalle.addObserver(obsever1);
        obsevalle.addObserver(obsever2);
        obsevalle.postNewPublication("发送新的消息");


        ILawsuit xiaomao = new XiaoMao();
        // 构造一个动态代理
        DynamicProxy proxy = new DynamicProxy(xiaomao);
        // 获取被代理类的ClassLoader
        ClassLoader loader = xiaomao.getClass().getClassLoader();
        // 动态构造一个代理者
        ILawsuit lawyer = (ILawsuit) Proxy.newProxyInstance(loader, new Class[]{ILawsuit.class}, proxy);

        lawyer.submit();
        lawyer.burden();
        lawyer.defend();
        lawyer.finish();
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


    private void getCountFromQueue() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        queue.offer(2);
        queue.offer(5);
        queue.offer(3);
        queue.offer(5);
        queue.offer(3);
        queue.offer(5);
        queue.offer(2);
        queue.offer(1);
        queue.offer(2);
        queue.offer(1);
//        int[] arr = new int[]{0, 3, 0, 3, 2, 0, 1, 0, 2, 3};
        int n = queue.size();
        int[] count = new int[6]; // count数组的大小理论上是第一个数组值的最大值+1
        int res = 0;
        int max_count = 0;
        for (int i = 0; i < n; i++) {
            // 获取第一个数组的值，在第二个数组值自增 第二个数组index为第一个数组的值，第二个数组的值为出现的最大次数。空间换时间
            count[queue.poll()]++;
        }
        for (int i = 0; i < count.length; i++) {
            if (count[i] >= max_count) { // 如果这个地方是大于，若出现了两个数字出现次数相同的情况则取前面的值，若改为>=则取后面的值
                max_count = count[i];
                res = i;
            }
        }
        System.out.println("max count == " + res);
    }


    private void testArraryList() {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            if (queue.size() >= 15) {
                queue.poll();
            }
            queue.offer(i);
        }
        System.out.println("list == " + queue.toString());
    }

    public static class Node<T> {
        private T value;    //节点值
        private Node<T> next;   //后继节点

        public Node() {
        }

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node reveseNode(Node head) {
//        Node prev = null;
//        Node now = node;
//        while (now != null) {
//            Node next = now.next;
//            now.next = prev;
//            prev = now;
//            now = next;
//        }
//        return prev;

        Node prev = null;
        Node next = null;
        while (head.next != null) {
            next = head.next;   //保存下一个节点
            head.next = prev;   //重置next
            prev = head;    //保存当前节点
            head = next;
        }
        head.next = prev;
        return head;
    }

    /**
     * 初始化链表
     **/
    private Node initLinkedList(int num) {
        Node head = new Node(0, null);
        Node cur = head;
        for (int i = 1; i < num; i++) {
            cur.next = new Node(i, null);
            cur = cur.next;
        }
        return head;
    }

    /**
     * 打印链表
     **/
    private void printLinkedList(Node head) {
        Node node = head;
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }
    }

    public void buddleSort() {
        int[] arr = new int[]{5, 2, 3, 6, 1, 4};
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        for (int value : arr) {
            System.out.println("arr == " + value);
        }

    }

    public void quickSort(int[] arr, int low, int high) {
        int start = low;
        int end = high;
        int key = arr[low];
        while (end > start) {
            while (end > start && arr[end] >= key) {
                end--;
            }
            if (arr[end] < key) {
                int temp = arr[end];
                arr[end] = arr[start];
                arr[start] = temp;
            }
            while (end > start && arr[start] <= key) {
                start++;
            }
            if (arr[start] >= key) {
                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
            }
        }
        if (start > low) {
            quickSort(arr, low, start - 1);
        }
        if (end < high) {
            quickSort(arr, end + 1, high);
        }
    }

    public Node Reverse(Node head) {
        Node prev = null;
        Node next = null;
        while (head.next != null) {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        head.next = prev;
        return head;
    }

    public void sort(int[] arr, int low, int high) {
        int start = low;
        int end = high;
        int key = arr[low];
        while (end > start) {
            while (end > start && arr[end] >= key) {
                end--;
            }
            if (arr[end] < key) {
                int temp = arr[end];
                arr[end] = arr[start];
                arr[start] = temp;
            }
            while (end > start && arr[start] <= key) {
                start++;
            }
            if (arr[start] >= key) {
                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
            }
        }
        if (start > low) {
            sort(arr, low, start - 1);
        }
        if (end < high) {
            sort(arr, end + 1, high);
        }
    }
}
