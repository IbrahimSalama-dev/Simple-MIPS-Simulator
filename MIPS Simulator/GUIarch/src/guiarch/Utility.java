package guiarch;

public class Utility {
    private int Usagenum=0;
    public Utility() {
        Usagenum++;
    }

    public static String ReqBits(String s, int y) {
        String dummy = "";
        if ((s.charAt(0)==1)&&(y==32)){
        return Integer.toBinaryString(ConvertBitsBack(s));
        }
        for (int i = s.length(); i < y; i++) {
            dummy += '0';
        }
        
        return dummy + s;
    }

 public static String findTwosCompliment(String bin) {
        String twos = "", ones = "";

        for (int i = 0; i < bin.length(); i++) {
            ones += flip(bin.charAt(i));
        }
        int number0 = Integer.parseInt(ones, 2);
        StringBuilder builder = new StringBuilder(ones);
        boolean b = false;
        for (int i = ones.length() - 1; i > 0; i--) {
            if (ones.charAt(i) == '1') {
                builder.setCharAt(i, '0');
            } else {
                builder.setCharAt(i, '1');
                b = true;
                break;
            }
        }
        if (!b)
            builder.append("1", 0, 7);

        twos = builder.toString();

        return twos;
    }
 public static char flip(char c) {
        return (c == '0') ? '1' : '0';
    }

    public static int ConvertBitsBack(String s) {
        int i, num;
        String s1;
        if (s.charAt(0) == '1') {
            i = -1;
            s1 = findTwosCompliment(s);
        
        } else {
            i = 1;
            s1=s;
        
        }
        num = Integer.parseInt(s1.trim(), 2);

        return num * i;
    }

}
