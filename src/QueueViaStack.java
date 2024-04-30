import java.util.Stack;

public class QueueViaStack {
    Object[] queue;
    public QueueViaStack(Stack stack1, Stack stack2) {
        for (int i = 0; i < stack1.size(); i++){
            stack2.push(stack1.pop());
        }
        Enqueue(stack2);
    }

    public void Enqueue(Stack stack) {
        int size = stack.size();
        queue = new Object[size];
        for(int i = 0; i < size; i++){
            queue[i] = stack.pop();
        }

    }
}

