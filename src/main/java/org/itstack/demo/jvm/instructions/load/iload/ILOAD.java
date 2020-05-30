package org.itstack.demo.jvm.instructions.load.iload;

import org.itstack.demo.jvm.instructions.base.Instruction;
import org.itstack.demo.jvm.instructions.base.InstructionIndex8;
import org.itstack.demo.jvm.runtime.Frame;

public class ILOAD extends InstructionIndex8 {
    //通过索引读取局部变量表，将局部变量表中的int型局部变量推入栈顶。
    @Override
    public void execute(Frame frame) {
        int val = frame.localVars().getInt(this.id);
        frame.operandStack().pushInt(val);
    }
}
