package org.itstack.demo.jvm.instructions.load.lload;

import org.itstack.demo.jvm.instructions.base.InstructionNoOperands;
import org.itstack.demo.jvm.runtime.Frame;


public class LLOAD0 extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        Long val = frame.localVars().getLong(0);
        frame.operandStack().pushLong(val);
    }

}
