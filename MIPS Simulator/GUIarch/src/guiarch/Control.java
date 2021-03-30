package guiarch;

public class Control {

    String arr;

    private int RegDst = 0;
    private int Branch = 0;
    private int MemRead = 0;
    private int MemtoReg = 0;
    private String Aluop;
    private int MemWrite = 0;
    private int AluSrc = 0;
    private int Regwrite = 0;
    private int jump = 0;
    private String dataMemControl = "";
private int tofy=0;


    public String getDataMemControl() {
        return dataMemControl;
    }

    public int getTofy() {
        return tofy;
    }
    public int getRegDst() {
        return RegDst;
    }

    public int getBranch() {
        return Branch;
    }

    public int getMemRead() {
        return MemRead;
    }

    public int getMemtoReg() {
        return MemtoReg;
    }

    public String getAluop() {
        return Aluop;
    }

    public int getMemWrite() {
        return MemWrite;
    }

    public int getAluSrc() {
        return AluSrc;
    }

    public int getRegwrite() {
        return Regwrite;
    }

    public int getJump() {
        return jump;
    }

    public Control(String s) {
        arr = s.trim();
        init();
    }

    void init() {
        if (arr.equals("000000")) //add,sub,nor,jr,sll
        {
            RegDst = 1;
            Branch = 0;
            MemRead = 0;
            MemtoReg = 0;
            Aluop = "10";
            MemWrite = 0;
            AluSrc = 0;
            Regwrite = 1;

        } else if (arr.equals("001000")) //addi
        {
            RegDst = 0;
            Branch = 0;
            MemRead = 0;
            MemtoReg = 0;
            Aluop = "00";
            MemWrite = 0;
            AluSrc = 1;
            Regwrite = 1;

        }
        else if (arr.equals("001010")) //slti
        {
            RegDst = 0;
            Branch = 0;
            MemRead = 0;
            MemtoReg = 0;
            Aluop = "11";
            MemWrite = 0;
            AluSrc = 1;
            Regwrite = 1;

        } else if (arr.equals("100011")) //lw
        {
            RegDst = 0;
            Branch = 0;
            MemRead = 1;
            MemtoReg = 1;
            Aluop = "00";
            MemWrite = 0;
            AluSrc = 1;
            Regwrite = 1;
            dataMemControl = "000";
        } else if (arr.equals("101011")) //sw
        {
            RegDst = 1;
            Branch = 0;
            MemRead = 0;
            MemtoReg = 0;
            Aluop = "00";
            MemWrite = 1;
            AluSrc = 1;
            Regwrite = 0;
            dataMemControl = "011";
        } else if (arr.equals("100000")) //lb
        {
            RegDst = 1;
            Branch = 0;
            MemRead = 1;
            MemtoReg = 1;
            Aluop = "00";
            MemWrite = 0;
            AluSrc = 1;
            Regwrite = 1;
            dataMemControl = "001";

        } else if (arr.equals("100100")) //lbu
        {
            RegDst = 1;
            Branch = 0;
            MemRead = 1;
            MemtoReg = 1;
            Aluop = "00";
            MemWrite = 0;
            AluSrc = 1;
            Regwrite = 1;
            dataMemControl = "010";
        } else if (arr.equals("101000")) //sb
        {
            RegDst = 1;
            Branch = 0;
            MemRead = 0;
            MemtoReg = 0;
            Aluop = "00";
            MemWrite = 1;
            AluSrc = 1;
            Regwrite = 0;
            dataMemControl = "100";

        } else if (arr.trim().equals("000011")) //jal
        {
            RegDst = 0;
            Branch = 0;
            MemRead = 0;
            MemtoReg = 0;
            Aluop = "01";
            MemWrite = 0;
            AluSrc = 0;
            Regwrite = 1;
            jump = 1;
            tofy=1;

        } else if (arr.trim().equals("000010")) //j
        {
            RegDst = 0;
            Branch = 0;
            MemRead = 0;
            MemtoReg = 0;
            Aluop = "01";
            MemWrite = 0;
            AluSrc = 0;
            Regwrite = 0;
            jump = 1;

        }else if (arr.trim().equals("000100")) //beq
        {
            RegDst = 0;
            Branch = 1;
            MemRead = 0;
            MemtoReg = 0;
            Aluop = "01";
            MemWrite = 0;
            AluSrc = 0;
            Regwrite = 0;
            jump = 0;

        }

    }

    public String getDataMem() {
        return dataMemControl;
    }
}
