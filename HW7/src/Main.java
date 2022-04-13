import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;
import java.util.concurrent.TimeUnit;
public class Main{
    public static void main(String Args[]){

        System.out.println("\t\t ### PART 1 ###\n");
        {
            System.out.println("\n\t## Testing NavigableSkipList ##\n");
            NavigableSet<Integer> list = new NavigableSkipList<>();
            StringBuilder res1 = new StringBuilder();

            list.add(40);
            list.add(38);
            list.add(28);
            list.add(12);
            list.add(49);
            list.add(47);
            list.add(41);
            list.add(9);
            list.add(5);

            System.out.println("\nTesting descendingIterator");
            Iterator iter = list.descendingIterator();
            while (iter.hasNext()) {
                res1.append(iter.next());
                res1.append(" ");
            }
            System.out.println(res1);
            StringBuilder res2 = new StringBuilder();
            System.out.println("\nAfter removing 9");
            list.remove(9);
            iter = list.descendingIterator();

            while (iter.hasNext()) {
                res2.append(iter.next());
                res2.append(" ");
            }
            System.out.println(res2);
        }
        {
            System.out.println("\n\t\t## Testing Navigable AVL Tree ##\n");
            NavigableSet<Integer> tree = new NavigableAVLTree<>();

            StringBuilder res1 = new StringBuilder();
            StringBuilder res2 = new StringBuilder();
            tree.add(40);
            tree.add(38);
            tree.add(28);
            tree.add(12);
            tree.add(49);
            tree.add(47);
            tree.add(41);
            tree.add(9);
            tree.add(5);
            System.out.println("\nTesting Iterator");
            Iterator iter = tree.iterator();

            while (iter.hasNext()) {
                res1.append(iter.next());
                res1.append(" ");
            }
            System.out.println(res1);
            System.out.println("\nTesting headset");
            {
                StringBuilder res = new StringBuilder();
                NavigableSet newTree = tree.headSet(30, false);
                Iterator iter1 = newTree.iterator();

                while (iter1.hasNext()) {
                    res.append(iter1.next());
                    res.append(" ");
                }
                System.out.println(res);
            }
            System.out.println("\nTesting tailset");
            {
                StringBuilder res = new StringBuilder();
                NavigableSet newTree = tree.tailSet(30, false);
                Iterator iter1 = newTree.iterator();

                while (iter1.hasNext()) {
                    res.append(iter1.next());
                    res.append(" ");
                }
                System.out.println(res);
            }
            tree.remove(9);
            iter = tree.iterator();
            while(iter.hasNext()){
                res2.append(iter.next());
                res2.append(" ");
            }
            System.out.println("\nAfter removing 9");
            System.out.println(res2);
        }
        System.out.println("\n\n\t\t ### PART 2 ###\n");
        System.out.println("\nTesting with inserting items like RED-BLACK TREE\n");
        {
            BinarySearchTree<Integer> tree = new BinarySearchTree<>();
            tree.add(7);
            tree.add(3);
            tree.add(18);
            tree.add(10);
            tree.add(22);
            tree.add(10);
            tree.add(11);
            tree.add(22);
            tree.add(26);

            whichTree(tree);
        }
        System.out.println("\nTesting with inserting items like AVL TREE\n");
        {
            BinarySearchTree<Integer> tree = new BinarySearchTree<>();
            tree.add(100);
            tree.add(50);
            tree.add(150);
            tree.add(25);
            tree.add(75);
            tree.add(125);
            tree.add(175);
            tree.add(65);
            tree.add(85);

            whichTree(tree);
        }
        System.out.println("\n\n\t\t ### PART 3 ###\n");
        Random rand = new Random();
        ArrayList<BinarySearchTree<Integer> > bSearchTrees10 = new ArrayList<>();
        ArrayList<BinarySearchTree<Integer> > bSearchTrees20 = new ArrayList<>();
        ArrayList<BinarySearchTree<Integer> > bSearchTrees40 = new ArrayList<>();
        ArrayList<BinarySearchTree<Integer> > bSearchTrees80 = new ArrayList<>();
        int count = 0;
        for(int i=0; i<10; ++i){
            bSearchTrees10.add(new BinarySearchTree<>());
            bSearchTrees20.add(new BinarySearchTree<>());
            bSearchTrees40.add(new BinarySearchTree<>());
            bSearchTrees80.add(new BinarySearchTree<>());
        }
        double totalTime10=0,totalTime20=0,totalTime40=0,totalTime80=0;
        for(int i=0; i<10; ++i) {
            double start = System.currentTimeMillis();
            while (count < 10000) {
                int num = rand.nextInt();
                if(bSearchTrees10.get(i).add(num))
                    ++count;
            }
            double end = System.currentTimeMillis();
            totalTime10 += end - start;
            count = 0;
            start = System.currentTimeMillis();
            while(count < 20000){
                int num = rand.nextInt();
                if(bSearchTrees20.get(i).add(num))
                    ++count;
            }
            end = System.currentTimeMillis();
            totalTime20 += end - start;
            count = 0;
            start = System.currentTimeMillis();
            while(count < 40000){
                int num = rand.nextInt();
                if(bSearchTrees40.get(i).add(num))
                    ++count;
            }
            end = System.currentTimeMillis();
            totalTime40 += end - start;
            count = 0;
            start = System.currentTimeMillis();
            while(count < 80000){
                int num = rand.nextInt();
                if(bSearchTrees80.get(i).add(num))
                    ++count;
            }
            end = System.currentTimeMillis();
            totalTime80 += end - start;
            count = 0;
        }
        System.out.println("\t\t### Testing Binary Search Tree ###\n\n");
        System.out.println("Average time of 10000 adding element Binary Search Tree " +  totalTime10/10 + " ms");
        System.out.println("Average time of 20000 adding element Binary Search Tree " +  totalTime20/10 + " ms");
        System.out.println("Average time of 40000 adding element Binary Search Tree " +  totalTime40/10 + " ms");
        System.out.println("Average time of 80000 adding element Binary Search Tree " +  totalTime80/10 + " ms");

        ArrayList<RedBlackTree<Integer> > redBlackTreesTree10 = new ArrayList<>();
        ArrayList<RedBlackTree<Integer> > redBlackTreesTree20 = new ArrayList<>();
        ArrayList<RedBlackTree<Integer> > redBlackTreesTree40 = new ArrayList<>();
        ArrayList<RedBlackTree<Integer> > redBlackTreesTree80 = new ArrayList<>();
        count = 0;
        for(int i=0; i<10; ++i){
            redBlackTreesTree10.add(new RedBlackTree<>());
            redBlackTreesTree20.add(new RedBlackTree<>());
            redBlackTreesTree40.add(new RedBlackTree<>());
            redBlackTreesTree80.add(new RedBlackTree<>());
        }
        totalTime10=0;totalTime20=0;totalTime40=0;totalTime80=0;
        for(int i=0; i<10; ++i) {
            double start = System.currentTimeMillis();
            while (count < 10000) {
                int num = rand.nextInt();
                if(redBlackTreesTree10.get(i).add(num))
                    ++count;
            }
            double end = System.currentTimeMillis();
            totalTime10 += end - start;
            count = 0;
            start = System.currentTimeMillis();
            while(count < 20000){
                int num = rand.nextInt();
                if(redBlackTreesTree20.get(i).add(num))
                    ++count;
            }
            end = System.currentTimeMillis();
            totalTime20 += end - start;
            count = 0;
            start = System.currentTimeMillis();
            while(count < 40000){
                int num = rand.nextInt();
                if(redBlackTreesTree40.get(i).add(num))
                    ++count;
            }
            end = System.currentTimeMillis();
            totalTime40 += end - start;
            count = 0;
            start = System.currentTimeMillis();
            while(count < 80000){
                int num = rand.nextInt();
                if(redBlackTreesTree80.get(i).add(num))
                    ++count;
            }
            end = System.currentTimeMillis();
            totalTime80 += end - start;
            count = 0;
        }
        System.out.println("\n\n\t\t### Testing Red Black Tree ###\n\n");
        System.out.println("Average time of 10000 adding element to Red Black Tree " +  totalTime10/10 + " ms");
        System.out.println("Average time of 20000 adding element to Red Black Tree " +  totalTime20/10 + " ms");
        System.out.println("Average time of 40000 adding element to Red Black Tree " +  totalTime40/10 + " ms");
        System.out.println("Average time of 80000 adding element to Red Black Tree " +  totalTime80/10 + " ms");

        ArrayList<TwoThreeFourTree<Integer> > two_threeTree10 = new ArrayList<>();
        ArrayList<TwoThreeFourTree<Integer> > two_threeTree20 = new ArrayList<>();
        ArrayList<TwoThreeFourTree<Integer> > two_threeTree40 = new ArrayList<>();
        ArrayList<TwoThreeFourTree<Integer> > two_threeTree80 = new ArrayList<>();
        count = 0;
        for(int i=0; i<10; ++i){
            two_threeTree10.add(new TwoThreeFourTree<>());
            two_threeTree20.add(new TwoThreeFourTree<>());
            two_threeTree40.add(new TwoThreeFourTree<>());
            two_threeTree80.add(new TwoThreeFourTree<>());
        }
        totalTime10=0;totalTime20=0;totalTime40=0;totalTime80=0;
        for(int i=0; i<10; ++i) {
            double start = System.currentTimeMillis();
            while (count < 10000) {
                int num = rand.nextInt();
                if(two_threeTree10.get(i).add(num))
                    ++count;
            }
            double end = System.currentTimeMillis();
            totalTime10 += end - start;
            count = 0;
            start = System.currentTimeMillis();
            while(count < 20000){
                int num = rand.nextInt();
                if(two_threeTree20.get(i).add(num))
                    ++count;
            }
            end = System.currentTimeMillis();
            totalTime20 += end - start;
            count = 0;
            start = System.currentTimeMillis();
            while(count < 40000){
                int num = rand.nextInt();
                if(two_threeTree40.get(i).add(num))
                    ++count;
            }
            end = System.currentTimeMillis();
            totalTime40 += end - start;
            count = 0;
            start = System.currentTimeMillis();
            while(count < 80000){
                int num = rand.nextInt();
                if(two_threeTree80.get(i).add(num))
                    ++count;
            }
            end = System.currentTimeMillis();
            totalTime80 += end - start;
            count = 0;
        }
        System.out.println("\n\n\t\t### Testing Two-Three Tree ###\n\n");
        System.out.println("Average time of 10000 adding element to two-three Tree " +  totalTime10/10 + " ms");
        System.out.println("Average time of 20000 adding element to two-three Tree " +  totalTime20/10 + " ms");
        System.out.println("Average time of 40000 adding element to two-three Tree " +  totalTime40/10 + " ms");
        System.out.println("Average time of 80000 adding element to two-three Tree " +  totalTime80/10 + " ms");

        ArrayList<BTree<Integer> > bTrees10 = new ArrayList<>();
        ArrayList<BTree<Integer> > bTrees20 = new ArrayList<>();
        ArrayList<BTree<Integer> > bTrees40 = new ArrayList<>();
        ArrayList<BTree<Integer> > bTrees80 = new ArrayList<>();
        count = 0;
        for(int i=0; i<10; ++i){
            bTrees10.add(new BTree<>(5));
            bTrees20.add(new BTree<>(5));
            bTrees40.add(new BTree<>(5));
            bTrees80.add(new BTree<>(5));
        }
        totalTime10=0;totalTime20=0;totalTime40=0;totalTime80=0;
        for(int i=0; i<10; ++i) {
            double start = System.currentTimeMillis();
            while (count < 10000) {
                int num = rand.nextInt();
                if(bTrees10.get(i).add(num))
                    ++count;
            }
            double end = System.currentTimeMillis();
            totalTime10 += end - start;
            count = 0;
            start = System.currentTimeMillis();
            while(count < 20000){
                int num = rand.nextInt();
                if(bTrees20.get(i).add(num))
                    ++count;
            }
            end = System.currentTimeMillis();
            totalTime20 += end - start;
            count = 0;
            start = System.currentTimeMillis();
            while(count < 40000){
                int num = rand.nextInt();
                if(bTrees40.get(i).add(num))
                    ++count;
            }
            end = System.currentTimeMillis();
            totalTime40 += end - start;
            count = 0;
            start = System.currentTimeMillis();
            while(count < 80000){
                int num = rand.nextInt();
                if(bTrees80.get(i).add(num))
                    ++count;
            }
            end = System.currentTimeMillis();
            totalTime80 += end - start;
            count = 0;
        }
        System.out.println("\n\n\t\t### Testing B-Tree Tree ###\n\n");
        System.out.println("Average time of 10000 adding element to B-Tree Tree " +  totalTime10/10 + " ms");
        System.out.println("Average time of 20000 adding element to B-Tree Tree " +  totalTime20/10 + " ms");
        System.out.println("Average time of 40000 adding element to B-Tree Tree " +  totalTime40/10 + " ms");
        System.out.println("Average time of 80000 adding element to B-Tree Tree " +  totalTime80/10 + " ms");



        ArrayList<SkipList<Integer> > skipList10 = new ArrayList<>();
        ArrayList<SkipList<Integer> > skipList20 = new ArrayList<>();
        ArrayList<SkipList<Integer> > skipList40 = new ArrayList<>();
        ArrayList<SkipList<Integer> > skipList80 = new ArrayList<>();
        count = 0;
        for(int i=0; i<10; ++i){
            skipList10.add(new SkipList<>());
            skipList20.add(new SkipList<>());
            skipList40.add(new SkipList<>());
            skipList80.add(new SkipList<>());
        }
        totalTime10=0;totalTime20=0;totalTime40=0;totalTime80=0;
        for(int i=0; i<10; ++i) {
            double start = System.currentTimeMillis();
            while (count < 10000) {
                int num = rand.nextInt();
                if(skipList10.get(i).add(num))
                    ++count;
            }
            double end = System.currentTimeMillis();
            totalTime10 += end - start;
            count = 0;
            start = System.currentTimeMillis();
            while(count < 20000){
                int num = rand.nextInt();
                if(skipList20.get(i).add(num))
                    ++count;
            }
            end = System.currentTimeMillis();
            totalTime20 += end - start;
            count = 0;
            start = System.currentTimeMillis();
            while(count < 40000){
                int num = rand.nextInt();
                if(skipList40.get(i).add(num))
                    ++count;
            }
            end = System.currentTimeMillis();
            totalTime40 += end - start;
            count = 0;
            start = System.currentTimeMillis();
            while(count < 80000){
                int num = rand.nextInt();
                if(skipList80.get(i).add(num))
                    ++count;
            }
            end = System.currentTimeMillis();
            totalTime80 += end - start;
            count = 0;
        }
        System.out.println("\n\n\t\t### Testing Skip List ###\n\n");
        System.out.println("Average time of 10000 adding element to Skip List " +  totalTime10/10 + " ms");
        System.out.println("Average time of 20000 adding element to Skip List " +  totalTime20/10 + " ms");
        System.out.println("Average time of 40000 adding element to Skip List " +  totalTime40/10 + " ms");
        System.out.println("Average time of 80000 adding element to Skip List " +  totalTime80/10 + " ms");

        totalTime10=0;totalTime20=0;totalTime40=0;totalTime80=0;
        for(int i=0; i<10; ++i) {
            double start = System.nanoTime();
            while (count < 100) {
                int num = rand.nextInt();
                if(bSearchTrees10.get(i).add(num))
                    ++count;
            }
            double end = System.nanoTime();
            totalTime10 += (end - start) / Math.pow(10,6);
            count = 0;
            start = System.nanoTime();
            while(count < 100){
                int num = rand.nextInt();
                if(bSearchTrees20.get(i).add(num))
                    ++count;
            }
            end = System.nanoTime();
            totalTime20 += (end - start) / Math.pow(10,6);
            count = 0;
            start = System.nanoTime();
            while(count < 100){
                int num = rand.nextInt();
                if(bSearchTrees40.get(i).add(num))
                    ++count;
            }
            end = System.nanoTime();
            totalTime40 += (end - start) / Math.pow(10,6);
            count = 0;
            start = System.nanoTime();
            while(count < 100){
                int num = rand.nextInt();
                if(bSearchTrees80.get(i).add(num))
                    ++count;
            }
            end = System.nanoTime();
            totalTime80 += (end - start) / Math.pow(10,6);
            count = 0;
        }
        System.out.println("\n\n\t\t## Testing to Insert 100 item ##\n ");
        System.out.printf("\nAfter inserting 100 item to Binary Search Tree(10000) %.4f ms\n",totalTime10);
        System.out.printf("After inserting 100 item to Binary Search Tree(20000) %.4f ms\n",totalTime20);
        System.out.printf("After inserting 100 item to Binary Search Tree(40000) %.4f ms\n",totalTime40);
        System.out.printf("After inserting 100 item to Binary Search Tree(80000) %.4f ms\n",totalTime80);

        totalTime10=0;totalTime20=0;totalTime40=0;totalTime80=0;
        for(int i=0; i<10; ++i) {
            double start = System.nanoTime();
            while (count < 100) {
                int num = rand.nextInt();
                if(redBlackTreesTree10.get(i).add(num))
                    ++count;
            }
            double end = System.nanoTime();
            totalTime10 += (end - start) / Math.pow(10,6);
            count = 0;
            start = System.nanoTime();
            while(count < 100){
                int num = rand.nextInt();
                if(redBlackTreesTree20.get(i).add(num))
                    ++count;
            }
            end = System.nanoTime();
            totalTime20 += (end - start) / Math.pow(10,6);
            count = 0;
            start = System.nanoTime();
            while(count < 100){
                int num = rand.nextInt();
                if(redBlackTreesTree40.get(i).add(num))
                    ++count;
            }
            end = System.nanoTime();
            totalTime40 += (end - start) / Math.pow(10,6);
            count = 0;
            start = System.nanoTime();
            while(count < 100){
                int num = rand.nextInt();
                if(redBlackTreesTree80.get(i).add(num))
                    ++count;
            }
            end = System.nanoTime();
            totalTime80 += (end - start) / Math.pow(10,6);
            count = 0;
        }
        System.out.printf("\nAfter inserting 100 item to Red Black Tree(10000) %.4f ms\n",totalTime10);
        System.out.printf("After inserting 100 item to Red Black Tree(20000) %.4f ms\n",totalTime20);
        System.out.printf("After inserting 100 item to Red Black Tree(40000) %.4f ms\n",totalTime40);
        System.out.printf("After inserting 100 item to Red Black Tree(80000) %.4f ms\n",totalTime80);

        totalTime10=0;totalTime20=0;totalTime40=0;totalTime80=0;
        for(int i=0; i<10; ++i) {
            double start = System.nanoTime();
            while (count < 100) {
                int num = rand.nextInt();
                if(two_threeTree10.get(i).add(num))
                    ++count;
            }
            double end = System.nanoTime();
            totalTime10 += (end - start) / Math.pow(10,6);
            count = 0;
            start = System.nanoTime();
            while(count < 100){
                int num = rand.nextInt();
                if(two_threeTree20.get(i).add(num))
                    ++count;
            }
            end = System.nanoTime();
            totalTime20 += (end - start) / Math.pow(10,6);
            count = 0;
            start = System.nanoTime();
            while(count < 100){
                int num = rand.nextInt();
                if(two_threeTree40.get(i).add(num))
                    ++count;
            }
            end = System.nanoTime();
            totalTime40 += (end - start) / Math.pow(10,6);
            count = 0;
            start = System.nanoTime();
            while(count < 100){
                int num = rand.nextInt();
                if(two_threeTree80.get(i).add(num))
                    ++count;
            }
            end = System.nanoTime();
            totalTime80 += (end - start) / Math.pow(10,6);
            count = 0;
        }
        System.out.printf("\nAfter inserting 100 item to Two-Three Tree(10000) %.4f ms\n",totalTime10);
        System.out.printf("After inserting 100 item to Two-Three Tree(20000) %.4f ms\n",totalTime20);
        System.out.printf("After inserting 100 item to Two-Three Tree(40000) %.4f ms\n",totalTime40);
        System.out.printf("After inserting 100 item to Two-Three Tree(80000) %.4f ms\n",totalTime80);

        totalTime10=0;totalTime20=0;totalTime40=0;totalTime80=0;
        for(int i=0; i<10; ++i) {
            double start = System.nanoTime();
            while (count < 100) {
                int num = rand.nextInt();
                if(bTrees10.get(i).add(num))
                    ++count;
            }
            double end = System.nanoTime();
            totalTime10 += (end - start) / Math.pow(10,6);
            count = 0;
            start = System.nanoTime();
            while(count < 100){
                int num = rand.nextInt();
                if(bTrees20.get(i).add(num))
                    ++count;
            }
            end = System.nanoTime();
            totalTime20 += (end - start) / Math.pow(10,6);
            count = 0;
            start = System.nanoTime();
            while(count < 100){
                int num = rand.nextInt();
                if(bTrees40.get(i).add(num))
                    ++count;
            }
            end = System.nanoTime();
            totalTime40 += (end - start) / Math.pow(10,6);
            count = 0;
            start = System.nanoTime();
            while(count < 100){
                int num = rand.nextInt();
                if(bTrees80.get(i).add(num))
                    ++count;
            }
            end = System.nanoTime();
            totalTime80 += (end - start) / Math.pow(10,6);
            count = 0;
        }
        System.out.printf("\nAfter inserting 100 item to B-Tree Tree(10000) %.4f ms\n",totalTime10);
        System.out.printf("After inserting 100 item to B-Tree Tree(20000) %.4f ms\n",totalTime20);
        System.out.printf("After inserting 100 item to B-Tree Tree(40000) %.4f ms\n",totalTime40);
        System.out.printf("After inserting 100 item to B-Tree Tree(80000) %.4f ms\n",totalTime80);

        totalTime10=0;totalTime20=0;totalTime40=0;totalTime80=0;
        for(int i=0; i<10; ++i) {
            double start = System.nanoTime();
            while (count < 100) {
                int num = rand.nextInt();
                if(skipList10.get(i).add(num))
                    ++count;
            }
            double end = System.nanoTime();
            totalTime10 += (end - start) / Math.pow(10,6);
            count = 0;
            start = System.nanoTime();
            while(count < 100){
                int num = rand.nextInt();
                if(skipList20.get(i).add(num))
                    ++count;
            }
            end = System.nanoTime();
            totalTime20 += (end - start) / Math.pow(10,6);
            count = 0;
            start = System.nanoTime();
            while(count < 100){
                int num = rand.nextInt();
                if(skipList40.get(i).add(num))
                    ++count;
            }
            end = System.nanoTime();
            totalTime40 += (end - start) / Math.pow(10,6);
            count = 0;
            start = System.nanoTime();
            while(count < 100){
                int num = rand.nextInt();
                if(skipList80.get(i).add(num))
                    ++count;
            }
            end = System.nanoTime();
            totalTime80 += (end - start) / Math.pow(10,6);
            count = 0;
        }
        System.out.printf("\nAfter inserting 100 item to Skip List(10000) %.4f ms\n",totalTime10);
        System.out.printf("After inserting 100 item to Skip List(20000) %.4f ms\n",totalTime20);
        System.out.printf("After inserting 100 item to Skip List(40000) %.4f ms\n",totalTime40);
        System.out.printf("After inserting 100 item to Skip List(80000) %.4f ms\n",totalTime80);







    }
    public static void whichTree(BinarySearchTree Tree){
        if(isAvl(Tree)){
            System.out.println("This is AVL Tree");
        }
        else
            System.out.println("This is not AVL Tree");

        if(isRedBlack(Tree)){
            System.out.println("This is Red-Black Tree");
        }
        else
            System.out.println("This is not Red-Black Tree");

    }
    public static boolean isAvl(BinarySearchTree tree){
        return isAvl(tree.root);
    }
    public static boolean isAvl(BinaryTree.Node Tree){
        if(Tree==null){
            return true;
        }
        int left = findHeight(Tree.left);
        int right = findHeight(Tree.right);

        if(Math.abs(left-right)<=1 && isAvl(Tree.left) && isAvl(Tree.right)){
            return true;
        }
        return false;
    }
    public static int findHeight(BinaryTree.Node aNode){
        if(aNode==null)
            return 0;
        return 1+Math.max(findHeight(aNode.left),findHeight(aNode.right));
    }
    public static int findHeightMin(BinaryTree.Node aNode){
        if(aNode==null)
            return 0;
        return 1+Math.min(findHeight(aNode.left),findHeight(aNode.right));
    }
    public static boolean isRedBlack(BinarySearchTree Tree){
        return isRedBlack(Tree.root);
    }
    public static boolean isRedBlack(BinaryTree.Node Tree){
        if(Tree==null){
            return true;
        }
        int min = Math.min(findHeightMin(Tree.left),findHeightMin(Tree.right))+1;
        int max = Math.max(findHeight(Tree.left),findHeight(Tree.right))+1;
        if(max<=min*2 && isAvl(Tree.left) && isAvl(Tree.right)){
            return true;
        }
        return false;
    }
}
