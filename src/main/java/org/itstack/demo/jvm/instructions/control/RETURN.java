package org.itstack.demo.jvm.instructions.control;

import org.itstack.demo.jvm.instructions.base.InstructionNoOperands;
import org.itstack.demo.jvm.runtime.Frame;

public class RETURN extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        frame.thread().popFrame();
    }

}