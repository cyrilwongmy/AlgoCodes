package FirstIntroduction.class07_BST;

/**
 * @author mingyan wang
 * @date 2021/2/14 7:36 PM
 */
public class Code08_PaperFolding {
    //TODO:
    public static void printAllFolds(int N) {
        printProcess(1, N, true);
    }

    // down = true up = false
    private static void printProcess(int i, int n, boolean down) {
        if (i > n) {
            return;
        }
        printProcess(i + 1, n, true);
        System.out.println(down ? "down" : "up");
        printProcess(i + 1, n, false);
    }
    public static void main(String[] args) {
        int N = 3;
        printAllFolds(N);
    }
}
