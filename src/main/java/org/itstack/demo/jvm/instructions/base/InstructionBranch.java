package org.itstack.demo.jvm.instructions.base;

import org.itstack.demo.jvm.runtime.Frame;
import org.itstack.demo.jvm.runtime.OperandStack;

public class InstructionBranch implements Instruction {
    //跳转指令，offset字段存储跳转偏移量
    protected int offset;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        //读取两个字节赋给offset
        this.offset = reader.readShort();
    }

    @Override
    public void execute(Frame frame) {

    }

    protected boolean _acmp(Frame frame){
        OperandStack stack = frame.operandStack();
        Object ref2 = stack.popRef();
        Object ref1 = stack.popRef();
        return ref1.equals(ref2);
    }

}
