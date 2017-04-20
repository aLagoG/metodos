package delag.metodosnumericos;

public class Pair<U,V> {
    private U first;
    private V second;

    public Pair(U f, V s){
        first = f;
        second = s;
    }

    public U getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }
}
