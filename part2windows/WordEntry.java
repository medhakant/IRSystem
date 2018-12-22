public class WordEntry {
    public String word;
    private double idf;
    public MySet<Position> wordPosition;

    //Constructor method. The argument is the word for which we are creating the word entry.
    WordEntry(String word){
        this.word = word;
        this.wordPosition = new MySet<>();
    }

    //Add a position entry for str.
    void addPosition(Position position){
        wordPosition.addElement(position);
    }

    //Add multiple position entries for str.
    void addPositions(MyLinkedList<Position> positions){
        MyLinkedList.Node<Position> node = positions.head;
        while (node!=null){
            wordPosition.addElement(node.data);
            node = node.next;
        }
    }

    //Return a linked list of all position entries for str.
    MyLinkedList<Position> getAllPositionsForThisWord(){
        return this.wordPosition.llist;
    }

    //Return an integer equal to the number of occurrences of the word in all the documents
    int totalWordOccurrences(){
        int i=0;
        MyLinkedList.Node<Position> node = wordPosition.llist.head;
        while (node!=null){
            i++;
            node=node.next;
        }
        return i;
    }

    void setIdf(double idf){
        this.idf =idf;
    }
    double getIdf(){
        return this.idf;
    }
}
