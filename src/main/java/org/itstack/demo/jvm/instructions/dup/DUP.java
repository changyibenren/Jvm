package org.itstack.demo.jvm.instructions.dup;


import org.itstack.demo.jvm.instructions.base.InstructionNoOperands;
import org.itstack.demo.jvm.runtime.Frame;
import org.itstack.demo.jvm.runtime.OperandStack;
import org.itstack.demo.jvm.runtime.Slot;

public class DUP extends InstructionNoOperands {
    @Override
    public void execute(Frame frame){
        OperandStack operandStack = frame.operandStack();
        Slot slot = operandStack.popSlot();
        operandStack.pushSlot(slot);
        operandStack.pushSlot(slot);
    }
}
