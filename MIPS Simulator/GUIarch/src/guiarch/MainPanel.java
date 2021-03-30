package guiarch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainPanel extends JFrame {
//DataPaths
    private String PCi;
    private String InstructionMemi;
    private String InstructionMemo1;
    private String InstructionMemo2;
    private String InstructionMemo3;
    private String InstructionMemo4;
    private String InstructionMemo5;
    private String InstructionMemo6;
    private String InstructionMemo7;
    private String InstructionMemo8;
    private String SignExtendoro;
    private String Controlo1;
    private String Controlo2;
    private String Controlo3;
    private String Controlo4;
    private String Controlo5;
    private String Controlo6;
    private String Controlo7;
    private String Controlo8;
    private String Controlo9;
    private String M1o;
    private String ALUControlo;
    private String Registero1;
    private String Registero2;
    private String SL1o;
    private String M2o;
    private String Aluo1;
    private String Aluo2;
    private String DataMemo;
    private String M3o;
    private String Add1o;
    private String Add2o;
    private String SL2o;
    private String Ando;
    private String M4o;
    private String M5o;
    
    
    
    private JLabel[] comlbl1 = new JLabel[20];
    private JLabel[] comlbl2 = new JLabel[20];
    private Container c;
    private BackgroundPanel Background;
    private JPanel southpanel = new JPanel();
    private JPanel eastpanel = new JPanel();
    private JTextArea txtarea = new JTextArea();

    private JLabel[] regnames = new JLabel[32];
    private JLabel[] regvalues = new JLabel[32];
    private JLabel IterationNo = new JLabel("Iteration No.");
    private JLabel NoOfIteration = new JLabel("0");
    public static int counter = 0;

    private JButton submitbtn = new JButton("Submit");
    private JButton nxtbtn = new JButton("Next Iteration");
    private JButton run =new JButton("Run");
    public MainPanel() {
        init();
    }

    private void SetDatapaths(CurrentInstruction arr) {
        InstructionMemi = PC.vrCurrentInstruction+"";
        InstructionMemo1 = arr.iM.getOpCode();
        InstructionMemo2 = arr.iM.getReg1();
        InstructionMemo3 = arr.iM.getReg2();
        InstructionMemo4 = arr.iM.getReg3();
        InstructionMemo5 = arr.iM.getShamt();
        InstructionMemo6 = arr.iM.getFunctCode();
        InstructionMemo7 = arr.iM.getJType26Bits();
        InstructionMemo8 = arr.iM.getIType16Bits();
        SignExtendoro = arr.sG.get32Bits();
        Controlo1 = arr.c.getRegDst() + "";
        Controlo2 = arr.c.getJump() + "";
        Controlo3 = arr.c.getBranch() + "";
        Controlo4 = arr.c.getMemRead() + "";
        Controlo5 = arr.c.getMemtoReg() + "";
        Controlo6 = arr.c.getAluop() + "";
        Controlo7 = arr.c.getMemWrite() + "";
        Controlo8 = arr.c.getAluSrc() + "";
        Controlo9 = arr.c.getRegwrite() + "";
        M1o = arr.M1.getOutputS();
        ALUControlo = arr.aC.getAluControlSignal();
        Registero1 = arr.rF.getReg1();
        Registero2 = arr.rF.getReg2();
        SL1o=arr.SL1.getS1();
        M2o=arr.M2.getOutputS();
        Aluo1=arr.aLu.getZeroFlag()+"";
        Aluo2=arr.aLu.getResult();
        DataMemo=arr.dM.getOutput();
        M3o=arr.M3.getOutputS();
        M4o=arr.M4.getOutputS();
        M5o=arr.M5.getOutputS();
        Ando=arr.and.getresult()+"";
        Add1o=arr.a1.getresult();
        Add2o=arr.a2.getresult();
        SL2o=arr.SL2.getS1();
        PCi=M5o;
    }

    private void init() {
        Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setBounds(0, 0, r.width, r.height);
        setDefaultCloseOperation(3);
        setResizable(true);

        Image i1 = new ImageIcon(this.getClass().getResource("/guiarch/arch.jpeg")).getImage();

        Background = new BackgroundPanel(i1);
        c = getContentPane();

        c.add(Background, BorderLayout.CENTER);
        c.add(southpanel, BorderLayout.SOUTH);

        eastpanel.setLayout(new GridLayout(32, 2));
        regnames[0] = new JLabel("$Zero");
        regnames[1] = new JLabel("$at");
        regnames[2] = new JLabel("$v0");
        regnames[3] = new JLabel("$v1");
        regnames[4] = new JLabel("$a0");
        regnames[5] = new JLabel("$a1");
        regnames[6] = new JLabel("$a2");
        regnames[7] = new JLabel("$a3");
        regnames[8] = new JLabel("$t0");
        regnames[9] = new JLabel("$t1");
        regnames[10] = new JLabel("$t2");
        regnames[11] = new JLabel("$t3");
        regnames[12] = new JLabel("$t4");
        regnames[13] = new JLabel("$t5");
        regnames[14] = new JLabel("$t6");
        regnames[15] = new JLabel("$t7");
        regnames[16] = new JLabel("$s0");
        regnames[17] = new JLabel("$s1");
        regnames[18] = new JLabel("$s2");
        regnames[19] = new JLabel("$s3");
        regnames[20] = new JLabel("$s4");
        regnames[21] = new JLabel("$s5");
        regnames[22] = new JLabel("$s6");
        regnames[23] = new JLabel("$s7");
        regnames[24] = new JLabel("$t8");
        regnames[25] = new JLabel("$t9");
        regnames[26] = new JLabel("$k0");
        regnames[27] = new JLabel("$k1");
        regnames[28] = new JLabel("$gp");
        regnames[29] = new JLabel("$sp");
        regnames[30] = new JLabel("$fp");
        regnames[31] = new JLabel("$ra");
        regvalues[0]=new JLabel("0");
        for (int i = 1; i < 32; i++) {
            regvalues[i] = new JLabel(Utility.ConvertBitsBack(RegFile.getReg(i))+"");
        }
        for (int i = 0; i < 32; i++) {
            regnames[i].setFont(new Font("Tahoma", 1, 24));
            regvalues[i].setFont(new Font("Tahoma", 1, 24));
            regnames[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            regvalues[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            eastpanel.add(regnames[i]);
            eastpanel.add(regvalues[i]);
        }
        eastpanel.setPreferredSize(new Dimension(r.width / 5, r.height));
        c.add(eastpanel, BorderLayout.EAST);

        txtarea.setPreferredSize(new Dimension(r.width / 5, r.height));
        txtarea.setFont(new Font("Tahoma", 1, 28));
        txtarea.setBorder(BorderFactory.createLineBorder(Color.black));
        c.add(txtarea, BorderLayout.WEST);

        submitbtn.setFont(new Font("Tahoma", 1, 48));
        run.setFont(new Font("Tahoma", 1, 48));
        submitbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (counter == 0) {
                    txtarea.setEditable(false);
                    txtarea.setBackground(Color.GRAY);
                                        String s = txtarea.getText();
                    if (s.charAt(s.length() - 1) != '\n') {
                        s = s + '\n';
                    }
                    CurrentInstruction arr = new CurrentInstruction(s);
                    SetDatapaths(arr);
                       for (int i = 0; i < 32; i++) {
                    regvalues[i].setText(Utility.ConvertBitsBack(RegFile.getReg(i))+"");
                }
                    counter++;
                    NoOfIteration.setText(Integer.toString(counter));
                }
            }
        });

        nxtbtn.setFont(new Font("Tahoma", 1, 48));
        nxtbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                    if((PC.vrCurrentInstruction / 4) - LabelFrame.StartingLine / 4<BuildInstructionArr.AssemblyCode.length){
                    CurrentInstruction x = new CurrentInstruction();
                    SetDatapaths(x);
                       for (int i = 0; i < 32; i++) {
                    regvalues[i].setText(Utility.ConvertBitsBack(Utility.ReqBits(RegFile.getReg(i)+"",32))+"");
                }
                    counter++;
                    NoOfIteration.setText(Integer.toString(counter));
            }}
            
        });
        run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (counter == 0) {
                    txtarea.setEditable(false);
                    txtarea.setBackground(Color.GRAY);
                                        String s = txtarea.getText();
                    if (s.charAt(s.length() - 1) != '\n') {
                        s = s + '\n';
                    }
                    CurrentInstruction arr = new CurrentInstruction(s);
                    SetDatapaths(arr);
                       for (int i = 0; i < 32; i++) {
                    regvalues[i].setText(Utility.ConvertBitsBack(RegFile.getReg(i))+"");
                }
                    counter++;
                    NoOfIteration.setText(Integer.toString(counter));
                }    
                while((PC.vrCurrentInstruction / 4) - LabelFrame.StartingLine / 4<BuildInstructionArr.AssemblyCode.length){
                    CurrentInstruction x = new CurrentInstruction();
                    SetDatapaths(x);
                       for (int i = 0; i < 32; i++) {
                    regvalues[i].setText(Utility.ConvertBitsBack(Utility.ReqBits(RegFile.getReg(i)+"",32))+"");
                }
                    counter++;
                    NoOfIteration.setText(Integer.toString(counter));
            }}
            
        });
        IterationNo.setFont(new Font("Tahoma", 1, 48));
        NoOfIteration.setFont(new Font("Tahoma", 1, 48));
        southpanel.add(run);
        southpanel.add(submitbtn);
        southpanel.add(nxtbtn);
        southpanel.add(IterationNo);
        southpanel.add(NoOfIteration);

        Background.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((e.getY() > Background.getHeight() / 2 && e.getY() < Background.getHeight() - (Background.getHeight() / 4)) && (e.getX() > Background.getWidth() / 2.5 && e.getX() < Background.getWidth() / 1.84)) {
                    JFrame freg = new JFrame("Register File");
                    freg.setBounds(Background.getX() + Background.getWidth() / 4, Background.getY() + Background.getHeight() / 4, Background.getWidth() / 2, Background.getHeight() / 2);
                    freg.setLayout(new GridLayout(9, 2));
                    comlbl1[0] = new JLabel("Input :");
                    comlbl1[1] = new JLabel("Register Write :");
                    comlbl1[2] = new JLabel("Register 1 :");
                    comlbl1[3] = new JLabel("Register 2 :");
                    comlbl1[4] = new JLabel("Mux Result :");
                    comlbl1[5] = new JLabel("Write Data :");
                    comlbl1[6] = new JLabel("Output :");
                    comlbl1[7] = new JLabel("Read Data 1 :");
                    comlbl1[8] = new JLabel("Read Data 2 :");

                    comlbl2[0] = new JLabel();
                    comlbl2[1] = new JLabel(M3o);
                    comlbl2[2] = new JLabel(InstructionMemo2);
                    comlbl2[3] = new JLabel(InstructionMemo3);
                    comlbl2[4] = new JLabel(M1o);
                    comlbl2[5] = new JLabel(Controlo9);
                    comlbl2[6] = new JLabel();
                    comlbl2[7] = new JLabel(Registero1);
                    comlbl2[8] = new JLabel(Registero2);

                    freg.add(comlbl1[0]);
                    freg.add(comlbl2[0]);
                    freg.add(comlbl1[1]);
                    freg.add(comlbl2[1]);
                    freg.add(comlbl1[2]);
                    freg.add(comlbl2[2]);
                    freg.add(comlbl1[3]);
                    freg.add(comlbl2[3]);
                    freg.add(comlbl1[4]);
                    freg.add(comlbl2[4]);
                    freg.add(comlbl1[5]);
                    freg.add(comlbl2[5]);
                    freg.add(comlbl1[6]);
                    freg.add(comlbl2[6]);
                    freg.add(comlbl1[7]);
                    freg.add(comlbl2[7]);
                    freg.add(comlbl1[8]);
                    freg.add(comlbl2[8]);

                    freg.setVisible(true);

                } else if ((e.getY() > Background.getHeight() / 1.93 && e.getY() < Background.getHeight() / 1.4) && (e.getX() > Background.getWidth() / 9.18 && e.getX() < Background.getWidth() / 4.8)) {
                    JFrame freg = new JFrame("Instruction Memory");
                    freg.setBounds(Background.getX() + Background.getWidth() / 4, Background.getY() + Background.getHeight() / 4, Background.getWidth() / 2, Background.getHeight() / 2);
                    freg.setLayout(new GridLayout(11, 2));
                    comlbl1[0] = new JLabel("Input :");
                    comlbl1[1] = new JLabel("Address :");
                    comlbl1[2] = new JLabel("Output :");
                    comlbl1[3] = new JLabel("OpCode :");
                    comlbl1[4] = new JLabel("Register 1 :");
                    comlbl1[5] = new JLabel("Register 2 :");
                    comlbl1[6] = new JLabel("Register 3 :");
                    comlbl1[7] = new JLabel("Shift Ammout :");
                    comlbl1[8] = new JLabel("Function Code :");
                    comlbl1[9] = new JLabel("Jump Code :");
                    comlbl1[10] = new JLabel("IType 16 Code :");

                    comlbl2[0] = new JLabel();
                    comlbl2[1] = new JLabel(InstructionMemi);
                    comlbl2[2] = new JLabel();
                    comlbl2[3] = new JLabel(InstructionMemo1);
                    comlbl2[4] = new JLabel(InstructionMemo2);
                    comlbl2[5] = new JLabel(InstructionMemo3);
                    comlbl2[6] = new JLabel(InstructionMemo4);
                    comlbl2[7] = new JLabel(InstructionMemo5);
                    comlbl2[8] = new JLabel(InstructionMemo6);
                    comlbl2[9] = new JLabel(InstructionMemo7);
                    comlbl2[10] = new JLabel(InstructionMemo8);

                    freg.add(comlbl1[0]);
                    freg.add(comlbl2[0]);
                    freg.add(comlbl1[1]);
                    freg.add(comlbl2[1]);
                    freg.add(comlbl1[2]);
                    freg.add(comlbl2[2]);
                    freg.add(comlbl1[3]);
                    freg.add(comlbl2[3]);
                    freg.add(comlbl1[4]);
                    freg.add(comlbl2[4]);
                    freg.add(comlbl1[5]);
                    freg.add(comlbl2[5]);
                    freg.add(comlbl1[6]);
                    freg.add(comlbl2[6]);
                    freg.add(comlbl1[7]);
                    freg.add(comlbl2[7]);
                    freg.add(comlbl1[8]);
                    freg.add(comlbl2[8]);
                    freg.add(comlbl1[9]);
                    freg.add(comlbl2[9]);
                    freg.add(comlbl1[10]);
                    freg.add(comlbl2[10]);

                    freg.setVisible(true);

                } else if ((e.getY() > Background.getHeight() / 3.89 && e.getY() < Background.getHeight() / 2.05) && (e.getX() > Background.getWidth() / 2.79 && e.getX() < Background.getWidth() / 2.325)) {
                    JFrame freg = new JFrame("Control");
                    freg.setBounds(Background.getX() + Background.getWidth() / 4, Background.getY() + Background.getHeight() / 4, Background.getWidth() / 2, Background.getHeight() / 2);
                    freg.setLayout(new GridLayout(12, 2));

                    comlbl1[0] = new JLabel("Input :");
                    comlbl1[1] = new JLabel("OP Code :");
                    comlbl1[2] = new JLabel("Output :");
                    comlbl1[3] = new JLabel("Register Destination :");
                    comlbl1[4] = new JLabel("Jump :");
                    comlbl1[5] = new JLabel("Branch :");
                    comlbl1[6] = new JLabel("Memory Read :");
                    comlbl1[7] = new JLabel("Memory to Register :");
                    comlbl1[8] = new JLabel("AluOP :");
                    comlbl1[9] = new JLabel("Memory Write :");
                    comlbl1[10] = new JLabel("Alu Source :");
                    comlbl1[11] = new JLabel("Register Write :");

                    comlbl2[0] = new JLabel();
                    comlbl2[1] = new JLabel(InstructionMemo1);
                    comlbl2[2] = new JLabel();
                    comlbl2[3] = new JLabel(Controlo1);
                    comlbl2[4] = new JLabel(Controlo2);
                    comlbl2[5] = new JLabel(Controlo3);
                    comlbl2[6] = new JLabel(Controlo4);
                    comlbl2[7] = new JLabel(Controlo5);
                    comlbl2[8] = new JLabel(Controlo6);
                    comlbl2[9] = new JLabel(Controlo7);
                    comlbl2[10] = new JLabel(Controlo8);
                    comlbl2[11] = new JLabel(Controlo9);

                    freg.add(comlbl1[0]);
                    freg.add(comlbl2[0]);
                    freg.add(comlbl1[1]);
                    freg.add(comlbl2[1]);
                    freg.add(comlbl1[2]);
                    freg.add(comlbl2[2]);
                    freg.add(comlbl1[3]);
                    freg.add(comlbl2[3]);
                    freg.add(comlbl1[4]);
                    freg.add(comlbl2[4]);
                    freg.add(comlbl1[5]);
                    freg.add(comlbl2[5]);
                    freg.add(comlbl1[6]);
                    freg.add(comlbl2[6]);
                    freg.add(comlbl1[7]);
                    freg.add(comlbl2[7]);
                    freg.add(comlbl1[8]);
                    freg.add(comlbl2[8]);
                    freg.add(comlbl1[9]);
                    freg.add(comlbl2[9]);
                    freg.add(comlbl1[10]);
                    freg.add(comlbl2[10]);
                    freg.add(comlbl1[11]);
                    freg.add(comlbl2[11]);

                    freg.setVisible(true);

                } else if ((e.getY() > Background.getHeight() / 2.1 && e.getY() < Background.getHeight() / 1.62) && (e.getX() > Background.getWidth() / 20.2 && e.getX() < Background.getWidth() / 12.49)) {
                    JFrame freg = new JFrame("PC");
                    freg.setBounds(Background.getX() + Background.getWidth() / 4, Background.getY() + Background.getHeight() / 4, Background.getWidth() / 2, Background.getHeight() / 2);
                    freg.setLayout(new GridLayout(4, 2));

                    comlbl1[0] = new JLabel("Input :");
                    comlbl1[1] = new JLabel("Next Instruction Address :");
                    comlbl1[2] = new JLabel("Output :");
                    comlbl1[3] = new JLabel("Current Instruction Address :");

                    comlbl2[0] = new JLabel();
                    comlbl2[1] = new JLabel(PCi);
                    comlbl2[2] = new JLabel();
                    comlbl2[3] = new JLabel(InstructionMemi);

                    freg.add(comlbl1[0]);
                    freg.add(comlbl2[0]);
                    freg.add(comlbl1[1]);
                    freg.add(comlbl2[1]);
                    freg.add(comlbl1[2]);
                    freg.add(comlbl2[2]);
                    freg.add(comlbl1[3]);
                    freg.add(comlbl2[3]);

                    freg.setVisible(true);

                } else if ((e.getY() > Background.getHeight() / 1.715 && e.getY() < Background.getHeight() / 1.235) && (e.getX() > Background.getWidth() / 1.325 && e.getX() < Background.getWidth() / 1.15)) {
                    JFrame freg = new JFrame("Data Memory");
                    freg.setBounds(Background.getX() + Background.getWidth() / 4, Background.getY() + Background.getHeight() / 4, Background.getWidth() / 2, Background.getHeight() / 2);
                    freg.setLayout(new GridLayout(7, 2));
                    comlbl1[0] = new JLabel("Input :");
                    comlbl1[1] = new JLabel("Alu Result :");
                    comlbl1[2] = new JLabel("Reg Data:");
                    comlbl1[3] = new JLabel("Mem Write :");
                    comlbl1[4] = new JLabel("Mem Read :");
                    comlbl1[5] = new JLabel("Output :");
                    comlbl1[6] = new JLabel("Read Data :");

                    comlbl2[0] = new JLabel();
                    comlbl2[1] = new JLabel(Aluo2);
                    comlbl2[2] = new JLabel(Registero2);
                    comlbl2[3] = new JLabel(Controlo7);
                    comlbl2[4] = new JLabel(Controlo4);
                    comlbl2[5] = new JLabel();
                    comlbl2[6] = new JLabel(DataMemo);

                    freg.add(comlbl1[0]);
                    freg.add(comlbl2[0]);
                    freg.add(comlbl1[1]);
                    freg.add(comlbl2[1]);
                    freg.add(comlbl1[2]);
                    freg.add(comlbl2[2]);
                    freg.add(comlbl1[3]);
                    freg.add(comlbl2[3]);
                    freg.add(comlbl1[4]);
                    freg.add(comlbl2[4]);
                    freg.add(comlbl1[5]);
                    freg.add(comlbl2[5]);
                    freg.add(comlbl1[6]);
                    freg.add(comlbl2[6]);

                    freg.setVisible(true);

                } else if ((e.getY() > Background.getHeight() / 1.29 && e.getY() < Background.getHeight() / 1.1) && (e.getX() > Background.getWidth() / 2.24 && e.getX() < Background.getWidth() / 1.96)) {
                    JFrame freg = new JFrame("Sign Extendor");
                    freg.setLayout(new GridLayout(4, 2));

                    comlbl1[0] = new JLabel("Input :");
                    comlbl1[1] = new JLabel("16-bit Constant :");
                    comlbl1[2] = new JLabel("Output :");
                    comlbl1[3] = new JLabel("32-bit Constant :");

                    comlbl2[0] = new JLabel();
                    comlbl2[1] = new JLabel(InstructionMemo8);
                    comlbl2[2] = new JLabel();
                    comlbl2[3] = new JLabel(SignExtendoro);

                    freg.add(comlbl1[0]);
                    freg.add(comlbl2[0]);
                    freg.add(comlbl1[1]);
                    freg.add(comlbl2[1]);
                    freg.add(comlbl1[2]);
                    freg.add(comlbl2[2]);
                    freg.add(comlbl1[3]);
                    freg.add(comlbl2[3]);

                    freg.setBounds(Background.getX() + Background.getWidth() / 4, Background.getY() + Background.getHeight() / 4, Background.getWidth() / 2, Background.getHeight() / 2);

                    freg.setVisible(true);

                } else if ((e.getY() > Background.getHeight() / 1.265 && e.getY() < Background.getHeight() / 1.09) && (e.getX() > Background.getWidth() / 1.68 && e.getX() < Background.getWidth() / 1.5)) {
                    JFrame freg = new JFrame("ALU Control");
                    freg.setBounds(Background.getX() + Background.getWidth() / 4, Background.getY() + Background.getHeight() / 4, Background.getWidth() / 2, Background.getHeight() / 2);
                    freg.setLayout(new GridLayout(5, 2));

                    comlbl1[0] = new JLabel("Input :");
                    comlbl1[1] = new JLabel("Function Code :");
                    comlbl1[2] = new JLabel("AluOP :");
                    comlbl1[3] = new JLabel("Output :");
                    comlbl1[4] = new JLabel("ALU Control Signal :");

                    comlbl2[0] = new JLabel();
                    comlbl2[1] = new JLabel(InstructionMemo6);
                    comlbl2[2] = new JLabel(Controlo6);
                    comlbl2[3] = new JLabel();
                    comlbl2[4] = new JLabel(ALUControlo);

                    freg.add(comlbl1[0]);
                    freg.add(comlbl2[0]);
                    freg.add(comlbl1[1]);
                    freg.add(comlbl2[1]);
                    freg.add(comlbl1[2]);
                    freg.add(comlbl2[2]);
                    freg.add(comlbl1[3]);
                    freg.add(comlbl2[3]);
                    freg.add(comlbl1[4]);
                    freg.add(comlbl2[4]);

                    freg.setVisible(true);

                } else if ((e.getY() > Background.getHeight() / 1.839 && e.getY() < Background.getHeight() / 1.3913) && (e.getX() > Background.getWidth() / 1.58 && e.getX() < Background.getWidth() / 1.38)) {
                    JFrame freg = new JFrame("ALU");
                    freg.setBounds(Background.getX() + Background.getWidth() / 4, Background.getY() + Background.getHeight() / 4, Background.getWidth() / 2, Background.getHeight() / 2);
                    freg.setLayout(new GridLayout(7, 2));

                    comlbl1[0] = new JLabel("Input :");
                    comlbl1[1] = new JLabel("Reg 1 :");
                    comlbl1[2] = new JLabel("Mux Output :");
                    comlbl1[3] = new JLabel("Alu Control :");
                    comlbl1[4] = new JLabel("Output :");
                    comlbl1[5] = new JLabel("Zero Flag :");
                    comlbl1[6] = new JLabel("Alu Result :");

                    comlbl2[0] = new JLabel();
                    comlbl2[1] = new JLabel(Registero1);
                    comlbl2[2] = new JLabel(M2o);
                    comlbl2[3] = new JLabel(ALUControlo);
                    comlbl2[4] = new JLabel();
                    comlbl2[5] = new JLabel(Aluo1);
                    comlbl2[6] = new JLabel(Aluo2);

                    freg.add(comlbl1[0]);
                    freg.add(comlbl2[0]);
                    freg.add(comlbl1[1]);
                    freg.add(comlbl2[1]);
                    freg.add(comlbl1[2]);
                    freg.add(comlbl2[2]);
                    freg.add(comlbl1[3]);
                    freg.add(comlbl2[3]);
                    freg.add(comlbl1[4]);
                    freg.add(comlbl2[4]);
                    freg.add(comlbl1[5]);
                    freg.add(comlbl2[5]);
                    freg.add(comlbl1[6]);
                    freg.add(comlbl2[6]);

                    freg.setVisible(true);

                } else if ((e.getY() > Background.getHeight() / 14.54 && e.getY() < Background.getHeight() / 4.1) && (e.getX() > Background.getWidth() / 6.77 && e.getX() < Background.getWidth() / 4.95)) {
                    JFrame freg = new JFrame("Adder(1)");
                    freg.setBounds(Background.getX() + Background.getWidth() / 4, Background.getY() + Background.getHeight() / 4, Background.getWidth() / 2, Background.getHeight() / 2);
                    freg.setLayout(new GridLayout(5, 2));

                    comlbl1[0] = new JLabel("Input :");
                    comlbl1[1] = new JLabel("PC :");
                    comlbl1[2] = new JLabel("4 :");
                    comlbl1[3] = new JLabel("Output :");
                    comlbl1[4] = new JLabel("PC +4 :");

                    comlbl2[0] = new JLabel();
                    comlbl2[1] = new JLabel(InstructionMemi);
                    comlbl2[2] = new JLabel("4");
                    comlbl2[3] = new JLabel();
                    comlbl2[4] = new JLabel(Add1o);

                    freg.add(comlbl1[0]);
                    freg.add(comlbl2[0]);
                    freg.add(comlbl1[1]);
                    freg.add(comlbl2[1]);
                    freg.add(comlbl1[2]);
                    freg.add(comlbl2[2]);
                    freg.add(comlbl1[3]);
                    freg.add(comlbl2[3]);
                    freg.add(comlbl1[4]);
                    freg.add(comlbl2[4]);

                    freg.setVisible(true);

                } else if ((e.getY() > Background.getHeight() / 8.1 && e.getY() < Background.getHeight() / 3.4) && (e.getX() > Background.getWidth() / 1.55 && e.getX() < Background.getWidth() / 1.36)) {
                    JFrame freg = new JFrame("Adder(2)");
                    freg.setBounds(Background.getX() + Background.getWidth() / 4, Background.getY() + Background.getHeight() / 4, Background.getWidth() / 2, Background.getHeight() / 2);
                    freg.setLayout(new GridLayout(5, 2));

                    comlbl1[0] = new JLabel("Input :");
                    comlbl1[1] = new JLabel("PC +4 :");
                    comlbl1[2] = new JLabel("Branch Address :");
                    comlbl1[3] = new JLabel("Output :");
                    comlbl1[4] = new JLabel("Adding Result :");

                    comlbl2[0] = new JLabel();
                    comlbl2[1] = new JLabel(Add1o);
                    comlbl2[2] = new JLabel(SL2o);
                    comlbl2[3] = new JLabel();
                    comlbl2[4] = new JLabel(Add2o);

                    freg.add(comlbl1[0]);
                    freg.add(comlbl2[0]);
                    freg.add(comlbl1[1]);
                    freg.add(comlbl2[1]);
                    freg.add(comlbl1[2]);
                    freg.add(comlbl2[2]);
                    freg.add(comlbl1[3]);
                    freg.add(comlbl2[3]);
                    freg.add(comlbl1[4]);
                    freg.add(comlbl2[4]);

                    freg.setVisible(true);

                } else if ((e.getY() > Background.getHeight() / 32 && e.getY() < Background.getHeight() / 8) && (e.getX() > Background.getWidth() / 3.1 && e.getX() < Background.getWidth() / 2.63)) {
                    JFrame freg = new JFrame("Shift Left 2(1)");
                    freg.setBounds(Background.getX() + Background.getWidth() / 4, Background.getY() + Background.getHeight() / 4, Background.getWidth() / 2, Background.getHeight() / 2);
                    freg.setLayout(new GridLayout(4, 2));

                    comlbl1[0] = new JLabel("Input :");
                    comlbl1[1] = new JLabel("Jump Address :");
                    comlbl1[2] = new JLabel("Output :");
                    comlbl1[3] = new JLabel("Output :");

                    comlbl2[0] = new JLabel();
                    comlbl2[1] = new JLabel(InstructionMemo7);
                    comlbl2[2] = new JLabel();
                    comlbl2[3] = new JLabel(SL1o);

                    freg.add(comlbl1[0]);
                    freg.add(comlbl2[0]);
                    freg.add(comlbl1[1]);
                    freg.add(comlbl2[1]);
                    freg.add(comlbl1[2]);
                    freg.add(comlbl2[2]);
                    freg.add(comlbl1[3]);
                    freg.add(comlbl2[3]);

                    freg.setVisible(true);

                } else if ((e.getY() > Background.getHeight() / 4.58 && e.getY() < Background.getHeight() / 3.25) && (e.getX() > Background.getWidth() / 1.73 && e.getX() < Background.getWidth() / 1.583)) {
                    JFrame freg = new JFrame("Shift Left 2(2)");
                    freg.setBounds(Background.getX() + Background.getWidth() / 4, Background.getY() + Background.getHeight() / 4, Background.getWidth() / 2, Background.getHeight() / 2);
                    freg.setLayout(new GridLayout(4, 2));

                    comlbl1[0] = new JLabel("Input :");
                    comlbl1[1] = new JLabel("Sign Extended :");
                    comlbl1[2] = new JLabel("Output :");
                    comlbl1[3] = new JLabel("Output :");

                    comlbl2[0] = new JLabel();
                    comlbl2[1] = new JLabel(SignExtendoro);
                    comlbl2[2] = new JLabel();
                    comlbl2[3] = new JLabel(SL2o);

                    freg.add(comlbl1[0]);
                    freg.add(comlbl2[0]);
                    freg.add(comlbl1[1]);
                    freg.add(comlbl2[1]);
                    freg.add(comlbl1[2]);
                    freg.add(comlbl2[2]);
                    freg.add(comlbl1[3]);
                    freg.add(comlbl2[3]);
                    freg.setVisible(true);

                } else if ((e.getY() > Background.getHeight() / 3.76 && e.getY() < Background.getHeight() / 3) && (e.getX() > Background.getWidth() / 1.34 && e.getX() < Background.getWidth() / 1.268)) {
                    JFrame freg = new JFrame("AND Gate");
                    freg.setBounds(Background.getX() + Background.getWidth() / 4, Background.getY() + Background.getHeight() / 4, Background.getWidth() / 2, Background.getHeight() / 2);
                    freg.setLayout(new GridLayout(5, 2));

                    comlbl1[0] = new JLabel("Input :");
                    comlbl1[1] = new JLabel("Zero Flag :");
                    comlbl1[2] = new JLabel("Branch Control :");
                    comlbl1[3] = new JLabel("Output :");
                    comlbl1[4] = new JLabel("Anding Result :");

                    comlbl2[0] = new JLabel();
                    comlbl2[1] = new JLabel(Aluo1);
                    comlbl2[2] = new JLabel(Controlo3);
                    comlbl2[3] = new JLabel();
                    comlbl2[4] = new JLabel(Ando);

                    freg.add(comlbl1[0]);
                    freg.add(comlbl2[0]);
                    freg.add(comlbl1[1]);
                    freg.add(comlbl2[1]);
                    freg.add(comlbl1[2]);
                    freg.add(comlbl2[2]);
                    freg.add(comlbl1[3]);
                    freg.add(comlbl2[3]);
                    freg.add(comlbl1[4]);
                    freg.add(comlbl2[4]);

                    freg.setVisible(true);

                } else if ((e.getY() > Background.getHeight() / 1.63589 && e.getY() < Background.getHeight() / 1.3991) && (e.getX() > Background.getWidth() / 2.7586 && e.getX() < Background.getWidth() / 2.5651)) {
                    JFrame freg = new JFrame("Mux(1)");
                    freg.setBounds(Background.getX() + Background.getWidth() / 4, Background.getY() + Background.getHeight() / 4, Background.getWidth() / 2, Background.getHeight() / 2);
                    freg.setLayout(new GridLayout(6, 2));
                    comlbl1[0] = new JLabel("Input :");
                    comlbl1[1] = new JLabel("Reg2 :");
                    comlbl1[2] = new JLabel("Reg3:");
                    comlbl1[3] = new JLabel("Selector :");
                    comlbl1[4] = new JLabel("Output :");
                    comlbl1[5] = new JLabel("Output :");

                    comlbl2[0] = new JLabel();
                    comlbl2[1] = new JLabel(InstructionMemo3);
                    comlbl2[2] = new JLabel(InstructionMemo4);
                    comlbl2[3] = new JLabel(Controlo1);
                    comlbl2[4] = new JLabel();
                    comlbl2[5] = new JLabel(M1o);

                    freg.add(comlbl1[0]);
                    freg.add(comlbl2[0]);
                    freg.add(comlbl1[1]);
                    freg.add(comlbl2[1]);
                    freg.add(comlbl1[2]);
                    freg.add(comlbl2[2]);
                    freg.add(comlbl1[3]);
                    freg.add(comlbl2[3]);
                    freg.add(comlbl1[4]);
                    freg.add(comlbl2[4]);
                    freg.add(comlbl1[5]);
                    freg.add(comlbl2[5]);

                    freg.setVisible(true);

                } else if ((e.getY() > Background.getHeight() / 1.5688 && e.getY() < Background.getHeight() / 1.3497) && (e.getX() > Background.getWidth() / 1.7066 && e.getX() < Background.getWidth() / 1.6326)) {
                    JFrame freg = new JFrame("Mux(2)");
                    freg.setBounds(Background.getX() + Background.getWidth() / 4, Background.getY() + Background.getHeight() / 4, Background.getWidth() / 2, Background.getHeight() / 2);
                    freg.setLayout(new GridLayout(6, 2));
                    comlbl1[0] = new JLabel("Input :");
                    comlbl1[1] = new JLabel("Reg :");
                    comlbl1[2] = new JLabel("Sign Extended:");
                    comlbl1[3] = new JLabel("Selector :");
                    comlbl1[4] = new JLabel("Output :");
                    comlbl1[5] = new JLabel("Ouptut :");

                    comlbl2[0] = new JLabel();
                    comlbl2[1] = new JLabel(Registero2);
                    comlbl2[2] = new JLabel(SignExtendoro);
                    comlbl2[3] = new JLabel(Controlo8);
                    comlbl2[4] = new JLabel();
                    comlbl2[5] = new JLabel(M2o);

                    freg.add(comlbl1[0]);
                    freg.add(comlbl2[0]);
                    freg.add(comlbl1[1]);
                    freg.add(comlbl2[1]);
                    freg.add(comlbl1[2]);
                    freg.add(comlbl2[2]);
                    freg.add(comlbl1[3]);
                    freg.add(comlbl2[3]);
                    freg.add(comlbl1[4]);
                    freg.add(comlbl2[4]);
                    freg.add(comlbl1[5]);
                    freg.add(comlbl2[5]);

                    freg.setVisible(true);

                } else if ((e.getY() > Background.getHeight() / 1.5688 && e.getY() < Background.getHeight() / 1.3497) && (e.getX() > Background.getWidth() / 1.10726 && e.getX() < Background.getWidth() / 1.07563)) {
                    JFrame freg = new JFrame("Mux(3)");
                    freg.setBounds(Background.getX() + Background.getWidth() / 4, Background.getY() + Background.getHeight() / 4, Background.getWidth() / 2, Background.getHeight() / 2);

                    freg.setLayout(new GridLayout(6, 2));
                    comlbl1[0] = new JLabel("Input :");
                    comlbl1[1] = new JLabel("Read Data :");
                    comlbl1[2] = new JLabel("Alu Result:");
                    comlbl1[3] = new JLabel("Selector :");
                    comlbl1[4] = new JLabel("Output :");
                    comlbl1[5] = new JLabel("Ouptut :");

                    comlbl2[0] = new JLabel();
                    comlbl2[1] = new JLabel(DataMemo);
                    comlbl2[2] = new JLabel(Aluo2);
                    comlbl2[3] = new JLabel(Controlo5);
                    comlbl2[4] = new JLabel();
                    comlbl2[5] = new JLabel(M3o);

                    freg.add(comlbl1[0]);
                    freg.add(comlbl2[0]);
                    freg.add(comlbl1[1]);
                    freg.add(comlbl2[1]);
                    freg.add(comlbl1[2]);
                    freg.add(comlbl2[2]);
                    freg.add(comlbl1[3]);
                    freg.add(comlbl2[3]);
                    freg.add(comlbl1[4]);
                    freg.add(comlbl2[4]);
                    freg.add(comlbl1[5]);
                    freg.add(comlbl2[5]);

                    freg.setVisible(true);

                } else if ((e.getY() > Background.getHeight() / 11.5301 && e.getY() < Background.getHeight() / 4.4101) && (e.getX() > Background.getWidth() / 1.21212 && e.getX() < Background.getWidth() / 1.1743)) {
                    JFrame freg = new JFrame("Mux(4)");
                    freg.setBounds(Background.getX() + Background.getWidth() / 4, Background.getY() + Background.getHeight() / 4, Background.getWidth() / 2, Background.getHeight() / 2);

                    freg.setLayout(new GridLayout(6, 2));
                    comlbl1[0] = new JLabel("Input :");
                    comlbl1[1] = new JLabel("PC +4 :");
                    comlbl1[2] = new JLabel("Branch Address :");
                    comlbl1[3] = new JLabel("Selector :");
                    comlbl1[4] = new JLabel("Output :");
                    comlbl1[5] = new JLabel("Ouptut :");

                    comlbl2[0] = new JLabel();
                    comlbl2[1] = new JLabel(Add1o);
                    comlbl2[2] = new JLabel(Add2o);
                    comlbl2[3] = new JLabel(Ando);
                    comlbl2[4] = new JLabel();
                    comlbl2[5] = new JLabel(M4o);

                    freg.add(comlbl1[0]);
                    freg.add(comlbl2[0]);
                    freg.add(comlbl1[1]);
                    freg.add(comlbl2[1]);
                    freg.add(comlbl1[2]);
                    freg.add(comlbl2[2]);
                    freg.add(comlbl1[3]);
                    freg.add(comlbl2[3]);
                    freg.add(comlbl1[4]);
                    freg.add(comlbl2[4]);
                    freg.add(comlbl1[5]);
                    freg.add(comlbl2[5]);

                    freg.setVisible(true);

                } else if ((e.getY() > Background.getHeight() / 11.5301 && e.getY() < Background.getHeight() / 4.4101) && (e.getX() > Background.getWidth() / 1.10726 && e.getX() < Background.getWidth() / 1.07563)) {
                    JFrame freg = new JFrame("Mux(5)");
                    freg.setBounds(Background.getX() + Background.getWidth() / 4, Background.getY() + Background.getHeight() / 4, Background.getWidth() / 2, Background.getHeight() / 2);

                    freg.setLayout(new GridLayout(6, 2));
                    comlbl1[0] = new JLabel("Input :");
                    comlbl1[1] = new JLabel("PC +4 :");
                    comlbl1[2] = new JLabel("M4o :");
                    comlbl1[3] = new JLabel("Selector :");
                    comlbl1[4] = new JLabel("Output :");
                    comlbl1[5] = new JLabel("Ouptut :");

                    comlbl2[0] = new JLabel();
                    comlbl2[1] = new JLabel(PC.PC4Bits+SL1o);
                    comlbl2[2] = new JLabel(M4o);
                    comlbl2[3] = new JLabel(Controlo2);
                    comlbl2[4] = new JLabel();
                    comlbl2[5] = new JLabel(M5o);

                    freg.add(comlbl1[0]);
                    freg.add(comlbl2[0]);
                    freg.add(comlbl1[1]);
                    freg.add(comlbl2[1]);
                    freg.add(comlbl1[2]);
                    freg.add(comlbl2[2]);
                    freg.add(comlbl1[3]);
                    freg.add(comlbl2[3]);
                    freg.add(comlbl1[4]);
                    freg.add(comlbl2[4]);
                    freg.add(comlbl1[5]);
                    freg.add(comlbl2[5]);

                    freg.setVisible(true);

                }

            }
        });
    }
}
