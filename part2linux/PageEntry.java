import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PageEntry {
    public String rawW=""; //the complete string read from the document
    public PageIndex pgIndex = new PageIndex(); //pgIndex contains the page index of all the words in this page
    private String[] words = {}; //cotains each individual strng; made by splitting the rawW file
    private int totalW;  //contains the total no. of words in the document
    public String name; //name of the page; later used for comparing pageEntry
    public long maxFreq;
    public double length=0;

    //Constructor method. The argument is the name of the document.
    // Read this le, and create the page index.
    PageEntry(String pageName){
        name = pageName;
        File file = new File("stemmeddoc/"+pageName);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                rawW+=line+" ";
            }
            words = rawW.split(" ");
            totalW = words.length;
            if(totalW>0){
                Stream<String> stream = Stream.of(rawW.split("\\W+")).parallel();
                Map<String, Long> wordFreq = stream.collect(Collectors.groupingBy(String::toString,Collectors.counting()));
                maxFreq = wordFreq.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getValue();
                Set< Map.Entry< String,Long> > st = wordFreq.entrySet();


                for (Map.Entry< String,Long> me:st)
                {
                    Position p = new Position(this,(double) (long)me.getValue()/maxFreq);
                    pgIndex.addPositionForWord(me.getKey(),p);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //returns the page index
    PageIndex getPageIndex(){
        return pgIndex;
    }

    //return the total no. of words in the webpae
    int totalWords(){
        return totalW;
    }

    public static void main(String[] args){
        PageEntry p = new PageEntry("2_.txt");

    }


}
