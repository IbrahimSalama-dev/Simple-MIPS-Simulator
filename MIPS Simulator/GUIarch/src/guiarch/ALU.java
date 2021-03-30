package guiarch;

public class ALU {

    private String reg1;
    private String reg2;
    private String action="";
    private String result;

    private int zeroFlag=0;

    public int getZeroFlag() {
        return zeroFlag;
    }

    public String getResult() {
        return result;
    }

    public ALU(String s1, String s2, String act) {
        reg1 = s1;
        reg2 = s2;
        action = act;
        action_do();
    }

    public String convert(String a) {
        String x = "";
        for (int i = 0; i < 32; i++) {
            if (a.charAt(i) == '0') {
                x = x + '1';
            } else {
                x = x + '0';
            }
        }
        return x;
    }

    public void action_do() {
        if (action.trim().equals("0010"))//add
        {
            int a = Utility.ConvertBitsBack(reg1);
            int b = Utility.ConvertBitsBack(reg2.trim());
            int sum = a + b;
            result = Utility.ReqBits(Integer.toBinaryString(sum), 32);
            //result = Integer.toString(sum); // sum has the required value in integer 
        } else if (action == "0110") //subtract
        {
            int a = Utility.ConvertBitsBack(reg1);
            int b = Utility.ConvertBitsBack(reg2.trim());

            int sum = a - b;
            this.result = Utility.ReqBits(Integer.toBinaryString(sum) + "", 32);
            if (sum == 0) {
                zeroFlag = 1;
            } else {
                zeroFlag = 0;
            }

        } else if (action == "0000")//and
        {
            int a = Utility.ConvertBitsBack(reg1);
            int b = Utility.ConvertBitsBack(reg2);
            int sum = a & b;
            this.result = Utility.ReqBits(sum + "", 32);
        } else if (action == "0111")//set on less than
        {
            int a = Utility.ConvertBitsBack(reg1);
            int b = Utility.ConvertBitsBack(reg2);
            if (a < b) {
                result = Utility.ReqBits(1 + "", 32);
            } else {
                result = Utility.ReqBits(0 + "", 32);
            }

        } else if (action == "1100")//nor   hagma3hom el awel b3d kda h3ml method t3'ayar kol bit
        {

            int a = Utility.ConvertBitsBack(reg1);
            int b = Utility.ConvertBitsBack(reg2);

            int sum = a + b;
            String or1 = Utility.ReqBits(sum + "", 32);  // (or1) contains the oring of the two registers
            //method that convert each bit 
            result = convert(or1);

        } else if (action == "0001") // or
        {
            int a = Utility.ConvertBitsBack(reg1);
            int b = Utility.ConvertBitsBack(reg2);
            int sum = a | b;
            result = Utility.ReqBits(sum + "", 32);

        }
        else if (action== "1111"){
        int a = Utility.ConvertBitsBack(reg1);
            int b = Utility.ConvertBitsBack(reg2);
            if(a<b)
                result=Utility.ReqBits(1+"", 32);
            else
                result=Utility.ReqBits(0+"", 32);
        }
        else if (action=="0100"){
        result=Utility.ReqBits(0+"", 32);
        }
        else if (action == "0011") // sll
        {   
            int a = Utility.ConvertBitsBack(reg1);
            int b = Utility.ConvertBitsBack(reg2);
            for(int i =0;i<b;i++){
             a=a*2;
            }
            result = Utility.ReqBits(Integer.toBinaryString(a) + "", 32);

        }

    }

}
