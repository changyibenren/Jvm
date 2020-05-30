package org.itstack.demo.jvm.instructions.push;

import org.itstack.demo.jvm.instructions.base.BytecodeReader;
import org.itstack.demo.jvm.instructions.base.Instruction;
import org.itstack.demo.jvm.runtime.Frame;
import org.itstack.demo.jvm.runtime.OperandStack;
import org.itstack.demo.jvm.runtime.Slot;

public class BIPUSH implements Instruction {
    private byte val;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.val = reader.readByte();
    }

    @Override
    public void execute(Frame frame){
        OperandStack operandStack = frame.operandStack();
        operandStack.pushInt(val);
    }

}
