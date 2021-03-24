package FirstIntroduction.class11_Recursion;

/**
 * 规定1和A对应、2和B对应、3和C对应...
 * 那么一个数字字符串比如"111”就可以转化为:
 * "AAA"、"KA"和"AK"
 * 给定一个只有数字字符组成的字符串str，
 * 返回有多少种转化结果
 * @author mingyan wang
 * @date 2021/3/8 11:17 AM
 */
public class Code05_ConvertToLetterString {
    public static int number(String str) {
        if (str == null || "".equals(str)) {
            return 0;
        }
        int[] ans = new int[1];
        process(str.toCharArray(), 0, ans);
        return ans[0];
    }

    /**
     * 110011
     * @param str
     * @param index
     * @param ans
     */
    private static void process(char[] str, int index, int[] ans) {
        if (index == str.length) {
            ans[0] = ans[0] + 1;
            return;
        }
        if ('0' == (str[index])) {
            return;
        }
        if ('1' == (str[index])) {
            // only take it as 1.
            process(str, index + 1, ans);
            // take it as 10, 11...19
            if (index + 1 < str.length) {
                process(str, index + 2, ans);
            }
            return;
        }
        if ('2' == (str[index])) {
            // only take it as 2
            process(str, index + 1, ans);
            // take it as 20 ... 26
            if (index + 1 < str.length && str[index] >= '0' && str[index] <= '6') {
                process(str, index + 2, ans);
            }
            // otherwise not a result
            return;
        }
        return;
    }




    // i之前的位置，如何转化已经做过决定了, 不用再关心
    // i... 有多少种转化的结果
    // 实现方法二，更加简介一些
    public static int process2(char[] str, int i) {
        if (i == str.length) { // base case
            return 1;
        }
        // i没有到终止位置, 比如10，只能转成J，不能转成A0
        if (str[i] == '0') {
            return 0;
        }
        // str[i]字符不是‘0’
        if (str[i] == '1') {
            int res = process2(str, i + 1); // i自己作为单独的部分，后续有多少种方法
            if (i + 1 < str.length) {
                res += process2(str, i + 2); // (i和i+1)作为单独的部分，后续有多少种方法
            }
            return res;
        }
        if (str[i] == '2') {
            int res = process2(str, i + 1); // i自己作为单独的部分，后续有多少种方法
            // (i和i+1)作为单独的部分并且没有超过26，后续有多少种方法
            if (i + 1 < str.length && (str[i + 1] >= '0' && str[i + 1] <= '6')) {
                res += process2(str, i + 2); // (i和i+1)作为单独的部分，后续有多少种方法
            }
            return res;
        }
        // str[i] == '3' ~ '9'
        // 只有一种选择，所以不需要考虑 递归考虑
        return process2(str, i + 1);
    }



    public static void main(String[] args) {
        System.out.println(number("11111"));
    }
}
