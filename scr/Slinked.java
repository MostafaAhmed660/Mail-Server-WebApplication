package eg.edu.alexu.csd.datastructure;

public class Slinked {
    SNode head ;
    public int size ;

    public static class SNode {
        Object data ;
        SNode next ;
        SNode(Object data){
            this.data = data ;
        }
    }

    public void add(int index,Object element){
        if (index > size - 1 || index < 0)
            throw new ArithmeticException("");

        else {
            size++;
            SNode mynode = new SNode(element);
            if (index == 0) {
                mynode.next = head;
                head = mynode;
            } else {
                SNode n = head;
                for (int i = 0; i < index - 1; i++) {
                    n = n.next;
                }
                mynode.next = n.next;
                n.next = mynode;
            }
        }
    }

    public void add(Object element){
        size++;
        SNode mynode = new SNode(element);
        if (head == null){
            head = mynode ;
        }
        else{
            SNode n = head ;
            while (n.next != null)
                n=n.next;
            n.next = mynode ;
            mynode.next = null;
        }
    }

    public Slinked sublist(int fromIndex, int toIndex){
        Slinked mylist = new Slinked();
        if (toIndex > size-1 || fromIndex > toIndex )
            throw new ArithmeticException("");

        else {
            SNode n = head;
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
        SNode node = head ;
        while (node!=null){
            System.out.print(node.data + " ");
            node = node.next ;
        }
    }

    public Object get(int index) {
        if (index > size - 1 || index < 0)
            throw new ArithmeticException("");

        else {
            SNode n = head;
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
            SNode n = head;
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
            SNode n = head;
            for (int i = 0; i < index-1; i++)
                n = n.next;
            n.next = (n.next).next;
        }
    }

    public int size(){
        return size ;
    }

    public boolean contains(Object o){
        SNode n = head ;
        while (n != null){
            if (n.data == o)
                return true ;
            n = n.next;
        }
        return false ;
    }
}
