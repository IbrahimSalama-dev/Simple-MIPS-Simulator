package guiarch;
class Label{
    public int LineNo;
    public String LabelName;
    Label(int i,String s){
        LineNo=i;
        LabelName=s;
    }
}
public class BuildInstructionArr {
    public static String[] AssemblyCode ;
    public static Label[] LabelArr;
    public static int lineCounters;
    BuildInstructionArr(String S){
         lineCounters=0;int LabelCounter=0;
        for (int i=0;i<S.length();i++) {
            if(S.charAt(i)==':')
                LabelCounter++;
            if (S.charAt(i) == '\n')
                lineCounters++;
        }
        AssemblyCode=new String[lineCounters];
        LabelArr=new Label[lineCounters];
        int j=0,y=0,l=0;
        for(int i=0;i<S.length();i++){
            if(S.charAt(i)==':'){
                LabelArr[l]=new Label(j+1,S.substring(y,i-1));
                y=i+1;
                l++;
            }
            if(S.charAt(i)=='\n'){
                AssemblyCode[j]=S.substring(y,i)+' ';
                y=i+1;
                j++;
            }
        }
        }
}