import java.util.ArrayList;
import java.util.EmptyStackException;

public class SetOfStacks {
    ArrayList<Stack> stacks = new ArrayList<Stack>();
    int max;
    public SetOfStacks(int max){
        this.max = max;
        Stack stack = new Stack(max);
        stacks.add(stack);
    }

    public void push(int value){
        int size = stacks.size()-1;
        Stack stack = stacks.get(size);
        if(stack.isFull()){
            Stack newStack = new Stack(max);
            newStack.push(value);
            stacks.add(newStack);
        }
        else
            stack.push(value);
    }

    public int popAt(int idx){
        int res=0;
        if(stacks.get(idx).size > 1){
            Stack stack = stacks.get(idx);
            int size = stack.size;
            if(size == max){
                res = stack.pop();
                Stack newStack = stacks.get(1);
                stack.push(newStack.removeBottom());
            }
        }
        else{
            Stack stack = stacks.get(idx);
            res = stack.pop();
        }
        return res;
    }
}

