package class03_MergeSort_QuickSort;

/**
 * @author mingyanwang
 */
public class Code02_SmallSum {

    // 1 3 4 2 5
    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
//        return process(arr, 0, arr.length - 1);
        return processNonRecur(arr);
    }


    public static int process(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int mid = L + ((R -L) >> 1);
        return process(arr, L, mid) +
                process(arr, mid + 1, R) +
                merge(arr, L, mid, R);
    }

    /**
     * My improved version using non-recursion approach for small sum problem
     * @param arr array input
     * @return small sum
     */
    public static int processNonRecur(int[] arr) {
        int N = arr.length;
        int mergeSize = 1;
        int sum = 0;
        while (mergeSize < N) {
            int L = 0;
            while (L < N) {
                int mid = L + mergeSize - 1;
                if (mid >= N) {
                    break; // improved1 should not return
                }
                int R = Math.min(mid + mergeSize, N - 1);
                sum += merge(arr, L, mid, R);
                L = R + 1;
            }
            if (mergeSize > (N >> 1)) {
                break;
            }
            mergeSize <<= 1;
        }
        return sum;
    }

    public static int merge(int[] arr, int L, int M, int R) {
        int[] helper = new int[R - L + 1];
        int p1 = L;
        int p2 = M + 1;
        int i = 0;
        int sum = 0;
        while (p1 <= M && p2 <= R) {
            sum += arr[p1] < arr[p2] ? arr[p1] * (R - p2 + 1) : 0;
            helper[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            helper[i++] = arr[p1++];
        }
        while (p2 <= R) {
            helper[i++] = arr[p2++];
        }
        for (int j = 0; j < helper.length; j++) {
            arr[j + L] = helper[j];
        }
        return sum;
    }

    // for test
    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (smallSum(arr1) != comparator(arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
