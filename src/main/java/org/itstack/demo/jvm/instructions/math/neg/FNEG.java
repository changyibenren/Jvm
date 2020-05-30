package org.itstack.demo.jvm.instructions.math.neg;

import org.itstack.demo.jvm.instructions.base.InstructionNoOperands;
import org.itstack.demo.jvm.runtime.Frame;
import org.itstack.demo.jvm.runtime.OperandStack;

//negate float
public class FNEG extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.operandStack();
        float val = stack.popFloat();
        stack.pushFloat(-val);
    }

}
