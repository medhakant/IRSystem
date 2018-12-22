//the adress from which the webpages are to be read is specified in the 15:PageEntry, please udate it before testing this class

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SearchEngine {
    public InvertedPageIndex invertedIndex;
    public MySort<SearchResult> sortingEngine = new MySort<>();
    //This is the constructor method. It should create an empty InvertedPageIndex.
    SearchEngine(){
        this.invertedIndex = new InvertedPageIndex();
        for(int i=1;i<=1400;i++){
            String pageName = i +"_.txt";
            PageEntry x = new PageEntry(pageName);
            invertedIndex.addPage(x);
        }
        for(int i=0;i<390;i++){//a loop to iterate over all the entries of the hashmap and update the word's IDF
            if(invertedIndex.hash.hashTable[i]!=null){
                MyLinkedList.Node<WordEntry> iterator =invertedIndex.hash.hashTable[i];
                while (iterator!=null){
                    iterator.data.setIdf((double)Math.log10((double) invertedIndex.totalPageEntry/iterator.data.totalWordOccurrences()));
                    iterator = iterator.next;
                }
            }
        }
        for(int i=0;i<390;i++){//a loop to iterate over all the entries of the hashmap and update the word's W in a doc
            if(invertedIndex.hash.hashTable[i]!=null){
                MyLinkedList.Node<WordEntry> iterator =invertedIndex.hash.hashTable[i];
                while (iterator!=null){
                    MyLinkedList.Node<Position> pgEntrIterator = iterator.data.wordPosition.llist.head;
                    while (pgEntrIterator!=null){
                        pgEntrIterator.data.setW(iterator.data.getIdf()*pgEntrIterator.data.getWordFreq());
                        pgEntrIterator.data.pe.length+=pgEntrIterator.data.getW();
                        pgEntrIterator=pgEntrIterator.next;
                    }
                    iterator = iterator.next;
                }
            }
        }
        for(int i=0;i<390;i++){//a loop to iterate over all the entries of the hashmap and update the word's W* in a doc
            if(invertedIndex.hash.hashTable[i]!=null){
                MyLinkedList.Node<WordEntry> iterator =invertedIndex.hash.hashTable[i];
                while (iterator!=null){
                    MyLinkedList.Node<Position> pgEntrIterator = iterator.data.wordPosition.llist.head;
                    while (pgEntrIterator!=null){
                        pgEntrIterator.data.setW_(pgEntrIterator.data.getW()/pgEntrIterator.data.pe.length);
                        pgEntrIterator=pgEntrIterator.next;
                    }
                    iterator = iterator.next;
                }
            }
        }
    }
    //This the main stub method that you have to implement. It takes an action as
    //a string. The list of actions, and their format will be described later.
    String performAction(String actionMessage){
        String processedQuery ="";
        processedQuery = actionMessage.replaceAll("[^a-zA-Z0-9+]" ," ").toLowerCase();
        processedQuery = processedQuery.replaceAll("\\b(s|a|about|above|after|again|against|all|am|an|and|any|are|aren't|as|at|be|because|been|before|being|below|between|both|but|by|can't|cannot|could|couldn't|did|didn't|do|does|doesn't|doing|don't|down|during|each|few|for|from|further|had|hadn't|has|hasn't|have|haven't|having|he|he'd|he'll|he's|her|here|here's|hers|herself|him|himself|his|how|how's|i|i'd|i'll|i'm|i've|if|in|into|is|isn't|it|it's|its|itself|let's|me|more|most|mustn't|my|myself|no|nor|not|of|off|on|once|only|or|other|ought|our|ours|ourselves|out|over|own|same|shan't|she|she'd|she'll|she's|should|shouldn't|so|some|such|than|that|that's|the|their|theirs|them|themselves|then|there|there's|these|they|they'd|they'll|they're|they've|this|those|through|to|too|under|until|up|very|was|wasn't|we|we'd|we'll|we're|we've|were|weren't|what|what's|when|when's|where|where's|which|while|who|who's|whom|why|why's|with|won't|would|wouldn't|you|you'd|you'll|you're|you've|your|yours|yourself|yourselves)\\b\\s?","");
        processedQuery = processedQuery.trim().replaceAll(" +", " ");
        String stemmedQuery = stemit(processedQuery);
        String[] arr = stemmedQuery.split(" ");
        String actionMsg = new String();
        if(invertedIndex.hash.hashTable[invertedIndex.hash.getHashIndex(arr[1])]!=null){
            ArrayList<SearchResult> relevanceList = sortingEngine.sortThisList(invertedIndex.getRelevanceOfPage(arr));
            for (int i=0;i<Math.min(relevanceList.size(),100);i++){
                actionMsg+= (i+1)+"    "+relevanceList.get(i).getPageEntry().name+"    "+relevanceList.get(i).relevance+"\n";
            }
        }
        if(actionMsg.length()>2)
            return actionMessage+": \n"+(actionMsg.substring(0,actionMsg.length()-2));
        else
            return "None of the words are present in any document";

    }

    public String stemit(String s){
        Stemmer stemmer = new Stemmer();
        String[] words = s.split(" ");
        String stemmedString = "";
        for(int i=0;i<words.length;i++){
            char[] charArr = words[i].toCharArray();
            stemmer.add(charArr,charArr.length);
            stemmer.stem();
            stemmedString += stemmer.toString()+" ";
        }
        return stemmedString;
    }

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int i=0;
        SearchEngine google = new SearchEngine();
        while (true){
            try{
                String query = input.nextLine();
                if(query.equals("quit"))
                    break;
                String queryOutput = "queryOutputs\\query" +i+ ".txt";
                File sizeOut = new File(queryOutput);
                FileOutputStream outStrm = new FileOutputStream(sizeOut);
                BufferedWriter buffWri = new BufferedWriter(new OutputStreamWriter(outStrm));
                String queryResult = google.performAction("query "+query);
                System.out.println(queryResult);
                buffWri.write(queryResult);
                buffWri.close();
                i++;
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
