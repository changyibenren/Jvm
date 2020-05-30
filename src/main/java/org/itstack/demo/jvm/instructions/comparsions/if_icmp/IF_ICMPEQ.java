package org.itstack.demo.jvm.instructions.comparsions.if_icmp;

import org.itstack.demo.jvm.instructions.base.Instruction;
import org.itstack.demo.jvm.instructions.base.InstructionBranch;
import org.itstack.demo.jvm.runtime.Frame;
import org.itstack.demo.jvm.runtime.OperandStack;


public class IF_ICMPEQ extends InstructionBranch {

    //比较栈顶两个int型数值大小，当相等时跳转。
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.operandStack();
        int val2 = stack.popInt();
        int val1 = stack.popInt();
        if (val1 == val2) {
            Instruction.branch(frame, this.offset);
        }
    }
    
}
