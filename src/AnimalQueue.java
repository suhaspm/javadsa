import java.util.LinkedList;

public class AnimalQueue {
    private LinkedList<Dog> dogs = new LinkedList<Dog>();
    private LinkedList<Cat> cats = new LinkedList<Cat>();
    private int index = 0;

    public void enqueue(Animal animal){
        animal.setOrder(index++);
        if(animal instanceof Dog)
            dogs.addLast((Dog)animal);
        else if(animal instanceof Cat)
            cats.addLast((Cat)animal);
    }

    public Animal dequeueAny(){
        Dog dog = dogs.peek();
        Cat cat = cats.peek();
        if(dog == null || cat.isOlderThan(dog)) return dequeueCats();
        else if (cat==null || dog.isOlderThan(cat)) return dequeueDogs();

        return null;
    }

    public Animal dequeueDogs(){
        return dogs.poll();
    }

    public Animal dequeueCats(){
        return cats.poll();
    }
}
