package org.itstack.demo.jvm.instructions.stores.dstore;

import org.itstack.demo.jvm.instructions.base.InstructionNoOperands;
import org.itstack.demo.jvm.runtime.Frame;

public class DSTORE_0 extends InstructionNoOperands {

    @Override
    public void execute(Frame frame) {
        _dstore(frame, 0);
    }

}
