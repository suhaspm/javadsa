import java.util.ArrayList;

public class TreeNode_Old {
    String data;
    ArrayList<TreeNode_Old> children;

    public TreeNode_Old(String data){
        this.data = data;
        children = new ArrayList<>();
    }

    public void addChild(TreeNode_Old node){
        this.children.add(node);
    }

    public String print(int level){
        String res;
        res = " ".repeat(level)+data+"\n";
        for (TreeNode_Old node:this.children)
            res += node.print(level+1);
        return res;
    }
}
