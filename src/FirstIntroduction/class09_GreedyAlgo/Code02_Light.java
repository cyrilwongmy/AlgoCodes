package FirstIntroduction.class09_GreedyAlgo;

import java.util.HashSet;

/**
 * 给定一个字符串str，只由‘X’和‘.’两种字符构成。
 * ‘X’表示墙，不能放灯，也不需要点亮
 * ‘.’表示居民点，可以放灯，需要点亮
 * 如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
 * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
 * @author mingyan wang
 * @date 2021/2/27 7:07 PM
 */
public class Code02_Light {
    /**
     * 暴力算法
     * @param road
     * @return 0 或者 正无穷表示没有解
     */
    public static int minLight1(String road) {
        if (road == null || "".equals(road)) {
            return 0;
        }

        return process(road.toCharArray(), 0, new HashSet<>());
    }

    /**
     *
     * @param chars
     * @param index
     * @param lights
     * @return
     */
    public static int process(char[] chars, int index, HashSet<Integer> lights) {
        if (index == chars.length) {
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] != 'X') {
                    if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            // 如果扫描完所有的.都被照亮，那么返回总共用的灯数量
            return lights.size();
        } else {
            int no = process(chars, index + 1, lights);
            int yes = Integer.MAX_VALUE;
            if ('.' == chars[index]) {
                lights.add(index);
                yes = process(chars, index + 1, lights);
                lights.remove(index);
            }
            return Math.min(no, yes);
        }
    }

    public static int minLight2(String road) {
        int totalLight = 0;
        char[] charArray = road.toCharArray();
        int i = 0;
        while (i < charArray.length) {
            if (charArray[i] == 'X') {
                i++;
            } else { // == '.'
                totalLight++;
                // crucial boundary check
                if (i + 1 == charArray.length) {
                    break;
                } else {
                    if (charArray[i+1] == 'X') {
                        i = i + 2;
                    } else {
                        i = i + 3;
                    }
                }
            }
        }
        return totalLight;
    }

    // for test
    public static String randomString(int len) {
        char[] res = new char[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        int len = 20;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            String test = randomString(len);
            int ans1 = minLight1(test);
            int ans2 = minLight2(test);
            if (ans1 != ans2) {
                System.out.println("oops!");
            }
        }
        System.out.println("finish!");
    }
}
