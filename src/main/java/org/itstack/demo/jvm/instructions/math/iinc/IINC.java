package org.itstack.demo.jvm.instructions.math.iinc;

import org.itstack.demo.jvm.instructions.base.BytecodeReader;
import org.itstack.demo.jvm.instructions.base.Instruction;
import org.itstack.demo.jvm.runtime.Frame;
import org.itstack.demo.jvm.runtime.LocalVars;

//increment local variable by constants
public class IINC implements Instruction {

    public int id;
    public int constVal;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.id = reader.readByte();
        this.constVal = reader.readByte();
    }

    //指定int型变量增加指定值。
    @Override
    public void execute(Frame frame) {
        LocalVars vars = frame.localVars();
        int val = vars.getInt(this.id);
        val += this.constVal;
        vars.setInt(this.id, val);
    }
}
