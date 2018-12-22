public class Position {

    public PageEntry pe;
    public double wf;
    private double w;
    private double w_;

    //Constructor method.
    Position(PageEntry p, double wordFreq){
        this.pe = p;
        this.wf = wordFreq;
    }

    //Return pi
    PageEntry getPageEntry(){
        return this.pe;
    }

    //Return wordIndex
    double getWordFreq(){
        return this.wf;
    }

    void setW(double w){
        this.w =w;
    }
    double getW(){
        return this.w;
    }

    void setW_(double w_){
        this.w_ =w_;
    }
    double getW_(){
        return this.w_;
    }
}
