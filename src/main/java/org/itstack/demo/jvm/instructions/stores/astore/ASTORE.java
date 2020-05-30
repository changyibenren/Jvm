package org.itstack.demo.jvm.instructions.stores.astore;

import org.itstack.demo.jvm.instructions.base.InstructionIndex8;
import org.itstack.demo.jvm.runtime.Frame;


//store reference into local variable
public class ASTORE extends InstructionIndex8 {

    //将栈顶数值ref存入当前frame的局部变量数组中指定下标id处的变量中，栈顶数值出栈。
    @Override
    public void execute(Frame frame) {
        _astore(frame, this.id);
    }

}
