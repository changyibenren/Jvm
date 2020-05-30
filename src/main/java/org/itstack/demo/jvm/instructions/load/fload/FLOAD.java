package org.itstack.demo.jvm.instructions.load.fload;

import org.itstack.demo.jvm.instructions.base.InstructionIndex8;
import org.itstack.demo.jvm.runtime.Frame;

//load float from local variable
public class FLOAD extends InstructionIndex8 {

    //通过索引读取局部变量表，将局部变量表中的float型局部变量推入栈顶。
    @Override
    public void execute(Frame frame) {
        Float val = frame.localVars().getFloat(this.id);
        frame.operandStack().pushFloat(val);
    }

}
