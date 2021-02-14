package class05_Tries_Sort;

import java.util.HashMap;

/**
 * @author mingyan wang
 * @date 2021/1/20 4:40 PM
 */
public class generalTries {
    Node root = new Node();
    class Node {
        int pass = 0;
        int end = 0;
        HashMap<Integer, Node> paths = new HashMap<>();
    }



    public void insert(String word) {
        if (word == null) {
            return;
        }
        Node node = root;
        node.pass++;
        char[] chars = word.toCharArray();
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            index = (int) chars[i];
            if (!node.paths.containsKey(index)) {
                node.paths.put(index, new Node());
            }
            node = node.paths.get(index);
            node.pass++;
        }
        node.end++;
    }

    public int search(String word) {
        if (word == null) {
            return 0;
        }
        Node node = root;
        char[] chars = word.toCharArray();
        int index;
        for (int i = 0; i < chars.length; i++) {
            index = (int) chars[i];
            if (!node.paths.containsKey(index)) {
                return 0;
            }
            node = node.paths.get(index);
        }
        return node.end;
    }

    public int prefixWith(String prefix) {
        if (prefix == null) {
            return 0;
        }
        Node node = root;
        char[] chars = prefix.toCharArray();
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            index = chars[i];
            if (!node.paths.containsKey(index)) {
                return 0;
            }
            node = node.paths.get(index);
        }
        return node.pass;

    }

    public void delete(String word) {
        if (search(word) != 0) {
            Node node = root;
            node.pass--; // 很容易忘记
            char[] chars = word.toCharArray();
            int index;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i];
                Node next = node.paths.get(index);
                if (--next.pass == 0) {
                    node.paths.remove(index);
                    return;
                }
                node = next;
            }
            node.end--;
        }
    }


}
