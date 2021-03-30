package guiarch;

public class Mux {


    private int Outi;
    private String Outs;
    Mux(String s0, String s1, int i) {
        if(i==0)
            Outs=s0;
        else if(i==1)
            Outs=s1;
    }

    Mux(int i0, int i1, int i) {
        if(i==0)
            Outi=i0;
        else if(i==1)
            Outi=i1;}

    
    public String getOutputS() {
        return Outs;
    }

    
    public int getOutputI() {
        return Outi;
    }

}
