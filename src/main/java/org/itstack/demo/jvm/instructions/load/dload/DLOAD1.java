package org.itstack.demo.jvm.instructions.load.dload;

import org.itstack.demo.jvm.instructions.base.InstructionNoOperands;
import org.itstack.demo.jvm.runtime.Frame;

public class DLOAD1 extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        double val = frame.localVars().getDouble(1);
        frame.operandStack().pushRef(val);
    }

}
