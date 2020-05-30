package org.itstack.demo.jvm.instructions.references;

import org.itstack.demo.jvm.instructions.base.InstructionIndex16;
import org.itstack.demo.jvm.runtime.Frame;
import org.itstack.demo.jvm.runtime.OperandStack;
import org.itstack.demo.jvm.runtime.constantpool.MethodRef;
import org.itstack.demo.jvm.runtime.constantpool.RunTimeConstantPool;

//调用对象实例方法
public class INVOKE_VIRTUAL extends InstructionIndex16 {

    @Override
    public void execute(Frame frame) {
        RunTimeConstantPool cp = frame.jMethod().clazz().constantPool();

        MethodRef methodRef = (MethodRef) cp.getConstants(this.id-1);
        if (methodRef.name.equals("println")) {
            OperandStack stack = frame.operandStack();
            switch (methodRef.descriptor) {
                case "(Z)V":
                    System.out.println(stack.popInt() != 0);
                    break;
                case "(C)V":
                    System.out.println(stack.popInt());
                    break;
                case "(I)V":
                case "(B)V":
                case "(S)V":
                    System.out.println(stack.popInt());
                    break;
                case "(F)V":
                    System.out.println(stack.popFloat());
                    break;
                case "(J)V":
                    System.out.println(stack.popLong());
                    break;
                case "(D)V":
                    System.out.println(stack.popDouble());
                    break;
                default:
                    System.out.println(methodRef.descriptor);
                    break;
            }
            stack.popRef();
        }
    }
}


