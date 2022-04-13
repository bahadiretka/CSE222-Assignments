/**
 * @author Bahadir Etka Kilinc
 * A special Binary Search Tree class that holds Max Heap nodes in.
 */
public class BKBSTHeapTree<E extends Comparable<E>>{

    private BSTNode<E> root;
    public BKBSTHeapTree(){
        root = null;
    }

    /**
     * enum structure for remove an item from tree
     */
    enum STATUS{
        LEFT,
        RIGHT,
        INVALID
    }
    /**
     * static inner Node class that holds a max heap for each node
     * @param <E> comparable generic type
     */
    private static class BSTNode<E extends Comparable<E>>{
        BKMaxBSTHeapNode<E> theData;
        BSTNode<E> left;
        BSTNode<E> right;

        /**
         * constructor for node with a generic item
         * @param item
         */
        BSTNode(BKMaxBSTHeapNode<E> item){
            theData = item;
            left = null;
            right = null;
        }
    }

    /**
     * Adds an item to binary search tree first checks all tree nodes if item is contained in the node
     * @param data item to be added
     * @return returns item's number of occurrences
     */
    public int add(E data){
        if(root == null){
            root = new BSTNode<>(new BKMaxBSTHeapNode<E>());
            return root.theData.add(data);
        }
        int num = searchTree(root,data);
        if(num == -1)
            return add(root,root,data);
        return num;
    }

    /**
     *  Checks all tree nodes if the item contained in the node
     * @param root root of tree
     * @param data item to be added
     * @return returns number of occurrences of item
     */
    private int searchTree(BSTNode<E> root, E data){
        if(root == null)
            return -1;
        if(root.theData.contains(data))
            return root.theData.add(data);
        int res = root.theData.peek().compareTo(data);
        if(res > 0)
            return searchTree(root.left,data);
        return searchTree(root.right,data);
    }

    /**
     * recursive add method
     * @param current current node
     * @param last last node
     * @param data item to added
     * @return item's number of occurrences
     */
    private int add(BSTNode<E> current, BSTNode<E> last, E data){
        if(current == null){
            int res = last.theData.peek().compareTo(data);
            if(res > 0){
                last.left = new BSTNode<>(new BKMaxBSTHeapNode<E>());
                return last.left.theData.add(data);
            }
            else{
                last.right = new BSTNode<>(new BKMaxBSTHeapNode<E>());
                return last.right.theData.add(data);
            }
        }
        if(current.theData.size() < 7){
            return current.theData.add(data);
        }
        else {
            int com = current.theData.peek().compareTo(data);
            if (com > 0) {
                return add(current.left, current, data);
            }
            else {
                return add(current.right, current, data);
            }
        }
    }

    /**
     * Removes an item from tree
     * @param data the item to be removed
     * @return returns deleted item's number of occurrences
     */
    public int remove(E data){
        if(root == null){
            return -1;
        }
        return remove(root,root, STATUS.INVALID,data);
    }

    /**
     * recursive remove method
     * @param current current node
     * @param last last node
     * @param sts holds the move after last
     * @param data item to be removed
     * @return returns deleted item's number of occurrences
     */
    private int remove(BSTNode<E> current, BSTNode<E> last, STATUS sts, E data){
        if(current == null){
            return -1;
        }
        if(current.theData.contains(data)){

            int res = current.theData.remove(data);
            if(current.theData.size() == 0){
                if(current.right != null) {
                    if(current.right.left == null){
                        if(sts == STATUS.RIGHT) {
                            last.right = current.right;
                            last.right.left = current.left;
                        }
                        if(sts == STATUS.LEFT){
                            last.left = current.right;
                            last.left.left = current.left;
                        }
                        return 0;
                    }
                    current.theData = helper(current.right, current.right);
                }
                else{
                    if(current.left != null){
                        if(sts == STATUS.LEFT)
                            last.left = current.left;
                        if(sts == STATUS.RIGHT)
                            last.right = current.left;
                        return 0;
                    }
                    if(sts == STATUS.LEFT)
                        last.left = null;
                    if(sts == STATUS.RIGHT)
                        last.right = null;
                }
                return 0;
            }
            return res;
        }
        int res = current.theData.peek().compareTo(data);
        if(res > 0){
            return remove(current.left,current, STATUS.LEFT,data);
        }
        else{
            return remove(current.right,current, STATUS.RIGHT,data);
        }
    }

    /**
     * recursive helper method which brings biggest element of down tree
     * @param current current node
     * @param last last node
     * @return returns biggest element of down tree
     */
    private BKMaxBSTHeapNode<E> helper(BSTNode<E> current, BSTNode<E> last){
        if(current.left == null){
            last.left = null;
            return current.theData;
        }
        return helper(current.left,current);
    }

    /**
     * Find method that gets how many of the given element is contained in the tree
     * @param item item to be found
     * @return returns how many of the given element is contained in the tree
     */
    public int find(E item){
        return find(root,item);
    }

    /**
     * recursive find method
     * @param root root of tree
     * @param item item to be searched
     * @return returns how many of the given element is contained in the tree
     */
    private int find(BSTNode<E> root, E item){
        if(root == null){
            return -1;
        }
        if(root.theData.contains(item)){
            return root.theData.find(item);
        }
        int res = root.theData.peek().compareTo(item);
        if(res > 0){ // current data bigger than to be searched data.
            return find(root.left,item);
        }
        else{
            return find(root.right,item);
        }
    }

    /**
     * Finds the most repeating element in the tree and returns
     * @return returns most repeating element in the tree
     */
    public E findMode(){
        BKMaxBSTHeapNode.Node<E> result = findMode(root,new BKMaxBSTHeapNode.Node<E>(null,0));
        return result.getData();
    }

    /**
     *
     * @param root root of tree
     * @param max last most repeating element
     * @return returns most repeated max heap's node
     */
    private BKMaxBSTHeapNode.Node<E> findMode(BSTNode<E> root, BKMaxBSTHeapNode.Node<E> max){
        if(root == null){
            return max;
        }
        BKMaxBSTHeapNode.Node<E> temp = root.theData.findMode();
        if(max.getOccur() < temp.getOccur())
            max = temp;
        return max(findMode(root.left,max),findMode(root.right,max));
    }

    /**
     * compares occurrences of two max heap's node
     * @param left first node
     * @param right second node
     * @return returns the node with a larger number of occurrences
     */
    private BKMaxBSTHeapNode.Node<E> max(BKMaxBSTHeapNode.Node<E> left, BKMaxBSTHeapNode.Node<E> right){
        return left.getOccur() > right.getOccur() ? left : right;
    }

    /**
     * String representation of tree with preorder traversal
     * @return returns representation of tree with preorder traversal
     */
    public String toString(){
        return toString(root);
    }

    /**
     * recursive method for wrapper to String
     * @param root root of tree
     * @return returns each tree node representation
     */
    private String toString(BSTNode<E> root){
        if(root == null){
            return "";
        }
        return root.theData + "\n" + toString(root.left) + toString(root.right);
    }
}
