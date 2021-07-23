package pers.xy.practice.datastruct.list;

import java.util.Random;

/**
 * @author xinyu
 * @date 2021/7/19
 */
public class SkipList<T extends Comparable<T>> {

    private SkipListNode<T> header;
    private SkipListNode<T> tail;
    private int length;
    private int maxLevel;

    public class SkipListNode<T extends Comparable<T>> {
        T obj;
        double score;
        SkipListNodeLevel[] level;
        SkipListNode backward;
    }

    class SkipListNodeLevel {
        SkipListNode forward;
        int span;//跨度
    }


    public SkipListNode insert(T obj, double score) {
        int randomLevel = getRandomLevel();
        SkipListNode<T> target = new SkipListNode<T>();
        SkipListNode[] update = new SkipListNode[Math.max(maxLevel, randomLevel)];//辅助数组，记录插入node，的每个层级的前置节点
        int[] rank = new int[update.length];//辅助数组，记录update中的节点，距head的 跨度
        //
        // 新的层级比原有层级都高。高出部分的前置节点都是 header
        if (randomLevel >= maxLevel) {
            for (int i = randomLevel - 1; i >= maxLevel; i--)
                update[i] = header;
        }
        //
        // 向右、向下查找，对update[] rank[] 赋值
        for (int i = update.length - 1; i >= 0; i--) {
            SkipListNode node = header;
            SkipListNode next = node.level[i].forward;
            // 向右查找  后序不为空 && （score> next的分值 || 分=next分 && next的obj比较小）
            while (next != null && (score > next.score || score == next.score && next.obj.compareTo(obj) < 0)) {
                rank[i] += node.level[i].span;
                node = next;
                next = next.level[i].forward;
            }
            update[i] = node;
        }
        // 高出maxLeve的层级，如果前置节点有后序节点，因为他们之间多了一个节点，所以span+1
        for (int i = randomLevel - 1; i >= maxLevel; i--) {
            if (update[i].level[i].span != 0) {
                update[i].level[i].span += 1;
            }
        }
        maxLevel = Math.max(maxLevel, randomLevel);
        //
        // 插入节点处理。
        for (int i = update.length - 1; i >= 0; i--) {
            SkipListNode pre = update[i];
            SkipListNode tmp = pre.level[i].forward;
            int span = pre.level[i].span;
            // 修改 跨度信息
            pre.level[i].span = rank[0] - rank[i] + 1;
            target.level[i].span = span > 0 ? span + rank[i] - rank[0] : 0;
            // 修改索引
            pre.level[i].forward = target;
            target.level[i].forward = tmp;

            target.backward = pre;
            //
            if (tmp == null)
                target.backward = header;
            else {
                target.backward = tmp.backward;
                tmp.backward = target;
            }
        }
        // 如果原来的尾节点，后序不为空了，新加入的target是尾节点了
        if (tail.level[0].forward != null)
            tail = target;
        length++;
        return target;
    }

    /**
     * @return
     */
    private int getRandomLevel() {
        int max = 32;
        int ret = 0;
        Random random = new Random();
        while (ret < max && random.nextInt(2) == 0) {
            ret++;
        }
        return ret;
    }


}
