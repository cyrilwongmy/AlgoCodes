package class03;

/**
 * @author mingyanwang
 * @date
 */
public class Code01_MergeSort {
    // recursion way
    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) return;
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int L, int R) {
        if (L == R) return;

        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    public static void merge(int[] arr, int L, int M, int R) {
        int[] helper = new int[R - L + 1];
        int p1 = L;
        int p2 = M + 1;
        int pH = 0;
        while (p1 <= M && p2 <= R) {
            helper[pH++] = arr[p2] >= arr[p1] ? arr[p2++] : arr[p1++];
        }
        while (p1 <= M) {
            helper[pH++] = arr[p1++];
        }
        while (p2 <= R) {
            helper[pH++] = arr[p2++];
        }
        for (int i = 0; i < helper.length; i++) {
            arr[L + i] = helper[i];
        }
    }

    // iterative approach
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int N = arr.length;
        int mergeSize = 1;
        while (mergeSize < N) {
            int L = 0; // improve1: every time L shall start from zero
            while (L < N) {
                int M = L + mergeSize - 1;
                if (M >= N) break; // improve2:
                int R = Math.min(M + mergeSize, N - 1); // improve3:
                merge(arr, L, M, R);
                L = R + 1; // improve4:
            }
            if (mergeSize > (N >> 1)) {
                break;
            }
            mergeSize <<= 1;
        }
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
            mergeSort1(arr1);
            mergeSort2(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");
    }
}
