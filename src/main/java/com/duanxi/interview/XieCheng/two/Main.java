package com.duanxi.interview.XieCheng.two;

import java.util.*;
import java.util.stream.Collectors;

class WorkflowNode {
    String nodeId;
    int timeoutMillis;
    List<WorkflowNode> nextNodes;
    boolean initialised;

    public static WorkflowNode load(String value) {
        // Create head node;
        Map<String, WorkflowNode> map = new HashMap<>();
        WorkflowNode head = new WorkflowNode("HEAD", 0, null);
        map.put(head.nodeId, head);

        for (String nodeValue : value.split("\\|")) {
            String[] properties = nodeValue.split("\\`");
            WorkflowNode node = map.get(properties[0]);

            node.timeoutMillis = Integer.parseInt(properties[1]);
            node.initialised = true;

            // Check next nodes
            if (properties[2].equals("END")) {
                continue;
            }
            node.nextNodes = Arrays.stream(properties[2].split(","))
                    .map(p -> new WorkflowNode(p, 0, null))
                    .collect(Collectors.toList());
            node.nextNodes.forEach(p -> map.put(p.nodeId, p));

            map.put(node.nodeId, node);
        }

        return head;
    }

    public WorkflowNode(String nodeId, int timeoutMillis, List<WorkflowNode> nextNodes) {
        this.nodeId = nodeId;
        this.timeoutMillis = timeoutMillis;
        this.nextNodes = nextNodes;
    }
}

public class Main {
    public static void main(String args[]) {
        Scanner cin = new Scanner(System.in);
        while (cin.hasNext()) {
            WorkflowNode node = null;
            try {
                node = WorkflowNode.load(cin.next());
            } catch (Exception e) {
                System.out.println(-1);
                return;
            }
            System.out.println(getMax(node));
        }
    }

    public static int getMax(WorkflowNode node) {
        if (node.nextNodes == null) return 0;
        return getMaxTime(node);
    }

    public static int getMaxTime(WorkflowNode node) {
        if (node.nextNodes == null) return node.timeoutMillis;// 返回自己的时间
        // 否则就要开始来计算当前每个分支的最大值了
        int maxTime = 0;
        List<WorkflowNode> nextNodes = node.nextNodes;
        int time = 0;
        for (WorkflowNode nextNode : nextNodes) {
            time = Math.max(time, node.timeoutMillis + getMaxTime(nextNode));
        }
        maxTime += time;
        return maxTime;
    }
}