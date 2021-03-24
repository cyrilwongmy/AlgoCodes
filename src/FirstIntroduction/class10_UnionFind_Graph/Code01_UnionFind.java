package FirstIntroduction.class10_UnionFind_Graph;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @author mingyan wang
 * @date 2021/3/2 12:27 PM
 */
public class Code01_UnionFind {
    public static class Node<V> {
        V value;

        public Node(V v) {
            value = v;
        }
    }
    public static class UnionSet<V> {
        // 节点值和节点对应关系map
        public HashMap<V, Node<V>> nodes;
        // K：当前节点，V：当前节点的父亲节点
        public HashMap<Node<V>, Node<V>> parent;
        // K：当前节点，V：当前节点所在的set的size
        public HashMap<Node<V>, Integer> sizeMap;

        /**
         * 根据一个List把所有值加入UnionSet中
         * @param values
         */
        public UnionSet(List<V> values) {
            for (V value : values) {
                Node<V> node = new Node<>(value);
                nodes.put(value, node);
                parent.put(node, node);
                sizeMap.put(node, 1);
            }

        }


        /**
         * 寻找当前节点的root节点，沿途历经上的所有节点都改为直接链接root节点（路径压缩）
         * @param cur
         * @return
         */
        public Node<V> findFather(Node<V> cur) {
            Node<V> target = cur;
            Stack<Node<V>> path = new Stack<>();
            while (parent.get(target) != target) {
                path.add(target);
                target = parent.get(target);
            }
            // 路径压缩
            while (!path.isEmpty()) {
                parent.put(path.pop(), target);
            }
            return target;
        }

        public boolean isSameSet(V a, V b) {
            // 很重要，要先check是否aheb都在set里面
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                return false;
            }
            return findFather(new Node<>(a)) == findFather(new Node<>(b));
        }

        public void union(V a, V b) {
            // 都在set里面才合并
            // 如果已经在同一个set，直接返回
            if (!nodes.containsKey(a) || !nodes.containsKey(b) || isSameSet(a, b)) {
                return;
            }

            Node<V> aFather = findFather(new Node<>(a));
            Node<V> bFather = findFather(new Node<>(b));
            Integer aSize = sizeMap.get(aFather);
            Integer bSize = sizeMap.get(bFather);
            Node<V> smallHead = aSize > bSize ? bFather : aFather;
            Node<V> bigHead = aSize > bSize ? aFather : bFather;
            parent.put(smallHead, bigHead);
            sizeMap.remove(smallHead);
            sizeMap.put(bigHead, aSize + bSize);

        }

    }

}
