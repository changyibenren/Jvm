package org.itstack.demo.jvm.instructions.load.lload;

import org.itstack.demo.jvm.instructions.base.InstructionIndex8;
import org.itstack.demo.jvm.runtime.Frame;


public class LLOAD extends InstructionIndex8 {

    //通过索引读取局部变量表，将局部变量表中的long型局部变量推入栈顶。
    @Override
    public void execute(Frame frame) {
        Long val = frame.localVars().getLong(this.id);
        frame.operandStack().pushLong(val);
    }

}
