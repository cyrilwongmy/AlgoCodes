package FirstIntroduction.class11_Recursion;

/**
 * @author mingyan wang
 * @date 2021/3/8 11:16 AM
 */
public class Code01_Hanoi {

    /**
     * 比较粗暴的写法
     * @param n
     */
    public static void hanoi1(int n) {
        leftToRight(n);
    }

    private static void leftToRight(int n) {
        if (n == 1) {
            System.out.println("Move 1 from left to right");
            return;
        }
        // 如果不只是一个在上面，需要先把n-1移动到middle，把n移动到right，最后把middle的n-1个移动到right
        leftToMiddle(n - 1);
        System.out.println("Move n from left to right");
        middleToRight(n - 1);
    }

    private static void middleToRight(int n) {
        if (n == 1) {
            System.out.println("Move 1 from middle to right");
            return;
        }
        middleToLeft(n - 1);
        System.out.println("Move n from middle to right");
        leftToRight(n - 1);
    }

    private static void leftToMiddle(int n) {
        if (n == 1) {
            System.out.println("Move 1 from left to middle");
            return;
        }
        leftToRight(n - 1);
        System.out.println("Move n from left to middle");
        rightToMiddle(n - 1);
    }

    private static void rightToMiddle(int n) {
        if (n == 1) {
            System.out.println("Move 1 from right to middle");
            return;
        }
        rightToLeft(n - 1);
        System.out.println("Move n from right to middle");
        leftToMiddle(n - 1);

    }

    private static void middleToLeft(int n) {
        if (n == 1) {
            System.out.println("Move 1 from middle to left");
            return;
        }
        middleToRight(n - 1);
        System.out.println("Move n from middle to left");
        rightToLeft(n - 1);
    }


    private static void rightToLeft(int n) {
        if (n == 1) {
            System.out.println("Move 1 from right to left");
            return;
        }
        rightToMiddle(n - 1);
        System.out.println("Move n from right to left");
        middleToLeft(n - 1);
    }

    public static void hanoi2(int n) {
        if (n > 0) {
            func(n, "left", "right", "middle");
        }
    }

    private static void func(int n, String from, String to, String others) {
        if (n == 1) {
            System.out.println("Move 1 from " + from + " to " + to);
            return;
        }
        func(n - 1, from, others, to);
        System.out.println("Move n from " + from + " to " + to);
        func(n - 1, others, to, from);

    }


    public static void main(String[] args) {
        int n = 3;
        hanoi1(n);
        System.out.println("============");
        hanoi2(n);
        System.out.println("============");
    }
}
