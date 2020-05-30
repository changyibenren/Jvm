package org.itstack.demo.jvm.instructions.load.aload;

import org.itstack.demo.jvm.instructions.base.InstructionNoOperands;
import org.itstack.demo.jvm.runtime.Frame;


public class ALOAD2 extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        Object ref = frame.localVars().getRef(2);
        frame.operandStack().pushRef(ref);
    }

}

