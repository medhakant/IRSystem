public class MyHashTable {
    public MyLinkedList.Node<WordEntry>[] hashTable = new MyLinkedList.Node[390];
    //Create a hash function which maps a string to the index of its word-entry in the
    //hashtable. The implementation of hashtable should support chaining.
    public int getHashIndex(String str){
        int hash=0;
        for(int i=0;i<str.length()&& i<3;i++)
            hash+= str.charAt(i);

        return ((hash*str.length())%390);
    }

    //This adds an entry to the hashtable: stringName(w) 􀀀 > positionList(w). If no word-
    //entry exists, then create a new word entry. However, if a word-
    //entry exists, then merge w with the existing word-entry.
    void addPositionsForWord(WordEntry w){
        String word = w.word;
        if(hashTable[getHashIndex(word)]==null){
            MyLinkedList<WordEntry> chain = new MyLinkedList<>();
            MyLinkedList.Node<WordEntry> dummy = new MyLinkedList.Node<>(w);
            chain.head = dummy;
            hashTable[getHashIndex(word)]= chain.head;}
        else {
            MyLinkedList.Node<WordEntry> chainL = hashTable[getHashIndex(word)];
            while (chainL!=null&&!chainL.data.word.equals(w.word)){
                chainL = chainL.next;
            }
            if(chainL!=null && chainL.data.word.equals(w.word)){
                chainL.data.addPositions(w.wordPosition.llist);
            }
            else{
                MyLinkedList.Node<WordEntry> nrear =hashTable[getHashIndex(word)];
                if (nrear!=null){
                    MyLinkedList.Node<WordEntry> nfront =nrear.next;
                    while (nfront!=null){
                        nfront=nfront.next;
                        nrear=nrear.next;
                    }
                    if(!nrear.data.equals(w)){
                        MyLinkedList.Node<WordEntry> newNode = new MyLinkedList.Node(w);
                        nrear.next=newNode;
                    }
                }
            }
        }

    }

//    public static void main(String[] args){
//        MyHashTable x = new MyHashTable();
//        System.out.println(x.getHashIndex("stack"));
//    }
}
