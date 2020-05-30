package org.itstack.demo.jvm.instructions.control;

import org.itstack.demo.jvm.instructions.base.Instruction;
import org.itstack.demo.jvm.instructions.base.InstructionBranch;
import org.itstack.demo.jvm.runtime.Frame;

//branch always
public class GOTO extends InstructionBranch {

   // 无条件跳转。
    @Override
    public void execute(Frame frame) {
        Instruction.branch(frame, this.offset);
    }
}
