package org.itstack.demo.jvm.instructions.load.iload;

import org.itstack.demo.jvm.instructions.base.InstructionNoOperands;
import org.itstack.demo.jvm.runtime.Frame;


public class ILOAD0 extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        int val = frame.localVars().getInt(0);
        frame.operandStack().pushInt(val);
    }
}
