package org.itstack.demo.jvm.instructions.comparsions.ifcond;

import org.itstack.demo.jvm.instructions.base.Instruction;
import org.itstack.demo.jvm.instructions.base.InstructionBranch;
import org.itstack.demo.jvm.runtime.Frame;

public class IFLT extends InstructionBranch {

    @Override
    public void execute(Frame frame) {
        int val = frame.operandStack().popInt();
        if (val < 0) {
            Instruction.branch(frame, this.offset);
        }
    }
}
