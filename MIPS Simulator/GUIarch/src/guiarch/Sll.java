
package guiarch;

public class Sll {
private String s1;    
Sll(String S){
    if(S.length()==32)
        s1=S.substring(2,32)+"00";
    else
        s1=S+"00";
}

    public String getS1() {
        return s1;
    }

}
