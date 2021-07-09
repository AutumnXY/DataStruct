package pers.xy.practice.datastruct.tree;

/**
 * @author xinyu
 * @date 2020/8/24
 */
public class AVLTest {

    //        private static Integer arr[] = {3, 2, 1, 4, 5, 6, 7, 16, 15, 14, 13, 12, 11, 10, 8, 9, 0};
    private static Integer arr[] = {3, 2, 1, 4, 5, 6, 7, 8, 16, 15, 15, 13, 12, 11, 10};
//    public static String arr[] = {"E", "A", "G", "C", "B", "D", "F"};

    public static void main(String[] args) {
        int i;
        AVL2 tree = new AVL2();

        for (i = 0; i < arr.length; i++) {
//            System.out.printf("%d ", arr[i]);
            tree.add(arr[i]);
        }
        System.out.println("");

        i = 6;
//        System.out.printf("\n== 删除根节点: %d", i);
//        tree.remove(i);
        tree.preOrder();
        System.out.println("");
        tree.middleOrder();
        System.out.println("");
        tree.lastOrder();

    }
}
