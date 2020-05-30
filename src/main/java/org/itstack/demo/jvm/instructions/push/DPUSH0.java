package org.itstack.demo.jvm.instructions.push;

import org.itstack.demo.jvm.instructions.base.Instruction;
import org.itstack.demo.jvm.instructions.base.InstructionNoOperands;
import org.itstack.demo.jvm.runtime.Frame;
import org.itstack.demo.jvm.runtime.OperandStack;

public class DPUSH0 extends InstructionNoOperands {
    //把double类型的0推入操作数栈顶。
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.operandStack();
        operandStack.pushDouble(0.0);
    }
}
