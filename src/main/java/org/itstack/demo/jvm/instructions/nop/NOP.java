package org.itstack.demo.jvm.instructions.nop;

import org.itstack.demo.jvm.instructions.base.Instruction;
import org.itstack.demo.jvm.instructions.base.InstructionNoOperands;
import org.itstack.demo.jvm.runtime.Frame;

public class NOP extends InstructionNoOperands {
    @Override
    public void execute(Frame frame){
        //do nothing
    }
}
