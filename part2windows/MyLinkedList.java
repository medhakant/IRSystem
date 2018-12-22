//Java Class for linked list
public class MyLinkedList<X> {
    public Node<X> head;

    static class Node<X>{ //linked list node
        Node<X> next;
        X data;

        Node(X d){ //constructor class
            data = d;
            next = null;
        }
    }

    static class TreeNode<X>{ //linked list Tree node
        TreeNode<X> left;
        TreeNode<X> right;
        TreeNode<X> parent;
        int NodeHeight;
        X data;

        TreeNode(X d){ //constructor class
            this.data = d;
            this.left = null;
            this.right = null;
            this.parent = null;
            this.NodeHeight = 0;
        }
    }

//    public static  void main(String[] args){
//        MyLinkedList<Integer> a = new MyLinkedList();
//        Node<Integer> x =new Node<>(23);
//        a.head = x;
//        System.out.println(a.head.data);
//    }
}
