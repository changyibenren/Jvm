package org.itstack.demo.jvm.instructions.swap;

import org.itstack.demo.jvm.instructions.base.InstructionNoOperands;
import org.itstack.demo.jvm.runtime.Frame;
import org.itstack.demo.jvm.runtime.OperandStack;
import org.itstack.demo.jvm.runtime.Slot;

public class SWAP extends InstructionNoOperands {
    @Override
    public void execute(Frame frame){
        OperandStack operandStack = frame.operandStack();
        Slot slot1 = operandStack.popSlot();
        Slot slot2 = operandStack.popSlot();
        operandStack.pushSlot(slot1);
        operandStack.pushSlot(slot2);

    }
}
