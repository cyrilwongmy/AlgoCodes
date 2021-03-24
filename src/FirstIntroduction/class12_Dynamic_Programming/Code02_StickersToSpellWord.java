package FirstIntroduction.class12_Dynamic_Programming;

import java.util.HashMap;

/**
 * 给定一个字符串str，给定一个字符串类型的数组arr。
 * arr里的每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，
 * 目的是拼出str来。 返回需要至少多少张贴纸可以完成这个任务。
 * 例子：str= "babac"，arr = {"ba","c","abcd"}
 * 至少需要两张贴纸"ba"和"abcd"，因为使用这两张贴纸，把每一个字符单独剪开，
 * 含有2个a、2个b、1个c。是可以拼出str的。所以返回2。
 * @author mingyan wang
 * @date 2021/3/21 3:12 PM
 */
public class Code02_StickersToSpellWord {

    public static int minStickers1(String[] stickers, String target) {
        int n = stickers.length;
        int[][] map = new int[n][26];
        HashMap<String, Integer> dp = new HashMap<>();
        for (int i = 0; i < map.length; i++) {
            char[] chars = stickers[i].toCharArray();
            for (char aChar : chars) {
                map[i][aChar - 'a']++;
            }
        }
        return process(dp, map, target);
    }

    private static int process(HashMap<String, Integer> dp, int[][] map, String target) {
        if (dp.containsKey(target)) {
            return dp.get(target);
        }
        int ans = Integer.MAX_VALUE;
        char[] targets = target.toCharArray();
        int tmap[] = new int[26];
        for (char c : targets) {
            tmap[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < map.length; i++) {
            if (map[i][targets[0] - 'a'] == 0) {
                continue;
            }
            for (int j = 0; j < 26; j++) {
                if (tmap[j] > 0) {
                    for (int k = 0; k < Math.max(0, tmap[j] - map[i][j]); k++) {
                        sb.append((char) 'a' + j);
                    }
                }
            }
            String s = sb.toString();
            int tmp = process(dp, map, s);
            if (tmp != -1) {
                ans = Math.min(ans, 1 + tmp);
            }
        }

        dp.put(target, ans == Integer.MAX_VALUE ? -1 : ans);
        return dp.get(target);

    }
}
