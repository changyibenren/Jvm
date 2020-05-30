package org.itstack.demo.jvm.instructions.pop;


import org.itstack.demo.jvm.instructions.base.InstructionNoOperands;
import org.itstack.demo.jvm.runtime.Frame;
import org.itstack.demo.jvm.runtime.OperandStack;

public class POP extends InstructionNoOperands {
    @Override
    public void execute(Frame frame){
        OperandStack operandStack = frame.operandStack();
        operandStack.popSlot();
    }
}
