package guiarch;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class LabelFrame extends JFrame {
    private JButton button1=new JButton("Submit");
    private JButton button2=new JButton ("Data Memory");
    private JTextField[] field=new JTextField[33];
    private JLabel[] regnames=new JLabel[33];
    private Container main=getContentPane();
    private Container centrePanel=new Container();
    private JPanel southPanel=new JPanel();
    public static int StartingLine=0;
    private Rectangle r;
    LabelFrame(){
        init();
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        setRegisters();
        StartingLine=Integer.parseInt(field[32].getText().trim());
        this.setVisible(false);
        MainPanel m=new MainPanel();
        m.setVisible(true);
        
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        JFrame dm1 = new JFrame("Data Memory");
        dm1.setLocation(r.width/3,r.height/3);
        dm1.setResizable(false);
        Container c1 = dm1.getContentPane();
        c1.setLayout(new GridLayout(3,2));
        JLabel lbl = new JLabel("Enter number of words to initialize: ");
        JTextField txt = new JTextField();
        JLabel lbl1 = new JLabel(" ");
        JLabel lbl2 = new JLabel(" ");
        JLabel lbl3 = new JLabel(" ");
        JLabel lbl4 = new JLabel(" ");
        c1.add(lbl1);
        c1.add(lbl2);
        c1.add(lbl);
        c1.add(txt);
        c1.add(lbl3);
        c1.add(lbl4);
        dm1.pack();
        dm1.setVisible(true);
      
        txt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = (txt.getText().isEmpty()) ? 0 : Integer.parseInt(txt.getText());
                JFrame dm2 = new JFrame("Data Memory");
                Container c = dm2.getContentPane();
                JPanel p1 = new JPanel(new GridLayout(x,4,r.width/40,r.height/50));
                JPanel p2 = new JPanel();
                JLabel lbls[] = new JLabel[x*2];
                JTextField txts[] = new JTextField[x*2];
                JButton btn = new JButton("Submit");
                
                dm2.setBounds((int)(r.width/3), (int)(r.height*0.125), r.width/3, (int)(r.height*0.725));
                c.add(p1);
                for(int i = 0; i < lbls.length; i++)
                {
                    lbls[i] = new JLabel("Address:");
                    i++;
                    lbls[i] = new JLabel("Values:");
                }
                for (int i = 0; i < txts.length; i++)
                    txts[i] = new JTextField();
                for (int i = 0; i < x*2; i++)
                {
                    p1.add(lbls[i]);
                    p1.add(txts[i]);
                }
                c.add(p2, BorderLayout.SOUTH);
                p2.add(btn);
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int mems [] = new int[x*2];
                        for(int i = 0; i < mems.length; i++)
                            mems[i] = Integer.parseInt(txts[i].getText());
                        DataMemory.initializeMemory(mems);
                        dm2.setVisible(false);
                    }
                });
                dm2.pack();
                dm2.setVisible(true);
                dm1.setVisible(false);
            }
        });
    }
    private void setRegisters(){
        for(int i=1;i<32;i++)
    RegFile.IntializeRegs(i, Integer.parseInt(field[i].getText()));
    }
    void init(){
    main.setLayout(new BorderLayout());
    main.add(centrePanel,BorderLayout.CENTER);
    main.add(southPanel,BorderLayout.SOUTH);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    r=GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
    this.setBounds(r);
    centrePanel.setLayout(new GridLayout(11,6));
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
    regnames[32] = new JLabel("Start Line No.");
    for(int i=0;i<33;i++){
        field[i]=new JTextField("0");
    }
    for(int i=0;i<33;i++){
     regnames[i].setFont(new Font("Tahoma", 1, 24));
     regnames[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
     field[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
     field[i].setFont(new Font("Tahoma", 1, 24));
     regnames[i].setHorizontalAlignment(SwingConstants.CENTER);
     field[i].setHorizontalAlignment(SwingConstants.CENTER);
     centrePanel.add(regnames[i]);
     centrePanel.add(field[i]);
    }
    button1.setText("Submit");
    button1.setHorizontalAlignment(SwingConstants.CENTER);
    button2.setHorizontalAlignment(SwingConstants.CENTER);
    button2.setFont(new Font("Tahoma", 0, 48));
    button1.setFont(new Font("Tahoma", 0, 48));
    button1.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
    button2.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
    
    southPanel.add(button2);
    southPanel.add(button1);
    }
   }
