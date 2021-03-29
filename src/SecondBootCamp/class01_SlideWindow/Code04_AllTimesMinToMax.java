package SecondBootCamp.class01_SlideWindow;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author mingyan wang
 * @date 2021/3/24 4:53 PM
 */
public class Code04_AllTimesMinToMax {

    /**
     * 暴力方法
     * @param arr
     * @return
     */
    public static int max1(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int minNum = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                    minNum = Math.min(minNum, arr[k]);
                }
                max = Math.max(max, minNum * sum);
            }
        }
        return max;
    }

    public static int max2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int[] prefixSum = new int[arr.length];
        prefixSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            prefixSum[i] += (prefixSum[i - 1] + arr[i]);
        }
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                Integer popIndex = stack.pop();
                int curResult = arr[popIndex] *
                        (stack.isEmpty() ? prefixSum[i - 1] :
                        (prefixSum[i - 1] - prefixSum[stack.peek()]));
                max = Math.max(max, curResult);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            int curResult = arr[pop] *
                    (stack.isEmpty() ? prefixSum[arr.length - 1] : prefixSum[arr.length - 1] - prefixSum[stack.peek()]);
            max = Math.max(max, curResult);
        }
        return max;
    }

    public static int[] gerenareRondomArray() {
        int[] arr = new int[(int) (Math.random() * 20) + 10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 101);
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTimes = 2000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = gerenareRondomArray();
            if (max1(arr) != max2(arr)) {
                System.out.println("FUCK!");
                break;
            }
        }
        System.out.println("test finish");
    }
}
