package org.itstack.demo.jvm.instructions.stores.istore;

import org.itstack.demo.jvm.instructions.base.InstructionIndex8;
import org.itstack.demo.jvm.runtime.Frame;

public class ISTORE extends InstructionIndex8 {

    @Override
    public void execute(Frame frame) {
        _istore(frame, this.id);
    }

}