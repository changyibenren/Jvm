package org.itstack.demo.jvm.instructions.base;

import org.itstack.demo.jvm.runtime.Frame;
import org.itstack.demo.jvm.runtime.OperandStack;


public class InstructionNoOperands implements Instruction {
    //无操作数指令
    @Override
    public void fetchOperands(BytecodeReader reader) {

    }

    @Override
    public void execute(Frame frame) {

    }

    protected void _dcmp(Frame frame, boolean flag) {
        OperandStack stack = frame.operandStack();
        double v2 = stack.popDouble();
        double v1 = stack.popDouble();
        if (v1 > v2) {
            stack.pushInt(1);
            return;
        }
        if (v1 == v2) {
            stack.pushInt(0);
            return;
        }
        if (v1 < v2) {
            stack.pushInt(-1);
            return;
        }
        if (flag) {
            stack.pushInt(1);
            return;
        }
        stack.pushInt(-1);
    }

    protected void _fcmp(Frame frame, boolean flag) {
        OperandStack stack = frame.operandStack();
        double v2 = stack.popFloat();
        double v1 = stack.popFloat();
        if (v1 > v2) {
            stack.pushInt(1);
            return;
        }
        if (v1 == v2) {
            stack.pushInt(0);
            return;
        }
        if (v1 < v2) {
            stack.pushInt(-1);
            return;
        }
        if (flag) {
            stack.pushInt(1);
            return;
        }
        stack.pushInt(-1);
    }

    protected void _astore(Frame frame, int id) {
        Object ref = frame.operandStack().popRef();
        frame.localVars().setRef(id, ref);
    }

    protected void _dstore(Frame frame, int id) {
        double val = frame.operandStack().popDouble();
        frame.localVars().setDouble(id, val);
    }

    protected void _fstore(Frame frame, int id) {
        float val = frame.operandStack().popFloat();
        frame.localVars().setFloat(id, val);
    }

    protected void _istore(Frame frame, int id) {
        int val = frame.operandStack().popInt();
        frame.localVars().setInt(id, val);
    }

    protected void _lstore(Frame frame, int id) {
        long val = frame.operandStack().popLong();
        frame.localVars().setLong(id, val);
    }
}
