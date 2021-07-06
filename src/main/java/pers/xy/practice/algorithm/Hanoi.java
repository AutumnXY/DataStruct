package pers.xy.practice.algorithm;

/**
 * 汉诺塔 3根柱子 递归算法
 *
 * @author xinyu
 * @date 2021/7/6
 */
public class Hanoi {
//  private final Logger logger = LoggerFactory.getLogger(getClass());


    public void hanoi(int n, String from, String to, String other) {
        if (n == 1)
            System.out.println(from + " -> " + to);
        else {
            hanoi(n - 1, from, other, to);
            hanoi(1, from, to, other);
            hanoi(n - 1, other, to, from);
        }
    }


    public static void main(String[] args) {
        Hanoi hanoi = new Hanoi();
        hanoi.hanoi(10, "A", "C", "B");
    }
}
