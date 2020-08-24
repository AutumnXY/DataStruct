package pers.xy.practice.datastruct.tree;

/**
 * @author xinyu
 * @date 2020/8/24
 */
public class AVLTest {

    private static int arr[] = {3, 2, 1, 4, 5, 6, 7, 16, 15, 14, 13, 12, 11, 10, 8, 9, 0};


    public static void main(String[] args) {
        int i;
        AVL tree = new AVL();

        System.out.printf("== 依次添加: ");
        for (i = 0; i < arr.length; i++) {
            System.out.printf("%d ", arr[i]);
            tree.insert(arr[i]);
        }

        i = 6;
        System.out.printf("\n== 删除根节点: %d", i);
        tree.remove(i);

    }
}
