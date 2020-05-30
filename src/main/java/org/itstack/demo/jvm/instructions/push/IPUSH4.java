package org.itstack.demo.jvm.instructions.push;

import org.itstack.demo.jvm.instructions.base.Instruction;
import org.itstack.demo.jvm.instructions.base.InstructionNoOperands;
import org.itstack.demo.jvm.runtime.Frame;
import org.itstack.demo.jvm.runtime.OperandStack;

public class IPUSH4 extends InstructionNoOperands {
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.operandStack();
        operandStack.pushInt(4);
    }
}
