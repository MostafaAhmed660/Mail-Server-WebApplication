package eg.edu.alexu.csd.datastructure;

public class Dlinked {
    DNode head ;
    public int size ;

    public static class DNode {
        Object data ;
        DNode next ;
        DNode prev ;
        DNode(Object data){
            this.data = data ;
        }
    }

    public void add(int index,Object element){
        if (index > size - 1 || index < 0)
            throw new ArithmeticException("");

        else {
            size++;
            DNode mynode = new DNode(element);
            if (index == 0) {
                mynode.next = head;
                head.prev = mynode;
                head = mynode;
            } else {
                DNode n = head;
                for (int i = 0; i < index - 1; i++) {
                    n = n.next;
                }
                mynode.next = n.next;
                (n.next).prev = mynode;
                n.next = mynode ;
                mynode.prev = n ;
            }
        }
    }

    public void add(Object element){
        size++;
        DNode mynode = new DNode(element);
        if (head == null){
            head = mynode ;
        }
        else{
            DNode n = head ;
            while (n.next != null)
                n=n.next;
            n.next = mynode ;
            mynode.prev = n;
        }
    }

    public Dlinked sublist(int fromIndex, int toIndex){
        Dlinked mylist = new Dlinked();
        if (toIndex > size-1 || fromIndex > toIndex )
            throw new ArithmeticException("");

        else {
            DNode n = head;
            for (int i = 0; i < fromIndex; i++)
                n = n.next;
            for (int i = fromIndex; i <= toIndex; i++) {
                mylist.add(n.data);
                n = n.next;
            }
        }
        return mylist ;
    }

    public void showall(){
        DNode node = head ;
        while (node!=null){
            System.out.print(node.data+" ");
            node = node.next ;
        }
    }

    public Object get(int index) {
        if (index > size - 1 || index < 0)
            throw new ArithmeticException("");

        else {
            DNode n = head;
            for (int i = 0; i < index; i++) {
                n = n.next;
            }
            return n.data;

        }
    }

    public void set(int index,Object element) {
        if (index > size - 1 || index < 0)
            throw new ArithmeticException("");

        else {
            DNode n = head;
            for (int i = 0; i < index; i++) {
                n = n.next;
            }
            n.data = element;
        }
    }

    public void clear(){
        head = null ;
        size = 0 ;
    }

    public boolean isEmpty(){
        return (head == null);
    }

    public void remove(int index){
        if (index > size -1 || index < 0)
            throw new ArithmeticException("");

        else if (index == 0){
            size--;
            head = head.next;
        }
        else {
            size--;
            DNode n = head;
            for (int i = 0; i < index-1; i++)
                n = n.next;
            n.next = (n.next).next;
            if (n.next != null)
                (n.next).prev = n ;
        }
    }

    public int size(){
        return size ;
    }

    public boolean contains(Object o){
        DNode n = head ;
        while (n != null){
            if (n.data == o)
                return true ;
            n = n.next;
        }
        return false ;
    }
}
