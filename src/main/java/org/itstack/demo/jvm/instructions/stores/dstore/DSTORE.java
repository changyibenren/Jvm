package org.itstack.demo.jvm.instructions.stores.dstore;

import org.itstack.demo.jvm.instructions.base.InstructionIndex8;
import org.itstack.demo.jvm.runtime.Frame;

public class DSTORE extends InstructionIndex8 {

    @Override
    public void execute(Frame frame) {
        _dstore(frame, this.id);
    }

}
