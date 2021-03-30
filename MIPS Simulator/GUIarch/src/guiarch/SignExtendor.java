package guiarch;

public class SignExtendor {

    private String arr;

    public String get32Bits() {
        return arr;
    }

    public SignExtendor(String s) {
        arr=Utility.ReqBits(s, 32);
}
}