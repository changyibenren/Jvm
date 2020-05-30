package org.itstack.demo.jvm.instructions.stores.astore;

import org.itstack.demo.jvm.instructions.base.InstructionNoOperands;
import org.itstack.demo.jvm.runtime.Frame;

public class ASTORE_3 extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        _astore(frame, 3);
    }

}
