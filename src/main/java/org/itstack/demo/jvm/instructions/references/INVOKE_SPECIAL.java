package org.itstack.demo.jvm.instructions.references;

import org.itstack.demo.jvm.instructions.base.InstructionIndex16;
import org.itstack.demo.jvm.runtime.Frame;

//调用private方法和init方法
public class INVOKE_SPECIAL  extends InstructionIndex16 {

    @Override
    public void execute(Frame frame) {
        frame.operandStack().popRef();
    }

}