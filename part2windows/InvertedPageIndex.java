public class InvertedPageIndex {
    public MyHashTable hash;
    public int totalPageEntry=0;
    InvertedPageIndex(){
        hash = new MyHashTable();
    }
    //Add a new page entry p to the inverted page index.
    void addPage(PageEntry p){
        totalPageEntry++;
        MyLinkedList.Node<WordEntry> y = p.pgIndex.getWordEntries().head;
        while (y!=null){
            hash.addPositionsForWord(y.data);
            y=y.next;
        }
    }

    //Return a set of page-entries of webpages which contain the word str.
    MySet<PageEntry> getPagesWhichContainWord(String str){
        MySet<PageEntry> wordOcr = new MySet<>();
        if(hash.hashTable[hash.getHashIndex(str)]==null)
            return null;
        else{
            MyLinkedList.Node<WordEntry> chainL = hash.hashTable[hash.getHashIndex(str)];
            while (chainL!=null&&!chainL.data.word.equals(str)){
                chainL = chainL.next;
            }
            if(chainL!=null && chainL.data.word.equals(str)){
                MyLinkedList.Node<Position> pos = chainL.data.wordPosition.llist.head;
                while (pos!=null){
                    wordOcr.addElement(pos.data.getPageEntry());
                    pos=pos.next;
                }
            }

        }
        return wordOcr;
    }

    double getIdf(String str){
        double idf =0;
        if(hash.hashTable[hash.getHashIndex(str)]==null)
            return idf;
        else{
            MyLinkedList.Node<WordEntry> chainL = hash.hashTable[hash.getHashIndex(str)];
            while (chainL!=null&&!chainL.data.word.equals(str)){
                chainL = chainL.next;
            }
            if(chainL!=null && chainL.data.word.equals(str)){
               idf = chainL.data.getIdf();
            }

        }
        return idf;
    }

    //Return the relevance of the webpage
    //for a group of words represented by the array str[ ]
    public double getRelevanceOfWordInPage(String word,PageEntry page){
        MyLinkedList.Node<WordEntry> iterator = hash.hashTable[hash.getHashIndex(word)];
        while (!iterator.data.word.equals(word)){
            iterator=iterator.next;
            if(iterator==null)
                return 0;
        }
        MyLinkedList.Node<Position> pageIterator = iterator.data.wordPosition.llist.head;
        while (pageIterator!=null){
            if(pageIterator.data.pe.name.equals(page.name))
                return pageIterator.data.getW_();
            pageIterator = pageIterator.next;
        }
            return 0;
    }

    MySet<PageEntry> getPagesWhichContainAnyWords(String str[]){
        MySet<PageEntry> phraseOcr = new MySet<>();
        int len = str.length;
        MySet<PageEntry> setWithAnyWords = new MySet<>();
        if(hash.hashTable[hash.getHashIndex(str[1])]!=null){
            MyLinkedList.Node<PageEntry> dummyOne = getPagesWhichContainWord(str[1]).llist.head;
            while (dummyOne!=null){
                setWithAnyWords.addElement(dummyOne.data);
                dummyOne=dummyOne.next;
            }
        }

        for(int i=2;i<len;i++){
            MySet<PageEntry> tempSet = new MySet<>();
            if(hash.hashTable[hash.getHashIndex(str[i])]!=null){
                MyLinkedList.Node<PageEntry> dummyTwo = getPagesWhichContainWord(str[i]).llist.head;
                while (dummyTwo!=null){
                    tempSet.addElement(dummyTwo.data);
                    dummyTwo=dummyTwo.next;
                }
            }
            setWithAnyWords=setWithAnyWords.union(tempSet);
        }
        return setWithAnyWords;
    }

    public MySet<SearchResult> getRelevanceOfPage(String str[ ]){
        MySet<SearchResult> pageWithRelevance = new MySet<>();
            int count=0;
            MySet<PageEntry> ListOfPagesWhichContainWord = getPagesWhichContainAnyWords(str);
            MyLinkedList.Node<PageEntry> pagesWhichContainWord = ListOfPagesWhichContainWord.llist.head;
            while (pagesWhichContainWord!=null){
                count++;
                pagesWhichContainWord = pagesWhichContainWord.next;
            }
            float relevance[] = new float[count];
            pagesWhichContainWord = ListOfPagesWhichContainWord.llist.head;
            for(int i=0;i<count;i++){
                relevance[i]=0;
                for(int j=1;j<str.length;j++){
                    relevance[i]+=getRelevanceOfWordInPage(str[j],pagesWhichContainWord.data);
                }
                pagesWhichContainWord=pagesWhichContainWord.next;
            }
            pagesWhichContainWord = ListOfPagesWhichContainWord.llist.head;
            for(int i=0;i<count;i++){
                SearchResult entry= new SearchResult(pagesWhichContainWord.data,relevance[i]);
                pageWithRelevance.addElement(entry);
                pagesWhichContainWord=pagesWhichContainWord.next;
            }
        return pageWithRelevance;
    }
}
