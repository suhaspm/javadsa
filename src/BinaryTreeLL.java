import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class BinaryTreeLL {
    BinaryNode root;

    public BinaryTreeLL(BinaryNode root){
        this.root = root;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public void insert(int value){
        BinaryNode newNode = new BinaryNode();
    }

    //Preorder traversal
    public void PreOrder(BinaryNode node){
        if(node == null)
            return;
        System.out.println(node.value);
        PreOrder(node.left);
        PreOrder(node.right);
    }

    public void InOrder(BinaryNode node){
        if(node == null) return;
        InOrder(node.left);
        System.out.println(node.value);
        InOrder(node.right);
    }

    public void PostOrder(BinaryNode node){
        if(node == null) return;
        PostOrder(node.left);
        PostOrder(node.right);
        System.out.println(node.value);
    }

    public void LevelOrder(){
        Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
        queue.add(root);
        while(!queue.isEmpty()){
            BinaryNode node = queue.remove();
            System.out.println(node.value);
            if(node.left != null)
                queue.add(node.left);
            if(node.right != null)
                queue.add(node.right);
        }
    }

    public void Search(String value){
        Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
        queue.add(root);
        while(!queue.isEmpty()){
            BinaryNode node = queue.remove();
            if(Objects.equals(node.value, value)) {
                System.out.println("The value " + value + " found");
                return;
            }
            if(node.left != null)
                queue.add(node.left);
            if(node.right != null)
                queue.add(node.right);
        }
        System.out.println("The value not found in the tree");
    }

    public void insert(String value){
        Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
        BinaryNode newNode = new BinaryNode();
        newNode.value = value;
        if(root == null){
            root = newNode;
            return;
        }
        queue.add(root);
        while(!queue.isEmpty()){
            BinaryNode node = queue.remove();
            if(node.left == null){
                node.left = newNode;
                break;
            }
            else if(node.right == null){
                node.right = newNode;
                break;
            }
            queue.add(node.left);
            queue.add(node.right);
        }
    }
}
