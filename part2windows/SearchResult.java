public class SearchResult implements Comparable<SearchResult> {
    public PageEntry page;
    public float relevance;
    //Constructor method.
    public SearchResult(PageEntry p, float r){
        this.page = p;
        this.relevance = r;

    }
    public PageEntry getPageEntry(){
        return page;
    }
    public float getRelevance(){
        return relevance;
    }
    //Gives the ordering between the current object and the otherObject.
    public int compareTo(SearchResult otherObject){
        int result = Float.compare(this.relevance,otherObject.relevance);
        return result;
    }
}
