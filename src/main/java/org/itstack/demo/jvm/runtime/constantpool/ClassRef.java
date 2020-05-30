package org.itstack.demo.jvm.runtime.constantpool;

import com.sun.tools.classfile.ConstantPool;
import com.sun.tools.classfile.ConstantPoolException;


public class ClassRef extends SymRef {

    public static ClassRef newClassRef(RunTimeConstantPool runTimeConstantPool, ConstantPool.CONSTANT_Class_info classInfo) throws ConstantPoolException {
        ClassRef ref = new ClassRef();
        ref.runTimeConstantPool = runTimeConstantPool;
        ref.className = classInfo.getName();
        return ref;
    }

}
