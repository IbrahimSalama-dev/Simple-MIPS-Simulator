package guiarch;
public class RegFile {
    private static final String reg0 = "00000000000000000000000000000000";
    private static String regs [] = new String[31];
    private int controlSignal;
    private String reg1Address, reg2Address, AddressToWriteIn;

    public RegFile(String Address1,String Address2, String Address3, int controlSignal){
        reg1Address = Address1;
        reg2Address = Address2;
        AddressToWriteIn = Address3;
        this.controlSignal = controlSignal;
    }

    public void setReg (String num){
        if(controlSignal == 1)
        {
            if(AddressToWriteIn.equals("00000"))
                return;
            regs[Integer.parseInt(AddressToWriteIn.trim(), 2) - 1] = num;
        }
    }
    public String getReg1(){
         if(Integer.parseInt(reg1Address, 2)==0)
            return "00000" ;
        return regs[Integer.parseInt(reg1Address, 2) - 1];
    }
    public String getReg2(){
        if(Integer.parseInt(reg2Address, 2)==0)
            return "00000" ;
        return regs[Integer.parseInt(reg2Address, 2) - 1];
    }
  
    
    public static String getReg(int i)
    {
        if(i==0)
            return reg0;
        else return regs[i-1];
    }   
    
    public static void IntializeRegs(int i, int num)
    {
        regs[i - 1] = Utility.ReqBits(Integer.toBinaryString(num), 32);
    }
    
}