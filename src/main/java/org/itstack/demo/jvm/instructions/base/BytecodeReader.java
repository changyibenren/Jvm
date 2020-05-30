package org.itstack.demo.jvm.instructions.base;

public class BytecodeReader {

    // 存放字节码
    private byte[] codes;
    // 记录存取到了哪个字节码
    private int pc;

    // 为了避免每次解码指令都新创建一个BytecodeReader实例，所以定义一个Reset()方法
    public void reset(byte[] codes, int pc) {
        this.codes = codes;
        this.pc = pc;
    }

    // 获取pc
    public int pc() {
        return this.pc;
    }

    //读取一个字节
    public byte readByte() {
        byte code = this.codes[this.pc];
        this.pc++;
        return code;
    }

    //读取两个字节
    public short readShort() {
        byte byte1 = readByte();
        byte byte2 = readByte();
        return (short) ((byte1 << 8) | byte2);
    }

    //读取三个字节
    public int readInt() {
        int byte1 = this.readByte();
        int byte2 = this.readByte();
        int byte3 = this.readByte();
        int byte4 = this.readByte();
        return (byte1 << 24) | (byte2 << 16) | (byte3 << 8) | byte4;
    }

    //used by lookupswitch and tableswitch
    public int[] readInts(int n) {
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = this.readInt();
        }
        return ints;
    }

    //used by lookupswitch and tableswitcch
    public void skipPadding() {
        while (this.pc % 4 != 0) {
            this.readByte();
        }
    }
}
