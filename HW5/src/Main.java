public class Main {
    public static void main(String [] args){

        System.out.println("\t\t\t## Part1 ##\n\n");
        BKHashMap<String,Integer> map = new BKHashMap<>();
        map.put("Fleur",1);
        map.put("Alfred",2);
        map.put("Franz",3);
        map.put("Viktor",4);
        map.put("Martha",5);
        map.put("Frantisek",6);
        map.put("Josef",7);
        map.put("Riedl",8);
        map.put("Rudolf",9);
        map.put("Theodor",10);

        MapIteratorInterface iter = map.iterator();
        System.out.println("Testing iterator with no parameter\n");
        while(iter.hasNext()){
            System.out.println(iter.next());
        }
        System.out.println("\n");
        iter = map.iterator("Riedl");
        System.out.println("Testing iterator with starting key Riedl\n");
        while(iter.hasNext()){
            System.out.println(iter.next());
        }
        System.out.println("\n");
        System.out.println("Testing prev method\n");
        System.out.println(iter.prev());
        System.out.println(iter.prev());

        System.out.println("\t\t\t## Part 2\n\n");

        System.out.println("\tTesting Part2.3\n");
        HashTableOpen<Integer,Integer> openTable = new HashTableOpen<>();

        openTable.put(3,1);
        openTable.put(12,2);
        openTable.put(13,3);
        openTable.put(25,4);
        openTable.put(23,5);
        openTable.put(51,6);
        openTable.put(42,7);

        System.out.println("\tSome keys are added to table\n");
        System.out.println(openTable);

        System.out.println("Deleting 13 from table\n");
        openTable.remove(13);
        System.out.println(openTable);

        System.out.println("\nGetting a key(23) which is contained");
        System.out.println("Its value: " + openTable.get(23) + "\n");

        System.out.println("\nTry to delete a key(65) which is not contained");
        System.out.println(openTable.remove(65) + "\n");

        System.out.println("\nTry to put a key(3) and new value(15) which is contained");
        System.out.println("Old Value: " + openTable.put(3,15) + "\n");
        System.out.println(openTable);

        System.out.println("\tTesting Part2.1\n");

        HashTableChain<Integer,Integer> chainTable = new HashTableChain<>();

        chainTable.put(3,1);
        chainTable.put(12,2);
        chainTable.put(13,3);
        chainTable.put(25,4);
        chainTable.put(23,5);
        chainTable.put(51,6);
        chainTable.put(42,7);

        System.out.println("\tSome keys are added to table\n");
        System.out.println(chainTable);

        System.out.println("Deleting 13 from table\n");
        chainTable.remove(13);
        System.out.println(chainTable);

        System.out.println("\nGetting a key(23) which is contained");
        System.out.println("Its value: " + chainTable.get(23) + "\n");

        System.out.println("\nTry to delete a key(65) which is not contained");
        System.out.println(chainTable.remove(65) + "\n");

        System.out.println("\nTry to put a key(3) and new value(15) which is contained");
        System.out.println("Old Value: " + chainTable.put(3,15) + "\n");
        System.out.println(chainTable);



        System.out.println("\tTesting Part2.2\n");

        BKHashTree<Integer,Integer> treeTable = new BKHashTree<>();

        treeTable.put(3,1);
        treeTable.put(12,2);
        treeTable.put(13,3);
        treeTable.put(25,4);
        treeTable.put(23,5);
        treeTable.put(51,6);
        treeTable.put(42,7);

        System.out.println("\tSome keys are added to table\n");
        System.out.println(treeTable);

        System.out.println("Deleting 13 from table\n");
        treeTable.remove(13);
        System.out.println(treeTable);

        System.out.println("\nGetting a key(23) which is contained");
        System.out.println("Its value: " + treeTable.get(23) + "\n");

        System.out.println("\nTry to delete a key(65) which is not contained");
        System.out.println(treeTable.remove(65) + "\n");

        System.out.println("\nTry to put a key(3) and new value(15) which is contained");
        System.out.println("Old Value: " + treeTable.put(3,15) + "\n");
        System.out.println(treeTable);

        System.out.println("\n\tTesting three class with big numbers\n\n");

        for (int i = 0; i < 10000; ++i)
            openTable.put(i, i * i);
        for (int i = 0; i < 10000; ++i)
            openTable.remove(i);
        System.out.println(openTable);

        for (int i = 0; i < 10000; ++i)
            chainTable.put(i, i * i);
        for (int i = 0; i < 10000; ++i)
            chainTable.remove(i);
        System.out.println(chainTable);

        for (int i = 0; i < 10000; ++i)
            treeTable.put(i, i * i);
        for (int i = 0; i < 10000; ++i)
            treeTable.remove(i);
        System.out.println(treeTable);
    }

}
