package org.itstack.demo.jvm.instructions.load.fload;

import org.itstack.demo.jvm.instructions.base.InstructionNoOperands;
import org.itstack.demo.jvm.runtime.Frame;

public class FLOAD2 extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        Float val = frame.localVars().getFloat(2);
        frame.operandStack().pushFloat(val);
    }

}
