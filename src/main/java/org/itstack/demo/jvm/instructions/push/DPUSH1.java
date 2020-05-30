package org.itstack.demo.jvm.instructions.push;

import org.itstack.demo.jvm.instructions.base.Instruction;
import org.itstack.demo.jvm.instructions.base.InstructionNoOperands;
import org.itstack.demo.jvm.runtime.Frame;
import org.itstack.demo.jvm.runtime.OperandStack;

public class DPUSH1 extends InstructionNoOperands {
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.operandStack();
        operandStack.pushDouble(1.0);
    }
}
