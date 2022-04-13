import java.util.Comparator;
import java.util.Random;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args){

        System.out.println("\t\t## Part 1 ##");

        Random randomGenerator = new Random();
        BKPriorityQueue<Integer> priorityQueue = new BKPriorityQueue<>();

        priorityQueue.offer(10);
        priorityQueue.offer(20);
        priorityQueue.offer(30);
        priorityQueue.offer(50);
        priorityQueue.offer(15);
        priorityQueue.offer(35);
        priorityQueue.offer(25);

        System.out.println("After adding some Integers");
        System.out.println(priorityQueue);
        System.out.println("Searching an element from Priority Queue which is contained");
        System.out.println(priorityQueue.search(10));
        System.out.println("Searching an element from Priority Queue which is NOT contained");
        System.out.println(priorityQueue.search(12));

        BKPriorityQueue<Integer> priorityQueue1 = new BKPriorityQueue<>();

        priorityQueue1.offer(60);
        priorityQueue1.offer(70);
        priorityQueue1.offer(80);
        priorityQueue1.offer(110);
        priorityQueue1.offer(100);
        priorityQueue1.offer(90);
        priorityQueue1.offer(85);

        System.out.println("Other priority queue created for merging");
        System.out.println(priorityQueue1);

        System.out.println("\nBefore merging queue2 to queue1");
        System.out.println("\nqueue 1\n"+priorityQueue);
        System.out.println("queue 2\n"+priorityQueue1);

        priorityQueue.merge(priorityQueue1);
        System.out.println("\nAfter merging\n");
        System.out.println("queue 1\n"+priorityQueue);
        System.out.println("queue 2\n"+priorityQueue1);

        System.out.println("\nRemoving fifth of largest element from queue 1");
        priorityQueue.removeLargestOf(5);
        System.out.println("queue 1\n"+priorityQueue);

        System.out.println("\nTesting queue's iterator with the set method");
        System.out.println("100 will be set with 14");

        BKIterator iter = priorityQueue.iterator();

        while(iter.hasNext()){
            if(iter.next().equals(100)){
                iter.set(14);
            }
        }
        System.out.println("queue 1\n"+priorityQueue);

        System.out.println("\n\n\t\t\t## Part 2 ##\n");


        ArrayList<Integer> randomNumbers = new ArrayList<>();

        for(int i=0; i<50; ++i){
            int num = randomGenerator.nextInt(50);
            randomNumbers.add(num);
        }
        BKBSTHeapTree<Integer> heapTree = new BKBSTHeapTree<>();

        for(int i=0; i<50; ++i){
            heapTree.add(randomNumbers.get(i));
        }

        System.out.println("\nMax Heap Binary Search Tree\n");
        System.out.println(heapTree);
        IntegerComparator<Integer> comparator = new IntegerComparator<>();
        randomNumbers.sort(comparator);
        System.out.println("\nSorted ArrayList\n");
        System.out.println(randomNumbers);

        int arrayMode = findRandomsMode(randomNumbers);
        int heapMode = heapTree.findMode();

        System.out.println("\nTree's Mode is " + heapMode);
        System.out.println("Random Number's Mode is " + arrayMode);

        System.out.println("So the find mode methods work correctly for both array list and tree, but when the orders are different, they cannot find the modes the same every time.");

        System.out.println("Searching 10 numbers in the tree\n");
        int randIndex;
        for(int i=0; i<10; ++i){
            randIndex = randomGenerator.nextInt(50);
            System.out.printf("Searching %d in the tree\n",randomNumbers.get(randIndex));
            System.out.printf("%d times found\n",heapTree.find(randomNumbers.get(randIndex)));
        }
        System.out.println("\nSearching three elements in tree which are not contained\n");
        int randNum;
        for(int i=0; i<3; ++i){
            randNum = randomGenerator.nextInt(50) + 50;
            System.out.printf("Searching %d in the tree\n",randNum);
            int occ = heapTree.find(randNum);
            if(occ == -1)
                System.out.println("Item is not contained.");
            else
                System.out.printf("%d times found\n",heapTree.find(randNum));
        }
        System.out.println("\nBefore deleting elements from tree\n");
        System.out.println(heapTree);
        System.out.println("\nRemoving 10 elements from tree which are contained\n");
        for(int i=0; i<10; ++i){
            randIndex = randomGenerator.nextInt(50);
            System.out.printf("%d will be deleted from tree\n",randomNumbers.get(randIndex));
            System.out.printf("There are %d occurrences left of %d\n",heapTree.remove(randomNumbers.get(randIndex)),randomNumbers.get(randIndex));
        }
        System.out.println("\nTry to delete three elements in tree which are not contained\n");

        for(int i=0; i<3; ++i){
            randNum = randomGenerator.nextInt(50) + 50;
            System.out.printf("removing %d in the tree\n",randNum);
            int occ = heapTree.remove(randNum);
            if(occ == -1)
                System.out.printf("Item could not delete from tree because %d is not contained.\n",randNum);
            else
                System.out.printf("There are %d occurrences left of %d\n",heapTree.remove(randNum),randNum);
        }
        System.out.println("\nAfter deletion items from Tree\n");
        System.out.println(heapTree);
    }
    public static int findRandomsMode(ArrayList<Integer> list){
        // initially the most repeated item is first one
        int mostRepeated,count=0,last,max = 0;
        last = list.get(0);
        mostRepeated = list.get(0);
        for(int i=0; i<list.size(); i++){
            if(last != list.get(i) || i == list.size()-1){
                if(count > max){
                    mostRepeated = list.get(i-1);
                    max = count;
                }
                last = list.get(i-1);
                count = 0;
            }
            ++count;
        }
        return mostRepeated;
    }
}
