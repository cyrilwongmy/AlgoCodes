package class05;

/**
 * @author mingyan wang
 * @date 2021/1/16 12:35 PM
 */
public class Tries {

    public static class Node1 {
        int pass;
        int end;
        Node1[] nexts;
        public Node1() {
            pass = 0;
            end = 0;
            nexts = new Node1[26];
        }
    }
    public static class Trie1 {
        private Node1 root;

        public Trie1() {
            root = new Node1();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] chs = word.toCharArray();
            Node1 node = root;
            node.pass++;
            int index = 0;
            for (int i = 0; i < chs.length; i++) { // 从左往右遍历字符
                index = chs[i] - 'a'; // 由字符，对应成走向哪条路
                if (node.nexts[index] == null) {
                    node.nexts[index] = new Node1();
                }
                node = node.nexts[index];
                node.pass++;
            }
            node.end++;
        }

        public void delete(String word) {
            if (search(word) != 0) {
                char[] chs = word.toCharArray();
                Node1 node = root;
                node.pass--;
                int index = 0;
                for (int i = 0; i < chs.length; i++) {
                    index = chs[i] - 'a';
                    if (--node.nexts[index].pass == 0) {
                        node.nexts[index] = null;
                        return;
                    }
                    node = node.nexts[index];
                }
                node.end--;
            }
        }

        // word这个单词之前加入过几次
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chs = word.toCharArray();
            Node1 node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.end;
        }

        // 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] chs = pre.toCharArray();
            Node1 node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.pass;
        }
    }

//    static class Trie1 {
//        private Node root;
//
//        public Trie1() {
//            root = new Node();
//        }
//
//        public void insert(String word) {
//            if (word == null) {
//                return;
//            }
//            char[] str = word.toCharArray();
//            Node curNode = root;
//            curNode.pass++;
//            int index = 0;
//            for (int i = 0; i < str.length; i++) {
//                index = str[i] - 'a';
//                Node next = curNode.paths[index];
//                if (next == null) {
//                    next = new Node();
//                }
//                next.pass++;
//                curNode = next;
//            }
//            curNode.end++;
//        }
//
//
//        public int search(String str) {
//            if (str == null) {
//                return 0;
//            }
//            Node node = root;
//
//            char[] chars = str.toCharArray();
//            int index;
//            for (int i = 0; i < chars.length; i++) {
//                index = chars[i] - 'a';
//                Node next = node.paths[index];
//                if (next == null) {
//                    return 0;
//                }
//                node = next;
//            }
//            return node.end;
//        }
//
//        public void delete(String word) {
//            if (word == null || (search(word) == 0)) {
//                return;
//            }
//            Node node = root;
//            root.pass--;
//            int index = 0;
//            char[] chars = word.toCharArray();
//            for (int i = 0; i < chars.length; i++) {
//                index = chars[i] - 'a';
//                Node next = node.paths[index];
//                next.pass--;
//                node = next;
//                if (node.pass == 0) {
//                    node = null;
//                    break;
//                }
//            }
//            if (node != null) {
//                node.end--;
//            }
//        }
//
//        public int prefixWith(String prefix) {
//            if (prefix == null) {
//                return 0;
//            }
//            Node node = root;
//            int index;
//            char[] chars = prefix.toCharArray();
//            for (int i = 0; i < chars.length; i++) {
//                index = chars[i] - 'a';
//                Node next = node.paths[index];
//                if (next == null) {
//                    return 0;
//                }
//                node = next;
//            }
//
//            return node.pass;
//        }
//    }


}
