package guiarch;

public class PC {
    public static int vrCurrentInstruction;
    public static String PC4Bits = "";

    
    public PC() {
        vrCurrentInstruction = LabelFrame.StartingLine;
        PC4Bits = Utility.ReqBits(Integer.toBinaryString(vrCurrentInstruction), 32).substring(0, 4);
    }
    public PC(String NextInstruction) {
        vrCurrentInstruction = Integer.parseInt(NextInstruction,2) + LabelFrame.StartingLine;
        PC4Bits = Utility.ReqBits(Integer.toBinaryString(vrCurrentInstruction), 32).substring(0, 4);
    }

   
    
}
