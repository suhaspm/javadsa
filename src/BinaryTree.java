public class BinaryTree {
    String[] tree;
    int lastUsedIndex;

    public BinaryTree(int size){
        tree = new String[size+1];
        lastUsedIndex = 0;
    }

    boolean isFull(){
        return lastUsedIndex == tree.length-1;
    }

    public void insert(String value){
        if(isFull()) { System.out.println("The tree is full");  return;}

        tree[lastUsedIndex+1] = value;
        lastUsedIndex++;
        System.out.println("The value " + value + " has been inserted to the tree");
    }

    public void preOrder(int index){
        if(index > lastUsedIndex) return;
        System.out.println(tree[index] + " ");
        preOrder(index*2);
        preOrder(index*2+1);
    }

    public void inOrder(int index){
        if(index > lastUsedIndex) return;
        inOrder(index*2);
        System.out.println(tree[index] + " ");
        inOrder(index*2+1);
    }

    public void postOrder(int index){
        if(index > lastUsedIndex) return;
        postOrder(index*2);
        postOrder(index*2+1);
        System.out.println(tree[index] + " ");
    }

    // Search method
    public int search(String value) {
        for (int i = 1; i<=lastUsedIndex; i++) {
            if (tree[i] == value) {
                System.out.println(value+" exists at the location: " + i);
                return i;
            }
        }
        System.out.println("The value does not exist in BT");
        return -1;
    }

    // Delete Method
    public void delete(String value) {
        int location = search(value);
        if (location == -1) {
            return;
        } else {
            tree[location] = tree[lastUsedIndex];
            lastUsedIndex--;
            System.out.println("The node successfully deleted");
        }
    }

    public void deleteBinaryTree(){
        tree = null;
    }
}
