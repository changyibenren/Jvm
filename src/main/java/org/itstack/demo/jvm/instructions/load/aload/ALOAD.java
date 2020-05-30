package org.itstack.demo.jvm.instructions.load.aload;

import org.itstack.demo.jvm.instructions.base.InstructionIndex8;
import org.itstack.demo.jvm.runtime.Frame;

//load reference from local variable
public class ALOAD extends InstructionIndex8 {

    //通过索引读取局部变量表，将局部变量表中的引用型局部变量推入栈顶。
    @Override
    public void execute(Frame frame) {
        Object ref = frame.localVars().getRef(this.id);
        frame.operandStack().pushRef(ref);
    }

}
