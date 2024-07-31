public class Pair<K, V> {

    private K element0;
    private V element1;
    int timestamp;
    String val;
    public static <K, V> Pair<K, V> createPair(K element0, V element1) {
        return new Pair<K, V>(element0, element1);
    }

    public Pair(K element0, V element1) {
        this.element0 = element0;
        this.element1 = element1;
    }

    public Pair(int timestamp, String val) {
        this.timestamp = timestamp;
        this.val = val;
    }

    public K getKey() {
        return element0;
    }

    public V getValue() {
        return element1;
    }

}