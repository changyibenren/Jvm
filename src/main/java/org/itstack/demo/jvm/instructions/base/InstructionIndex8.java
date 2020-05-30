package org.itstack.demo.jvm.instructions.base;

import org.itstack.demo.jvm.runtime.Frame;

public class InstructionIndex8 implements Instruction {

    //单字节指令
    //表示局部变量表索引
    public int id;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.id = reader.readByte();

    }

    @Override
    public void execute(Frame frame) {

    }

    protected void _astore(Frame frame, int id) {
        Object ref = frame.operandStack().popRef();
        frame.localVars().setRef(id, ref);
    }

    protected void _dstore(Frame frame, int id) {
        double val = frame.operandStack().popDouble();
        frame.localVars().setDouble(id, val);
    }

    protected void _fstore(Frame frame, int id) {
        float val = frame.operandStack().popFloat();
        frame.localVars().setFloat(id, val);
    }

    protected void _istore(Frame frame, int id) {
        int val = frame.operandStack().popInt();
        frame.localVars().setInt(id, val);
    }

    protected void _lstore(Frame frame, int id) {
        long val = frame.operandStack().popLong();
        frame.localVars().setLong(id, val);
    }
}
