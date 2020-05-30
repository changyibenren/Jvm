package org.itstack.demo.jvm.instructions.stores.istore;

import org.itstack.demo.jvm.instructions.base.InstructionNoOperands;
import org.itstack.demo.jvm.runtime.Frame;

public class ISTORE_1 extends InstructionNoOperands {

    //将栈顶int型数值存入第二个局部变量，从0开始计数
    @Override
    public void execute(Frame frame) {
        _istore(frame, 1);
    }

}

