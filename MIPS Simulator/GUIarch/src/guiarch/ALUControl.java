package guiarch;

public class ALUControl {

    private String ALUop, functCode, AluControlSignal;
private int jrc=0;
private int shamtc=0;

    public int getShamtc() {
        return shamtc;
    }

    public int getJrc() {
        return jrc;
    }

    public String getAluControlSignal() {
        return AluControlSignal;
    }

    public ALUControl(String ALUop, String functCode) {
        this.ALUop = ALUop;
        this.functCode = functCode;
        chooseOperation();
    }

    private void chooseOperation() {
        switch (ALUop) {
            case "00"://add
                AluControlSignal = "0010";
                break;
            case "01"://sub
                AluControlSignal = "0110";
                break;
            case"11":
                AluControlSignal="1111";
                break;
            case "10":
                switch (functCode) {
                    case "100000":
                        AluControlSignal = "0010";
                        break;
                    case "100010":
                        AluControlSignal = "0110";
                        break;
                    case "100100":
                        AluControlSignal = "0000";
                        break;
                    case "100101":
                        AluControlSignal = "0001";
                        break;
                    case "101010":
                        AluControlSignal = "0111";
                        break;
                    case "001000":
                        AluControlSignal="0100";
                        jrc=1;
                        break;
                    case "000000":
                        shamtc=1;
                        AluControlSignal="0011";
                        break;
                }
            
        }
        
    }

}
