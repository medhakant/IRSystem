public class MySet<X> {

    MyLinkedList<X> llist = new MyLinkedList();

    public  Boolean IsEmpty(){
        if (llist.head==null)
            return true;
        else
            return false;
    }

    public  Boolean IsMember(X e){
        MyLinkedList.Node<X> n =llist.head;
        while (n != null)
        {
            if(n.data.equals(e))
                return true;
            n=n.next;
        }
        return false;

    }

    //Add element to the set.
    void addElement(X element){
        MyLinkedList.Node<X> nrear =llist.head;
        if (nrear!=null){
            MyLinkedList.Node<X> nfront =nrear.next;
            while (nfront!=null){
                if (nrear.data.equals(element))
                    return;
                nfront=nfront.next;
                nrear=nrear.next;
            }
            if(!nrear.data.equals(element)){
                MyLinkedList.Node<X> newNode = new MyLinkedList.Node(element);
                nrear.next=newNode;
            }
        }
        else
            llist.head =new MyLinkedList.Node(element);
    }

    //Return MySet which represents a union of the current set and the otherSet.
    MySet<X> union(MySet<X> otherSet){
        MySet<X> u = new MySet();
        MyLinkedList.Node<X> n = llist.head;
        while (n!=null){
            u.addElement(n.data);
            n=n.next;
        }
        n = otherSet.llist.head;
        while (n!=null){
            u.addElement(n.data);
            n=n.next;
        }
        return u;
    }

    //Return MySet which represents an intersection of the current set and the otherSet.
    MySet<X> intersection(MySet<X> otherSet){
        MySet<X> i = new MySet();
        MyLinkedList.Node<X> n = otherSet.llist.head;
        while (n!=null){
            if (IsMember(n.data)==true)
                i.addElement(n.data);

            n=n.next;
        }
        return i;
    }

    MySet<X> subtract(MySet<X> otherSet){
        MySet<X> i = new MySet();
        MyLinkedList.Node<X> n = llist.head;
        while (n!=null){
            if (otherSet.IsMember(n.data)==false)
                i.addElement(n.data);

            n=n.next;
        }
        return i;
    }

    public static void main(String[] args)
    {

        MySet<Integer> m=new MySet();
        m.addElement(5);
        m.addElement(6);
        m.addElement(7);
        m.addElement(8);

        MySet<Integer> a = new MySet();
        a.addElement(1);
        a.addElement(2);
        a.addElement(3);
        a.addElement(5);

        MyLinkedList.Node n = m.subtract(a).llist.head;
        while (n!=null){
            System.out.println(n.data);
            n=n.next;
        }

        return ;
    }

}
