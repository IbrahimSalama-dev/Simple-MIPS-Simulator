package guiarch;

public class Adder {

    private String result;

    public Adder(int c) {
        int res=c+4;
        result = Utility.ReqBits(Integer.toBinaryString(res), 32);
    }

    public Adder(String s1, String s2) {    
        int x = Utility.ConvertBitsBack(s1);
        int y = Utility.ConvertBitsBack(s2);
        int sum = x/4 + y;
        result = Utility.ReqBits(Integer.toBinaryString(sum), 32);

    }

    public String getresult() {
        return result;
    }

}
