package org.itstack.demo.jvm.instructions.push;

import org.itstack.demo.jvm.instructions.base.BytecodeReader;
import org.itstack.demo.jvm.instructions.base.Instruction;
import org.itstack.demo.jvm.runtime.Frame;
import org.itstack.demo.jvm.runtime.OperandStack;

//sipush指令从操作数中获取一个short型整数，扩展成int型，然后推入栈顶。
public class SIPUSH implements Instruction {
    private short val;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.val = reader.readByte();
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.operandStack();
        operandStack.pushInt(val);
    }
}
