package pers.xy.practice.algorithm;

/**
 * 汉诺塔 3根柱子 递归算法
 *
 * @author xinyu
 * @date 2021/7/6
 */
public class Hanoi {
//  private final Logger logger = LoggerFactory.getLogger(getClass());

    int step = 0;

    public void hanoi(int n, String from, String to, String other) {
        if (n == 1) {
            step++;
//            System.out.println(from + " -> " + to);
        } else {
            hanoi(n - 1, from, other, to);
            hanoi(1, from, to, other);
            hanoi(n - 1, other, to, from);
        }
    }


    public static void main(String[] args) {
        int n = 10;
        Hanoi hanoi = new Hanoi();
        hanoi.hanoi(n, "A", "C", "B");
        int expect = Double.valueOf(Math.pow(2, n)).intValue() - 1;
        System.out.println("Step: " + hanoi.step + " == " + expect + " ? " + (hanoi.step == expect));
    }
}
