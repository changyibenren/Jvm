package org.itstack.demo.jvm.runtime.constantpool;


import com.sun.tools.classfile.ConstantPool;
import com.sun.tools.classfile.ConstantPoolException;
import org.itstack.demo.jvm.runtime.methodarea.JMethod;
import org.itstack.demo.jvm.runtime.methodarea.Class;
import org.itstack.demo.jvm.runtime.methodarea.MethodLookup;

public class MethodRef extends MemberRef {

    private JMethod method;

    public static MethodRef newMethodRef(RunTimeConstantPool runTimeConstantPool, ConstantPool.CONSTANT_Methodref_info refInfo) throws ConstantPoolException {
       MethodRef ref = new MethodRef();
       ref.runTimeConstantPool = runTimeConstantPool;
       ref.copyMethodRefInfo(refInfo);
        //ref.copyMemberRefInfo(refInfo);
       return ref;
    }

    public JMethod ResolvedMethod() throws ClassNotFoundException {
        if (null == this.method){
            this.resolveMethodRef();
        }
        return this.method;
    }

    private void resolveMethodRef() throws ClassNotFoundException {
        Class d = this.runTimeConstantPool.getClazz();
        Class c = this.resolvedClass();
        if (c.isInterface()) {
            throw new IncompatibleClassChangeError();
        }

        JMethod method = lookupMethod(c, this.name, this.descriptor);
        if (null == method){
            throw new NoSuchMethodError();
        }

        if (!method.isAccessibleTo(d)){
            throw new IllegalAccessError();
        }

        this.method = method;
    }

    public JMethod lookupMethod(Class clazz, String name, String descriptor) {
        JMethod method = MethodLookup.lookupMethodInClass(clazz, name, descriptor);
        if (null == method) {
            method = MethodLookup.lookupMethodInInterfaces(clazz.interfaces, name, descriptor);
        }
        return method;
    }
}
