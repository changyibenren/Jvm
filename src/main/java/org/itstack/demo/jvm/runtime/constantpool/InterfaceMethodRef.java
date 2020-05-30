package org.itstack.demo.jvm.runtime.constantpool;

import com.sun.tools.classfile.ConstantPool;
import com.sun.tools.classfile.ConstantPoolException;
import org.itstack.demo.jvm.runtime.methodarea.JMethod;


public class InterfaceMethodRef extends MemberRef {

    private JMethod method;

    public static InterfaceMethodRef newInterfaceMethodRef(RunTimeConstantPool runTimeConstantPool, ConstantPool.CONSTANT_InterfaceMethodref_info refInfo) throws ConstantPoolException {
        InterfaceMethodRef ref = new InterfaceMethodRef();
        ref.runTimeConstantPool = runTimeConstantPool;
        ref.copyInterfaceMethodRefInfo(refInfo);
        //ref.copyMemberRefInfo(refInfo);
        return ref;
    }

    public JMethod resolvedInterfaceMethod() {
        if (this.method == null){
            this.resolveInterfaceMethodRef();
        }
        return this.method;
    }

    public void resolveInterfaceMethodRef(){

    }


}
