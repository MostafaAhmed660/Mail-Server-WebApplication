package eg.edu.alexu.csd.datastructure;

public class Pqueue implements IPriorityQueue {

    Slinked pqueue = new Slinked();
    Slinked pqueuekey = new Slinked();

    public void insert(Object item,int key) {
        if (pqueue.isEmpty()) {
            pqueue.add(item);
            pqueuekey.add(key);
        }

        else{
            if (key > (int)pqueuekey.get(pqueuekey.size -1)){
                pqueue.add(item);
                pqueuekey.add(key);
                return;
            }
            int n = 0 ;
            while (key >= (int)pqueuekey.get(n) && n != (pqueue.size)-1){
                n++;
            }
            pqueue.add(n,item);
            pqueuekey.add(n,key);
        }

    }

    public void show(){
        pqueue.showall();
    }

    public Object removeMin(){
        Object e = pqueue.get(pqueue.size-1);
        pqueue.remove(pqueue.size-1);
        pqueuekey.remove(pqueuekey.size-1);
        return e ;
    }

    public Object min() {
        return pqueue.get(pqueue.size-1);
    }

    public boolean isEmpty(){
        return pqueue.isEmpty();
    }

    public int size(){
        return pqueue.size;
    }
}
