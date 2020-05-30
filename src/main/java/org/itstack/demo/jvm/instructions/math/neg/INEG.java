package org.itstack.demo.jvm.instructions.math.neg;

import org.itstack.demo.jvm.instructions.base.InstructionNoOperands;
import org.itstack.demo.jvm.runtime.Frame;
import org.itstack.demo.jvm.runtime.OperandStack;

//negate int
public class INEG extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.operandStack();
        int val = stack.popInt();
        stack.pushInt(-val);
    }

}

