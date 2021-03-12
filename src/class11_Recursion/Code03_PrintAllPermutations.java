package class11_Recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mingyan wang
 * @date 2021/3/8 11:17 AM
 */
public class Code03_PrintAllPermutations {
    // 打印一个字符串的全部排列
    public static ArrayList<String> permutation(String str) {
        if (str == null || "".equals(str)) {
            return new ArrayList<>();
        }
        char[] charArray = str.toCharArray();
        ArrayList<String> ans = new ArrayList<>();

        process1(charArray, 0, ans);

        return ans;
    }


    /**
     * index = 0, path = ""
     * abc
     *
     * @param charArray
     * @param index
     * @param ans
     */
    public static void process1(char[] charArray, int index, ArrayList<String> ans) {
        if (index == charArray.length) {
            ans.add(new String(charArray));
            return;
        }
        // no swap
        for (int i = index; i < charArray.length; i++) {
            swap(index, i, charArray);
            process1(charArray, index + 1, ans);
            swap(index, i, charArray);
        }
    }

    public static void swap(int i, int j, char[] charArray) {
        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
    }


    /**
     * 打印一个字符串的全部排列，要求不要出现重复的排列
     *
     * @param str
     * @return
     */
    public static ArrayList<String> permutationNoRepeat(String str) {
        if (str == null || "".equals(str)) {
            return new ArrayList<>();
        }
        ArrayList<String> ans = new ArrayList<>();

        process2(str.toCharArray(), 0, ans);

        return ans;
    }

    public static void process2(char[] charArray, int index, ArrayList<String> ans) {
        if (index == charArray.length) {
            ans.add(new String(charArray));
        }
        boolean[] visited = new boolean[26];
        for (int i = index; i < charArray.length; i++) {
            if (!visited[charArray[i] - 'a']) {
                visited[charArray[i] - 'a'] = true;
                swap(i, index, charArray);
                process2(charArray, index + 1, ans);
                swap(i, index, charArray);
            }
        }
    }


    public static void main(String[] args) {
        String s = "aac";
        List<String> ans1 = permutation(s);
        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("=======");
        List<String> ans2 = permutationNoRepeat(s);
        for (String str : ans2) {
            System.out.println(str);
        }

    }
}
