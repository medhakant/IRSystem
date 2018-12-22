# Readme for Information Retrieval System which I named SearchEngine

## PreProcessing( Part 1)

For preprocessing step a java file PreProcessing.java was created and it has a split method to split all the different documents in the cranfield collection into 1400 separate documents and then save them as txt files. The other one is in the main of the PreProcessing class which processes the broken down documents by removing all characters other than words and alphabets, removing all stopwords and also stemming the text. To test it yourself, just compile and run the PreProcessing class simply. Though you will need to create two folders in same folder where java classes are present namely "splitdoc" and "stemmeddoc" which have been created so that the files are arranged. An output file containing the length of the documents is also made.

## Information Retrieval( Part 2)

For this purpose, a java class PageEntry was created that inputs a documents and breaks it down into words and the words are stored in a HashMap which also I built myself. And then calculated the IDF and WordFrequency in a document. Then a MySort class was created to sort the result to show top 100 results only. To test this part simply run the SearchEngine class and input query messages on terminal. The output it produces are stored in text files named query#.txt, where # is the query number starting from 0. For this part too you need to create a folder in the same location where the java classes are present names "queryOutputs" which will contain all the output query text documents. The output will also be printed to the terminal. Also, one thing to notice, you don't to run part1 again for part2 but you need to copy the folder "stemmeddoc" from part1 to the code location of part2.