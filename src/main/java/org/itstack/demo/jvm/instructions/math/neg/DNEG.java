package org.itstack.demo.jvm.instructions.math.neg;

import org.itstack.demo.jvm.instructions.base.InstructionNoOperands;
import org.itstack.demo.jvm.runtime.Frame;
import org.itstack.demo.jvm.runtime.OperandStack;

//negate double
public class DNEG extends InstructionNoOperands {

    //栈顶double型数值取负，并且结果进栈。
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.operandStack();
        double val = stack.popDouble();
        stack.pushDouble(-val);
    }

}