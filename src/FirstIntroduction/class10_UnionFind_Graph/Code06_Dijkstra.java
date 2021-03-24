package FirstIntroduction.class10_UnionFind_Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author mingyan wang
 * @date 2021/3/2 12:28 PM
 */
public class Code06_Dijkstra {
    public static HashMap<Node, Integer> dijkstra1(Node start) {
        HashSet<Node> selectedNodes = new HashSet<>();
        HashMap<Node, Integer> distanceMap = new HashMap<>();

        distanceMap.put(start, 0);
        Node minNode = getMinDistanceAndUnSelectedNodes(distanceMap, selectedNodes);
        while (minNode != null) {
            Integer distanceSoFar = distanceMap.get(minNode);
            for (Edge edge : minNode.edges) {
                Integer originDistance = distanceMap.get(edge.to);
                distanceMap.put(edge.to,
                        Math.min(distanceSoFar + edge.weight,
                                originDistance ==  null ?
                                        Integer.MAX_VALUE : originDistance ));
            }
            selectedNodes.add(minNode);
            minNode = getMinDistanceAndUnSelectedNodes(distanceMap, selectedNodes);
        }

        return distanceMap;
    }

    private static Node getMinDistanceAndUnSelectedNodes(
            HashMap<Node, Integer> distanceMap,
            HashSet<Node> selectedNodes) {

        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            if (!selectedNodes.contains(entry.getKey())) {
                if (entry.getValue() < minDistance) {
                    minDistance = entry.getValue();
                    minNode = entry.getKey();
                }
            }
        }

        return minNode;
    }


    public static class NodeHeap {
        private Node[] nodes;
        private HashMap<Node, Integer> heapIndexMap;
        // key 某一个节点， value 从源节点出发到该节点的目前最小距离
        private HashMap<Node, Integer> distanceMap;
        int size = 0;

        public NodeHeap(int size) {
            this.size = size;
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
        }

        public void addOrUpdateOrIgnore(Node node, int distance) {
            // update
            if (inHeap(node)) {
                Integer originDistance = distanceMap.get(node);
                distanceMap.put(node, Math.min(originDistance, distance));
                // important
                insertHeapify(node, heapIndexMap.get(node));
            }
            // add
            if (!isEntered(node)) {
                nodes[size] = node;
                heapIndexMap.put(node, size);
                distanceMap.put(node, distance);
                insertHeapify(node, size++);
            }
            // ignore: entered but not in heap
        }

        private void insertHeapify(Node node, Integer index) {
            while (distanceMap.get(nodes[index]) > distanceMap.get(nodes[(index - 1) / 2]) ) {
                swap(index, (index - 1) / 2);
                // start from 0
                index = (index - 1) / 2;
            }
        }

        /**
         * 表示节点进来过heap，不论是不是ignore状态（-1）或者在inheap状态
         * @param node
         * @return
         */
        private boolean isEntered(Node node) {
            return heapIndexMap.containsKey(node);
        }

        private boolean inHeap(Node node) {
            return isEntered(node) && heapIndexMap.get(node) != -1;
        }

        public NodeRecord pop() {
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            swap(0, size - 1);
            heapIndexMap.put(nodes[size - 1], -1);
            distanceMap.remove(nodes[size - 1]);
            nodes[size -1] = null;
            siftDown(0, --size);
            return nodeRecord;
        }

        private void siftDown(int index, int size) {
            int left = index * 2 + 1;
            while (left < size) {
                int smallest = left + 1 < size && distanceMap.get(nodes[left]) < distanceMap.get(nodes[left + 1])
                        ? left +1 : left;
                smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ? index : smallest;
                if (smallest == index) {
                    break;
                }
                swap(smallest, index);
                index = smallest;
                left = index * 2 + 1;
            }
        }

        private void swap(int i, int j) {
            heapIndexMap.put(nodes[i], j);
            heapIndexMap.put(nodes[j], i);
            Node temp = nodes[i];
            nodes[i] = nodes[j];
            nodes[j] = temp;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }

    public static class NodeRecord {
        Node node;
        int distance;

        public NodeRecord(Node node, Integer integer) {
            this.node = node;
            this.distance = integer;
        }
    }

    // 改进后的dijkstra算法，使用自建堆
    // 从head出发，所有head能到达的节点，生成到达每个节点的最小路径记录并返回
    public static HashMap<Node, Integer> dijkstra2(Node head, int size) {
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(head, 0);
        HashMap<Node, Integer> result = new HashMap<>();
        while (!nodeHeap.isEmpty()) {
            NodeRecord minNodeRecord = nodeHeap.pop();
            Node curNode = minNodeRecord.node;
            int curDistance = minNodeRecord.distance;
            for (Edge edge : curNode.edges) {
                nodeHeap.addOrUpdateOrIgnore(edge.to, curDistance + edge.weight);
            }
            result.put(curNode, curDistance);
        }
        return result;
    }










}
