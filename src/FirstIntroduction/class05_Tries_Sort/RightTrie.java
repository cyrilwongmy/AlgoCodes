package FirstIntroduction.class05_Tries_Sort;

import java.util.HashMap;

/**
 * @author mingyan wang
 * @date 2021/1/20 5:01 PM
 */
public class RightTrie {
    private final HashMap<String, Integer> box;

    public RightTrie() {
        box = new HashMap<>();
    }

    public void insert(String word) {
        if (!box.containsKey(word)) {
            box.put(word, 1);
        } else {
            box.put(word, box.get(word) + 1);
        }
    }

    public int search(String word) {
        if (box.containsKey(word)) {
            return box.get(word);
        }
        return 0;
    }

    public void delete(String word) {
        if (box.containsKey(word)) {
            if (box.get(word) == 1) {
                box.remove(word);
            } else {
                box.put(word, box.get(word) - 1);
            }
        }
    }

    public int prefixWith(String prefix) {
        int count = 0;
        for (String word: box.keySet()) {
            if (word.startsWith(prefix)) {
                count += box.get(word);
            }
        }
        return count;
    }
    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 6);
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

    public static void main(String[] args) {
        int arrLen = 100;
        int strLen = 20;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);
            Tries.Trie1 trie1 = new Tries.Trie1();
            generalTries trie2 = new generalTries();
            RightTrie right = new RightTrie();
            for (int j = 0; j < arr.length; j++) {
                double decide = Math.random();
                if (decide < 0.25) {
                    trie1.insert(arr[j]);
                    trie2.insert(arr[j]);
                    right.insert(arr[j]);
                } else if (decide < 0.5) {
                    trie1.delete(arr[j]);
                    trie2.delete(arr[j]);
                    right.delete(arr[j]);
                } else if (decide < 0.75) {
                    int ans1 = trie1.search(arr[j]);
                    int ans2 = trie2.search(arr[j]);
                    int ans3 = right.search(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                } else {
                int ans1 = trie1.prefixNumber(arr[j]);
                int ans2 = trie2.prefixWith(arr[j]);
                int ans3 = right.prefixWith(arr[j]);
                    if (ans1 != ans3 ) {
                        System.out.println("Ans 1 != Right");
                        System.out.println("ANS1" + ans1);
                        System.out.println("ANS3" + ans3);
                        System.out.println("ANS2" + ans2);
                    }
                    if (ans2 != ans3 ) {
                        System.out.println("Ans 2 != Right");
                    }
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        System.out.println("finish!");

    }
}

