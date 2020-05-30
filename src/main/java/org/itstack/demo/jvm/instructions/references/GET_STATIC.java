package org.itstack.demo.jvm.instructions.references;

import com.sun.tools.classfile.*;
import org.itstack.demo.jvm.instructions.base.InstructionIndex16;
import org.itstack.demo.jvm.runtime.Frame;
import org.itstack.demo.jvm.runtime.OperandStack;
import org.itstack.demo.jvm.runtime.Slots;
import org.itstack.demo.jvm.runtime.constantpool.FieldRef;
import org.itstack.demo.jvm.runtime.constantpool.RunTimeConstantPool;
import org.itstack.demo.jvm.runtime.methodarea.JField;
import org.itstack.demo.jvm.runtime.methodarea.Class;

//获取指定类的静态域，并将其值压入栈顶
public class GET_STATIC extends InstructionIndex16 {

    @Override
    public void execute(Frame frame){
        RunTimeConstantPool runTimeConstantPool = frame.jMethod().clazz().constantPool();
        FieldRef ref = (FieldRef)runTimeConstantPool.getConstants(this.id-1);
        JField field = ref.resolvedField();
        if (!field.isStatic()){
            throw new IncompatibleClassChangeError();
        }
        Class clazz = field.clazz();
        String descriptor = field.descriptor();
        int slotId = field.slotId();
        Slots slots = clazz.staticVars();
        OperandStack stack = frame.operandStack();
        switch (descriptor.substring(0, 1)) {
            case "Z":
            case "B":
            case "C":
            case "S":
            case "I":
                stack.pushInt(slots.getInt(slotId));
                break;
            case "F":
                stack.pushFloat(slots.getFloat(slotId));
                break;
            case "J":
                stack.pushLong(slots.getLong(slotId));
                break;
            case "D":
                stack.pushDouble(slots.getDouble(slotId));
                break;
            case "L":
            case "[":
                stack.pushRef(slots.getRef(slotId));
                break;
            default:
                break;
        }
    }

}
