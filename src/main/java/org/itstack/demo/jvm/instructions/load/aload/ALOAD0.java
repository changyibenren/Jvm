package org.itstack.demo.jvm.instructions.load.aload;

import org.itstack.demo.jvm.instructions.base.InstructionNoOperands;
import org.itstack.demo.jvm.runtime.Frame;

public class ALOAD0 extends InstructionNoOperands {

    //通过索引读取局部变量表第0号局部变量，将0号引用型局部变量推入栈顶。
    @Override
    public void execute(Frame frame) {
        Object ref = frame.localVars().getRef(0);
        frame.operandStack().pushRef(ref);
    }

}
