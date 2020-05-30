package org.itstack.demo.jvm.instructions.references;

import org.itstack.demo.jvm.instructions.base.InstructionIndex16;


import org.itstack.demo.jvm.runtime.Frame;
import org.itstack.demo.jvm.runtime.OperandStack;
import org.itstack.demo.jvm.runtime.constantpool.ClassRef;
import org.itstack.demo.jvm.runtime.methodarea.Class;
import org.itstack.demo.jvm.runtime.constantpool.RunTimeConstantPool;
//类型转换检查，如果该检查未通过将会抛出ClassCastException异常
public class CHECK_CAST extends InstructionIndex16 {

    @Override
    public void execute(Frame frame) throws ClassNotFoundException {
        OperandStack stack = frame.operandStack();
        Object ref = stack.popRef();
        stack.pushRef(ref);
        if (null == ref) return;
        RunTimeConstantPool cp = frame.jMethod().clazz().constantPool();
        ClassRef clazzRef = (ClassRef) cp.getConstants(this.id);
        Class clazz = clazzRef.resolvedClass();
    }

}
