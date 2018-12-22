import java.util.ArrayList;

public class MySort<Sortable> {

    //Given a set of Sortable objects, this method returns a sorted list of objects. The list is represented as Java's
    //ArrayList where the following relation holds: if a<b, sortedlist.get(a).getNumber() >= sortedlist.get(b).getNumber().
    //You can implement any sorting algorithm that you want. Your SearchEngine class should use the MySort class to sort the set of
    //pages on the basis of the relevance criteria.
    ArrayList<Sortable> sortThisList(MySet<Sortable> listOfSortableEntries){
        ArrayList<Sortable> sortedList = new ArrayList<>();
        if(!listOfSortableEntries.IsEmpty()){
            MyLinkedList.Node<Sortable> node = listOfSortableEntries.llist.head;
            sortedList.add(node.data);
            node=node.next;
            int i=1;
            while (node!=null) {
                MyLinkedList.Node<Sortable> key = node;
                int iterator = i-1;
                while ( (iterator > -1) && (((SearchResult)sortedList.get(iterator)).compareTo((SearchResult) key.data))==-1 ) {
                    iterator--;
                }
                sortedList.add(iterator+1,key.data);
                node=node.next;
                i++;
            }
            return sortedList;
        }else
            return sortedList;
    }

//    public static void main(String args[]){
//        MySet<SearchResult> set = new MySet<>();
//        PageEntry page = new PageEntry("stack_datastructure_wiki");
//        SearchResult x = new SearchResult(page,1);
//        SearchResult y = new SearchResult(page,2);
//        set.addElement(x);
//        set.addElement(y);
//        MySort<SearchResult> sort = new MySort<>();
//        System.out.println(sort.sortThisList(set).get(0).relevance+"and"+sort.sortThisList(set).get(1).relevance);
//    }
}
