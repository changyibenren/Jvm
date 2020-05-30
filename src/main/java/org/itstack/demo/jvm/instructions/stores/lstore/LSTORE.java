package org.itstack.demo.jvm.instructions.stores.lstore;

import org.itstack.demo.jvm.instructions.base.InstructionIndex8;
import org.itstack.demo.jvm.runtime.Frame;

public class LSTORE extends InstructionIndex8 {

    @Override
    public void execute(Frame frame) {
        _lstore(frame, this.id);
    }

}
