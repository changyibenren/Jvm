package org.itstack.demo.jvm.instructions.stores.istore;

import org.itstack.demo.jvm.instructions.base.InstructionNoOperands;
import org.itstack.demo.jvm.runtime.Frame;

public class ISTORE_2 extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        _istore(frame, 2);
    }

}

