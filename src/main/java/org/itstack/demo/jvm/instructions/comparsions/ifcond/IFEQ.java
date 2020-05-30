package org.itstack.demo.jvm.instructions.comparsions.ifcond;

import org.itstack.demo.jvm.instructions.base.Instruction;
import org.itstack.demo.jvm.instructions.base.InstructionBranch;
import org.itstack.demo.jvm.runtime.Frame;

public class IFEQ extends InstructionBranch {

    //当栈顶int型数值等于0时跳转。
    @Override
    public void execute(Frame frame) {
        int val = frame.operandStack().popInt();
        if (0 == val) {
            Instruction.branch(frame, this.offset);
        }
    }
}
