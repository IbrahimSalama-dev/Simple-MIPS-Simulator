package guiarch;

public class InstructionMemory {

    private int address;
    private String OpCode;
    private String OpCodeString;
    private String reg3;
    private String reg1;
    private String reg2;
    private String shAmt;
    private String functCode;
    private String JType26Bits;
    private String IType16Bits;
    public int charNo = 0;

    InstructionMemory(int address) {
        this.address=address;
        init(address);
    }

    private void init(int address) {
        OpCodeString = BuildOpCodeString(BuildInstructionArr.AssemblyCode[address].toCharArray());
        OpCode = BuildOpCodeBits(OpCodeString);
        if (OpCodeString.trim().equals("jr")) {
            reg1 = "00000";
            reg2 = "00000";
            reg3 = "00000";
            shAmt = "00000";
            functCode = "001000";
            IType16Bits = reg2 + shAmt + functCode;
            JType26Bits = reg3 + reg1 + IType16Bits;
        } else {
            if (OpCodeString.trim().equals("j") || OpCodeString.trim().equals("jal")) {
                JType26Bits = BuildJType26bits(BuildInstructionArr.AssemblyCode[address].toCharArray(), charNo);
                IType16Bits = JType26Bits.substring(10, 26);
                reg1 = JType26Bits.substring(0, 5);
                reg2 = JType26Bits.substring(5, 10);
                reg3 = JType26Bits.substring(10, 15);
                shAmt = JType26Bits.substring(15, 20);
                functCode = JType26Bits.substring(20, 26);
            } else {
                reg3 = BuildRegisterBits(BuildRegisterString(BuildInstructionArr.AssemblyCode[address].toCharArray(), charNo));
                if (OpCodeString.trim().equals("lw") || OpCodeString.trim().equals("sw") || OpCodeString.trim().equals("lb") || OpCodeString.trim().equals("lbu") || OpCodeString.trim().equals("sb")) {
                    IType16Bits = BuildIType16Bits(BuildInstructionArr.AssemblyCode[address].toCharArray(), charNo);
                }
                reg1 = BuildRegisterBits(BuildRegisterString(BuildInstructionArr.AssemblyCode[address].toCharArray(), charNo));
                BuildRemainingBits();
            }
        }

    }

    public String getOpCode() {
        return OpCode;
    }

    public String getReg1() {
        return reg1;
    }

    public String getReg2() {
        return reg2;
    }

    public String getReg3() {
        return reg3;
    }

    public String getShamt() {
        return shAmt;
    }

    public String getFunctCode() {
        return functCode;
    }

    public String getIType16Bits() {
        return IType16Bits;
    }

    public String getJType26Bits() {
        return JType26Bits;
    }

    private String BuildOpCodeString(char[] S) {
        char[] A = new char[5];
        for (int i = 0;; i++) {
            if (S[i] == ' ') {
                break;
            }
            A[i] = S[i];
            charNo++;
        }
        return new String(A);
    }

    private String BuildOpCodeBits(String S) {
        S = S.toLowerCase();
        switch (S.trim()) {
            case "add": {
                functCode = "100000";
                return "000000";
            }
            case "addi":
                return "001000";
            case "lw":
                return "100011";
            case "sw":
                return "101011";
            case "lb":
                return "100000";
            case "lbu":
                return "100100";
            case "sb":
                return "101000";
            case "nor": {
                functCode = "100111";
                return "000000";
            }
            case "beq":
                return "000100";
            case "j":
                return "000010";
            case "jal":
                return "000011";
            case "slt": {
                functCode = "101010";
                return "000000";
            }
            case "jr": {
                functCode = "001000";
                return "000000";
            }
            case "slti":
                return "001010";
            case "sll": {
                functCode = "000000";
                return "000000";
            }
        }
        return "01";
    }

    private String BuildRegisterString(char[] S, int start) {
        char[] A = new char[2];
        for (int i = 0;; i++) {
            charNo++;
            if (S[i + start] == '$') {
                A[0] = S[i + 1 + start];
                A[1] = S[i + 2 + start];
                charNo += 2;
                break;
            }
        }
        return new String(A);
    }

    private String BuildRegisterBits(String S) {
        String s = S.toLowerCase().trim();
        int regNum = 0;
        switch (s.charAt(0)) {
            case 's':
                if (s.charAt(1) == 'p') {
                    return Integer.toBinaryString(29);
                }
                for (int i = 0; i <= 7; i++) {
                    if (s.charAt(1) == (char) (i + '0')) {
                        return Integer.toBinaryString((i + 16));
                    }
                }
            case 't':
                for (int i = 0; i <= 7; i++) {
                    if (s.charAt(1) == (char) (i + '0')) {
                        return Utility.ReqBits(Integer.toBinaryString(i + 8), 5);
                    }
                }
                if (s.charAt(1) == (char) (9 + '0')) {
                    return Integer.toBinaryString(25);
                } else if (s.charAt(1) == (char) (8 + '0')) {
                    return Integer.toBinaryString(24);
                }
            case 'a':
                for (int i = 0; i <= 3; i++) {
                    if (s.charAt(1) == (char) (i + '0')) {
                        return Integer.toBinaryString(0x100 | (i + 4)).substring(4);
                    }
                }
                if (s.charAt(1) == 't') {
                    return Integer.toBinaryString(0x100 | 1).substring(4);
                }
            case 'v':
                for (int i = 0; i <= 1; i++) {
                    if (s.charAt(1) == (char) (i + '0')) {
                        return Integer.toBinaryString(0x100 | (i + 2)).substring(4);
                    }
                }
            case 'g':
                return Integer.toBinaryString(28);
            case 'r':
                return Integer.toBinaryString(31);
            case 'f':
                return Integer.toBinaryString(30);
            case '0':
                return Integer.toBinaryString(0x100 | 0).substring(4);
            case 'z':
                return Integer.toBinaryString(0x100 | 0).substring(4);
            case 'k':
                for (int i = 0; i <= 1; i++) {
                    if (s.charAt(1) == (char) (i + '0')) {
                        return Integer.toBinaryString(0x100 | (i + 26)).substring(4);
                    }
                }

        }
        return Integer.toBinaryString(regNum);
    }

    private char CheckOpcodeType(String S) {
        if (S.equals("000010") || S.equals("000011")) {
            return 'j';
        }
        if (Integer.parseInt(OpCode, 2) == 0) {
            return 'r';
        }

        return 'i';
    }

    private String BuildShamtBits(int I) {
        return Integer.toBinaryString(0x100 | I).substring(4);
    }

    private int BuildShamtint(char[] S, int start) {
        char[] A = new char[6];
        for (int i = 0;; i++) {
            if (S[i + start] != ' ' && S[i + start] != ',') {
                for (int j = 0;; j++) {
                    A[j] = S[i + start];
                    charNo++;
                    if (S[i + start + 1] == ' ') {
                        break;
                    }
                    i++;
                }
                break;
            }
        }
        return Integer.parseInt(new String(A).trim());

    }

    private String BuildIType16Bits(char[] S, int start) {
        char[] A = new char[16];
        for (int i = 0;; i++) {
            if (S[i + start] != ' ' && S[i + start] != ',') {
                for (int j = 0;; j++) {
                    A[j] = S[i + start];
                    charNo++;
                    if (S[i + start + 1] == ' ' || S[i + start + 1] == '(') {
                        break;
                    }
                    i++;
                }
                break;
            }
        }
        return Utility.ReqBits(Integer.toBinaryString(Integer.parseInt(new String(A).trim())), 16);
    }

    private String BuildLabelName(char[] S, int start) {
        char[] A = new char[6];
        for (int i = 0;; i++) {
            if (S[i + start] != ' ' && S[i + start] != ',') {
                for (int j = 0;; j++) {
                    A[j] = S[i + start];
                    charNo++;
                    if (S[i + start + 1] == ' ') {
                        break;
                    }
                    i++;
                }
                break;
            }
        }
        return new String(A).trim();
    }

    private String BuildJType26Labelbits(String S) {
        String r = "";
        for (Label l : BuildInstructionArr.LabelArr) {
            if (l.LabelName == S) {
                r = (Integer.toBinaryString(0x1000000 | l.LineNo));
            }
        }
        return r;
    }

    private String BuildJType26bits(char[] S, int start) {
        char[] A = new char[16];
        for (int i = 0;; i++) {
            if (S[i + start] != ' ' && S[i + start] != ',') {
                for (int j = 0;; j++) {
                    A[j] = S[i + start];
                    charNo++;
                    if (S[i + start + 1] == ' ' || S[i + start + 1] == '(') {
                        break;
                    }
                    i++;
                }
                break;
            }
        }
        return Utility.ReqBits(Integer.toBinaryString(Integer.parseInt(new String(A).trim())), 26);
    }

    private void BuildRemainingBits() {
        switch (CheckOpcodeType(OpCode)) {
            case 'r':
                if (OpCodeString.trim().equals("sll")) {
                    shAmt = BuildShamtBits(BuildShamtint(BuildInstructionArr.AssemblyCode[address].toCharArray(), charNo));
                    reg2 = "00000";
                    IType16Bits = reg2 + shAmt + functCode;
                    JType26Bits = reg3 + reg1 + IType16Bits;
                } else {
                    reg2 = BuildRegisterBits(BuildRegisterString(BuildInstructionArr.AssemblyCode[address].toCharArray(), charNo));
                    shAmt = "00000";
                    IType16Bits = reg2 + shAmt + functCode;
                    JType26Bits = reg3 + reg1 + IType16Bits;
                }
                break;
            case 'i':
                if (!(OpCodeString.trim().equals("lw") || OpCodeString.trim().equals("sw") || OpCodeString.trim().equals("lb") || OpCodeString.trim().equals("lbu") || OpCodeString.trim().equals("sb"))) {
                    IType16Bits = BuildIType16Bits(BuildInstructionArr.AssemblyCode[address].toCharArray(), charNo);
                }
                reg2=reg3;
                reg3 = IType16Bits.substring(0, 5);
                shAmt = IType16Bits.substring(5, 10);
                functCode = IType16Bits.substring(10, 16);
                JType26Bits = reg3 + reg1 + IType16Bits;
                break;

            case 'j':

        }
    }
}
