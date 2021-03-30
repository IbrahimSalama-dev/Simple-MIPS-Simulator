package guiarch;

public class CurrentInstruction {

    BuildInstructionArr arr;
    InstructionMemory iM;
    Control c;
    SignExtendor sG;
    RegFile rF;
    ALUControl aC;
    PC p;
    Adder a1;
    Adder a2;
    And and;
    DataMemory dM;
    Sll SL1;
    Sll SL2;
    ALU aLu;
    Mux M1;
    Mux M2;
    Mux M3;
    Mux M4;
    Mux M5;
    Mux M6;
    Mux M7;
    Mux M8;
    Mux M9;

    public CurrentInstruction(String s) {
        arr = new BuildInstructionArr(s);
        p = new PC();
        init();

    }

    private void init() {
        iM = new InstructionMemory((PC.vrCurrentInstruction / 4) - LabelFrame.StartingLine / 4);
        c = new Control(iM.getOpCode().trim());
        a1 = new Adder(PC.vrCurrentInstruction);
        sG = new SignExtendor(iM.getIType16Bits());
        M1 = new Mux(iM.getReg2(), iM.getReg3(), c.getRegDst());
        aC = new ALUControl(c.getAluop(), iM.getFunctCode());
        SL1 = new Sll(iM.getJType26Bits());
        M6 = new Mux(M1.getOutputS(),"11111",c.getTofy());
        rF = new RegFile(iM.getReg1(), iM.getReg2(), M6.getOutputS(), c.getRegwrite());
        M2 = new Mux(rF.getReg2(), sG.get32Bits(), c.getAluSrc());
        M9= new Mux(M2.getOutputS(),iM.getShamt(),aC.getShamtc());
        aLu = new ALU(rF.getReg1(), M9.getOutputS(), aC.getAluControlSignal());
        dM = new DataMemory(c.getMemWrite(), c.getMemRead(), rF.getReg2(), aLu.getResult(), c.getDataMem());
        M3 = new Mux(aLu.getResult(), dM.getOutput(), c.getMemtoReg());
        M7=new Mux(M3.getOutputS(),a1.getresult(),c.getTofy());
        rF.setReg(M7.getOutputS());
        SL2 = new Sll(sG.get32Bits());
        a2 = new Adder(SL2.getS1(), a1.getresult());
        and = new And(c.getBranch(), aLu.getZeroFlag());
        M4 = new Mux(a1.getresult(), a2.getresult(), and.getresult());
        M5 = new Mux(M4.getOutputS(), PC.PC4Bits + SL1.getS1(), c.getJump());
        M8= new Mux(M5.getOutputS(),rF.getReg(31),aC.getJrc());
        p = new PC(M8.getOutputS());
    }

    public CurrentInstruction() {

        init();
    }

}
