package class09_GreedyAlgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

/**
 * 给定一个由字符串组成的数组strs，
 * 必须把所有的字符串拼接起来，
 * 返回所有可能的拼接结果中，
 * 字典序最小的结果
 * @author mingyan wang
 * @date 2021/2/27 7:07 PM
 */
public class Code01_LowestLexicography {

    /**
     * 暴力解法，所有字符串排列组合。
     * 当作对数器用
     * @param strs
     * @return
     */
    public static String lowestString1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        HashSet<Integer> usedStrIdx = new HashSet<>();
        ArrayList<String> resultStrs = new ArrayList<>();
        process(usedStrIdx, "", strs, resultStrs);
        String lowestResult = resultStrs.get(0);
        for (int i = 1; i < resultStrs.size(); i++) {
            if (lowestResult.compareTo(resultStrs.get(i)) > 0) {
                lowestResult = resultStrs.get(i);
            }

        }
        return lowestResult;
    }

    /**
     *
     * @param usedStrIdx 已经用过的字符串的下标，当前函数就不要再使用了。
     * @param concatStrings 之前拼接到目前的字符串
     * @param strs 所有可选择的字符串，也包括了使用过的，需要根据 usedStrIdx去除掉用过的字符串。
     * @param resultStrs 所有可能的拼接结果
     */
    public static void process(HashSet<Integer> usedStrIdx,
                               String concatStrings,
                               String[] strs,
                               ArrayList<String> resultStrs) {
        if (usedStrIdx.size() == strs.length) {
            resultStrs.add(concatStrings);
        } else {
            for (int i = 0; i < strs.length; i++) {
                // 只选择未使用过的字符串
                if (!usedStrIdx.contains(i)) {
                    usedStrIdx.add(i);
                    process(usedStrIdx, concatStrings + strs[i], strs, resultStrs);
                    usedStrIdx.remove(i);
                }
            }
        }
    }


    /**
     * 贪心算法：字符串A和字符串B，A拼接B如果小于B拼接A，那么就让A放在B前面。
     * 解法：字符串数组按照拼接的结果排序，排序后的所有字符串拼接结果即为最小字典序拼接。
     * @param strs 给定的所有可选择字符串
     * @return 所有可选择字符串拼接结果中字典序最小的
     */
    public static String lowestString2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1+o2).compareTo(o2 + o1);
            }
        });
        StringBuilder resultStr = new StringBuilder();
        for (String str : strs) {
            resultStr.append(str);
        }
        return resultStr.toString();
    }


    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 5);
            ans[i] = (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    // for test
    public static String[] copyStringArray(String[] arr) {
        String[] ans = new String[arr.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = String.valueOf(arr[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 6;
        int strLen = 5;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr1 = generateRandomStringArray(arrLen, strLen);
            String[] arr2 = copyStringArray(arr1);
            if (!lowestString1(arr1).equals(lowestString2(arr2))) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
