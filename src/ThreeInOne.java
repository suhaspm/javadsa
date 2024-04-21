public class ThreeInOne {
    private int numberOfStacks = 3;
    private int stackCapacity;
    private int[] values;
    private int[] sizes;

    public ThreeInOne(int stackSize) {
        values = new int[stackSize];
        sizes = new int[stackSize];
        stackCapacity = 0;
    }

    // isFull
    public boolean isFull(int stackNum) {
        return values[stackNum*numberOfStacks+stackCapacity] == 1;
    }

    // isEmpty
    public boolean isEmpty(int stackNum) {
        return values[stackNum*numberOfStacks+stackCapacity] == 0;
    }

    // indexOfTop - this is helper method for push, pop and peek methods

    private int indexOfTop(int stackNum) {
        return stackNum*numberOfStacks+stackCapacity;
    }

    // push
    public void push(int stackNum, int value) {
        values[stackNum*numberOfStacks+stackCapacity] = value;
        sizes[stackNum*numberOfStacks+stackCapacity]++;
    }

    // pop
    public int pop(int stackNum) {
        int value = values[stackNum*numberOfStacks+stackCapacity];
        sizes[stackNum*numberOfStacks+stackCapacity]--;
        return value;
    }

    // peek

    public int peek(int stackNum) {
        return values[stackNum*numberOfStacks+stackCapacity];
    }
}
