package org.itstack.demo.jvm.instructions.base;


import org.itstack.demo.jvm.runtime.Frame;

public class InstructionIndex16 implements Instruction {

    //双字节指令
    //表示常量池索引
    protected int id;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.id = reader.readShort();

    }

    @Override
    public void execute(Frame frame) throws ClassNotFoundException {

    }
}
