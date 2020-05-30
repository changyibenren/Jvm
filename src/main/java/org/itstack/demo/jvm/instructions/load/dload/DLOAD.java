package org.itstack.demo.jvm.instructions.load.dload;

import org.itstack.demo.jvm.instructions.base.InstructionIndex8;
import org.itstack.demo.jvm.runtime.Frame;

public class DLOAD extends InstructionIndex8 {

    //通过索引读取局部变量表，将局部变量表中的double型局部变量推入栈顶。
    @Override
    public void execute(Frame frame) {
        double val = frame.localVars().getDouble(this.id);
        frame.operandStack().pushRef(val);
    }

}