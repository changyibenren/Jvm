package org.itstack.demo.jvm.instructions.math.rem;

import org.itstack.demo.jvm.instructions.base.InstructionNoOperands;
import org.itstack.demo.jvm.runtime.Frame;
import org.itstack.demo.jvm.runtime.OperandStack;

//remainder double
public class DREM extends InstructionNoOperands {

    //栈顶两个double型数值作取模运算，并且结果进栈。
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.operandStack();
        double v2 = stack.popDouble();
        double v1 = stack.popDouble();
        double res = v1 % v2;
        stack.pushDouble(res);
    }

}
