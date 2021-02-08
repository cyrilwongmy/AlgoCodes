package class03;

import java.util.Arrays;

/**
 * @author mingyan wang
 * @date 2021/1/14 3:46 PM
 */
public class PartitionAndQuickSort {
    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static int partition(int[] arr, int L, int R) { // 1, 3, 4, 2 ->
        if (L > R) {
            return -1;
        }
        if (L == R) {
            return L;
        }
        int lessEqualIndex = L - 1; // WRONG using -1, should use `L - 1`
        for (int index = L; index < R; index++) {
            if (arr[index] <= arr[R]) {
                swap(arr, ++lessEqualIndex, index);
            }
        }
        swap(arr, R, ++lessEqualIndex);
        return lessEqualIndex;
    }


    // arr[L...R] 玩荷兰国旗问题的划分，以arr[R]做划分值
    //  <arr[R]  ==arr[R]  > arr[R]
    public static int[] netherlandsFlag(int[] arr, int L, int R) { // 4, 2, 2, 1, 2
        if (L > R) {
            return new int[] {-1, -1};
        }
        if (L == R) {
            return new int[] {L, R};
        }

        int lessIndex = L - 1;
        int greaterIndex = R;
        int index = L;
        while (index < greaterIndex) {
            if (arr[index] == arr[R]) {
                index++;
            } else if (arr[index] < arr[R]) {
                swap(arr, ++lessIndex, index++);
            } else {
                swap(arr, --greaterIndex, index); //  1, 2, 2, 4, 2
            }
        }
        swap(arr, greaterIndex, R);
        return new int[] {lessIndex + 1, greaterIndex};
    }

    // official version
    public static int[] netherlandsFlag2(int[] arr, int L, int R) {
        if (L > R) {
            return new int[] { -1, -1 };
        }
        if (L == R) {
            return new int[] { L, R };
        }
        int less = L - 1; // < 区 右边界
        int more = R;     // > 区 左边界
        int index = L;
        while (index < more) {
            if (arr[index] == arr[R]) {
                index++;
            } else if (arr[index] < arr[R]) {
                swap(arr, index++, ++less);
            } else { // >
                swap(arr, index, --more);
            }
        }
        swap(arr, more, R);
        return new int[] { less + 1, more };
    }

    public static void quickSort1(int[] arr) {
         process1(arr, 0, arr.length - 1);
    }

    /**
     * 一次只排好一个数，每次按照arr[R]，按照arr[R]数组左右分成两组，最后把
     * arr[R]放回属于它的位置。
     * @param arr arr to be partitioned
     * @param L the left index of partition
     * @param R the rihgt index of partition
     */
    public static void process1(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int mid = partition(arr, L, R);
        process1(arr, L, mid - 1);
        process1(arr, mid + 1, R);
    }

    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    /**
     * 一次性分组，分成小于，等于，大于区；每次不再是只排序一个数字而是一组数组。
     * @param arr
     * @param L
     * @param R
     */
    public static void process2(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        // indexes[0] euqal area left boundary
        // indexes[1] equal area right boundary
        int[] indexes = netherlandsFlag(arr, L, R);
        process2(arr, L ,indexes[0] - 1);
        process2(arr, indexes[1] + 1, R);
    }

    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process3(arr, 0, arr.length - 1);
    }
    public static void process3(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] indexes = netherlandsFlag(arr, L, R);
        process3(arr, L, indexes[0] - 1);
        process3(arr, indexes[1] + 1, R);
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
        // Testing for partition
//        int[] arr = new int[]{1, 3, 4, 2};
//        System.out.println();
//        if (partition(arr, 0, 3) != 1) {
//            System.out.println("Fucking fucked");
//        } else {
//            System.out.println("Nice");
//        }
//        int[] arr1 = new int[]{1, 1, 4, 2};
//        if (partition(arr1, 1, 3) != 1) {
//            System.out.println("Fucking fucked");
//        } else {
//            System.out.println("Nice");
//        }


//        Test for netherland problem //  1, 2, 2, 4, 2
//        int[] arr3 = new int[] {4, 2, 2, 1, 2};
//        if (isEqual(netherlandsFlag2(arr3,0, 4), netherlandsFlag(arr3, 0, 4))) {
//            System.out.println("Fucking fucked");
////            System.out.println(Arrays.toString(netherlandsFlag(new int[]{4, 2, 2, 1, 2}, 0, 4)));
//        } else {
//            System.out.println("Nice!");
//        }


        // Testing for whole sorting in this class
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            quickSort1(arr1);
            quickSort2(arr2);
            quickSort3(arr3);
            if (!isEqual(arr1, arr2) || !isEqual(arr2, arr3)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");

    }
}
