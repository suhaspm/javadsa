import java.util.List;

public class NAry {
    public int val;
    public List<NAry> children;

    public NAry() {}

    public NAry(int _val) {
        val = _val;
    }

    public NAry(int _val, List<NAry> _children) {
        val = _val;
        children = _children;
    }
}
