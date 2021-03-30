package guiarch;
import java.util.*;

class MemoryFields {

    private String byte4, byte3, byte2, byte1, word;
    private String address;

    public MemoryFields(String word, String address) {
        this.word = word;
        byte4 = word.substring(0, 8);
        byte3 = word.substring(8, 16);
        byte2 = word.substring(16, 24);
        byte1 = word.substring(24, 32);
        this.address = address;
    }

    public void setword(String str) {
        word = str;
        byte4 = str.substring(0, 8);
        byte3 = str.substring(8, 16);
        byte2 = str.substring(16, 24);
        byte1 = str.substring(24, 32);
    }
    
    public String getAddress(){
        return address;
    }

    public String getWord() {
        return word;
    }

    public String getByte1() {
        return byte1;
    }

    public void setByte1(String byte1) {
        this.byte1 = byte1;
    }

    public String getByte2() {
        return byte2;
    }
    
}

public class DataMemory {

    private static ArrayList<MemoryFields> memory = new ArrayList<MemoryFields>();
    private int memwrite, memread;
    private String address;
    private String opType;
    private String registerData;
    private String output;

    public String getOutput() {
        return output;
    }

    public DataMemory(int write, int read, String RegisterData, String address, String unsigned) {
        memwrite = write;
        memread = read;
        this.address = address;
        opType = unsigned;
        registerData = RegisterData;
        setOutput();
    }

    private void setOutput() {
        if (memread == 1) {
            switch (opType) {
                case "000":
                    output = loadWord();
                    break;
                case "001":
                    output = loadByte();
                    break;
                case "010":
                    output = loadByteUnsigned();
                    break;
            }
        } else if (memwrite == 1) {
            switch (opType) {
                case "011":
                    storeWord(registerData);
                    break;
                case "100":
                    storeByte(registerData);
            }

        }
    }

    private void storeWord(String str) {

        for (MemoryFields i : memory) {
            if (i.getAddress().equals(address)) {
                i.setword(str);
                return;
            }
        }
        memory.add(new MemoryFields(str, address));
    }

    private void storeByte(String str) {
        if (memwrite == 0) {
            return;
        }
        for (MemoryFields i : memory) {
            if (i.getAddress().equals(address)) {
                i.setword("000000000000000000000000" + str.substring(24, 32));
                return;
            }
        }
        memory.add(new MemoryFields("000000000000000000000000" + str.substring(24, 32), address));
    }

    private String loadWord() {
        if (memread == 1) {
            for (MemoryFields i : memory) {
                if (i.getAddress().equals(address)) {
                    return i.getWord();
                }
            }
        }
        return "error";
    }

    private String loadByte() {
        if (memread == 1) {
            for (MemoryFields i : memory) {
                if (i.getAddress().equals(address)) {
                    if (i.getWord().charAt(24) == '0') {
                        return ("000000000000000000000000" + (i.getByte1()));
                    } else {
                        return ("111111111111111111111111" + (i.getByte1()));
                    }
                }
            }
        }
        return "error";
    }

    private String loadByteUnsigned() {
        if (memread == 1) {
            for (MemoryFields i : memory) {
                if (i.getAddress().equals(address)) {
                    return ("000000000000000000000000" + (i.getByte1()));
                }
            }
        }
        return "error";
    }
    
    public static void initializeMemory(int []mems){
        String Address [] = new String[mems.length/2];
        String Values [] = new String[mems.length/2];
        int j = 0;
        for (int i = 0; i < mems.length/2; i++)
        {
            Address[i] = Utility.ReqBits(Integer.toBinaryString(mems[j]),32);
            j++;
            Values[i] = Utility.ReqBits(Integer.toBinaryString(mems[j]),32);
            j++;
        }
        memory.add(new MemoryFields(Values[0], Address[0]));
        for (int i = 1; i < Values.length; i++)
        {
            boolean h = false;
            for(MemoryFields k : memory)
            {
                if(k.getAddress().equals(Address[i]))
                {
                    k.setword(Values[i]);
                    h = true;
                }  
            }
            if(h);
            else
                memory.add(new MemoryFields(Values[i], Address[i]));
        }
    }
}

