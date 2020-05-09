package eg.edu.alexu.csd.datastructure;

public class queue {
    Slinked s = new Slinked();

    public void enqueue(Object item){
        s.add(item);
    }

    public Object dequeue(){
        Object e = s.get(0);
        s.remove(0);
        return e ;
    }

    public boolean isEmpty(){
        return s.isEmpty();
    }

    public int size(){
        return s.size();
    }
}
