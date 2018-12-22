public class PageIndex {
    private MySet<WordEntry> wordEntries = new MySet<>();

    //Add position p to the word-entry of str. If a word entry for str is already
    //present in the page index, then add p to the word entry. Other-
    //wise, create a new word-entry for str with just one position entry p.
    void addPositionForWord(String str, Position p){
        MyLinkedList.Node<WordEntry> dummy = wordEntries.llist.head;
        while(dummy!=null&&!dummy.data.word.equals(str)){
            dummy = dummy.next;
        }
        if(dummy!=null){
            if(dummy.data.word.equals(str)){
                dummy.data.addPosition(p);}}
        else {
            WordEntry word = new WordEntry(str);
            MyLinkedList.Node<WordEntry> node = new MyLinkedList.Node<>(word);
            node.data.addPosition(p);
            wordEntries.addElement(node.data);}

    }

    //Return a list of all word entries stored in the page index.
    MyLinkedList<WordEntry> getWordEntries(){
        return this.wordEntries.llist;
    }
}
