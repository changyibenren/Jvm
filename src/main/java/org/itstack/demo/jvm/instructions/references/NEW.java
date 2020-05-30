package org.itstack.demo.jvm.instructions.references;

import org.itstack.demo.jvm.instructions.base.InstructionIndex16;
import org.itstack.demo.jvm.runtime.methodarea.Class;
import org.itstack.demo.jvm.runtime.Frame;
import org.itstack.demo.jvm.runtime.constantpool.ClassRef;
import org.itstack.demo.jvm.runtime.constantpool.RunTimeConstantPool;

//创建一个对象，并且其引用进栈
public class NEW extends InstructionIndex16 {

    @Override
    public void execute(Frame frame) throws ClassNotFoundException {
        RunTimeConstantPool cp = frame.jMethod().clazz().constantPool();
        ClassRef classRef = (ClassRef) cp.getConstants(this.id-1);
        Class clazz = classRef.resolvedClass();
        if (clazz.isInterface() || clazz.isAbstract()) {
            throw new InstantiationError();
        }
        Object ref = clazz.newObject();
        frame.operandStack().pushRef(ref);
    }

}