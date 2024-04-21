
public class Main {
    public static void main(String[] args) {
        //SetOfStacks();
        //AnimalQueue();
        BinaryTree tree = new BinaryTree(5);
        tree.insert("A");
        tree.insert("B");
        tree.insert("C");
        tree.insert("D");
        tree.insert("E");
        tree.preOrder(1);
        tree.inOrder(1);
        tree.postOrder(1);
        tree.search("B");
        tree.delete("B");
        tree.preOrder(1);
    }

    public static void SetOfStacks(){
        SetOfStacks sos = new SetOfStacks(3);
        sos.push(1);
        sos.push(2);
        sos.push(3);
        sos.push(4);
        sos.push(5);
        int val = sos.popAt(0);
        int val1 = sos.popAt(1);
    }

    public static void AnimalQueue(){
        AnimalQueue animals = new AnimalQueue();
        animals.enqueue(new Cat("Kiki"));
        animals.enqueue(new Cat("Kari"));
        animals.enqueue(new Dog("Beji"));
        animals.enqueue(new Cat("Reki"));
        animals.enqueue(new Dog("Dexter"));

        String name = animals.dequeueAny().name();
        name = animals.dequeueDogs().name();
        name = animals.dequeueCats().name();
    }
}