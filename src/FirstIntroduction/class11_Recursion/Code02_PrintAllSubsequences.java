package FirstIntroduction.class11_Recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author mingyan wang
 * @date 2021/3/8 11:16 AM
 */
public class Code02_PrintAllSubsequences {

    /**
     * 打印一个字符串的全部子序列，可以有重复的子序列。
     * （注意不是字串，字串是不能跳跃字符选择，面试要问清楚）
     * abc --> a, ab, abc, b, bc, c
     * 算法流程：从左往右，经过每一个char时候选择是否pick这个char
     * @param s
     * @return
     */
    public static List<String> subs(String s) {
        char[] charArray = s.toCharArray();
        List<String> ans = new ArrayList<>();
        process1(charArray, 0, "", ans);

        return ans;
    }

    public static void process1(char[] charArray, int index, String path ,List<String> ans) {
        // base case
        if (index == charArray.length) {
            ans.add(path);
            return;
        }
        // choose next char
        process1(charArray, index + 1, path + charArray[index], ans);
        // don't choose next char
        process1(charArray, index + 1, path, ans);
    }

    /**
     * 打印一个字符串的全部子序列，要求不要出现重复字面值的子序列
     * @param s
     * @return
     */
    public static List<String> subsNoRepeat(String s) {
        if (s == null || "".equals(s)) {
            return new ArrayList<>();
        }
        char[] charArray = s.toCharArray();
        HashSet<String> ans = new HashSet<>();
        process2(charArray, 0, "", ans);

        return new ArrayList<>(ans);
    }

    public static void process2(char[] charArray, int index, String path, HashSet<String> ans) {
        if (index == charArray.length) {
            ans.add(path);
            return;
        }
        // choose char in index
        process2(charArray, index + 1, path + charArray[index], ans);
        // don't choose
        process2(charArray, index + 1, path , ans);

    }



        public static void main(String[] args) {
        String test = "aacc";
//        String test = "abcd";
        List<String> ans1 = subs(test);
        List<String> ans2 = subsNoRepeat(test);

        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("=================");
        for (String str : ans2) {
            System.out.println(str);
        }
        System.out.println("=================");

    }
}
