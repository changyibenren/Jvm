package org.itstack.demo.jvm.runtime.methodarea;

import com.sun.tools.classfile.*;
import com.sun.tools.classfile.Field;
import com.sun.tools.classfile.Method;


public class ClassMember {

    public int accessFlags;
    public String name;
    public String descriptor;
    public Class clazz;
    public ConstantPool constantPool;

    public void setConstantPool(ConstantPool constantPool){
        this.constantPool = constantPool;
    }

    public void copyFieldInfo(Field field) throws ConstantPoolException, Descriptor.InvalidDescriptor {
        //field.
        this.accessFlags = field.access_flags.flags;
        this.name = field.getName(this.constantPool);
        //this.descriptor = field.descriptor.getFieldType(this.constantPool);
        this.descriptor = field.descriptor.getValue(this.constantPool);
    }

    public void copyMethodInfo(Method method) throws ConstantPoolException, Descriptor.InvalidDescriptor {
        this.accessFlags = method.access_flags.flags;
        this.name = method.getName(this.constantPool);
       // this.descriptor = method.descriptor.getFieldType(this.constantPool);
        this.descriptor = method.descriptor.getValue(this.constantPool);
    }

    public boolean isPublic() {
        return 0 != (this.accessFlags & AccessFlags.ACC_PUBLIC);
    }

    public boolean isPrivate() {
        return 0 != (this.accessFlags & AccessFlags.ACC_PRIVATE);
    }

    public boolean isProtected() {
        return 0 != (this.accessFlags & AccessFlags.ACC_PROTECTED);
    }

    public boolean isStatic() {
        return 0 != (this.accessFlags & AccessFlags.ACC_STATIC);
    }

    public boolean isFinal() {
        return 0 != (this.accessFlags & AccessFlags.ACC_FINAL);
    }

    public boolean isSynthetic() {
        return 0 != (this.accessFlags & AccessFlags.ACC_SYNTHETIC);
    }

    public String name() {
        return this.name;
    }

    public String descriptor() {
        return this.descriptor;
    }

    public Class clazz() {
        return this.clazz;
    }

    public boolean isAccessibleTo(Class d) {
        if (this.isPublic()) {
            return true;
        }
        Class c = this.clazz;
        if (this.isProtected()) {
            return d == c || c.getPackageName().equals(d.getPackageName());
        }
        if (!this.isPrivate()) {
            return c.getPackageName().equals(d.getPackageName());
        }
        return d == c;
    }
    /*public int accessFlags;
    public String name;
    public String descriptor;
    public Class clazz;

    public void copyMemberInfo(MemberInfo memberInfo) throws ConstantPool.UnexpectedEntry, ConstantPool.InvalidIndex {
        this.accessFlags = memberInfo.accessFlags();
        this.name = memberInfo.name();
        this.descriptor = memberInfo.descriptor();
    }

    public boolean isPublic() {
        return 0 != (this.accessFlags & AccessFlags.ACC_PUBLIC);
    }

    public boolean isPrivate() {
        return 0 != (this.accessFlags & AccessFlags.ACC_PRIVATE);
    }

    public boolean isProtected() {
        return 0 != (this.accessFlags & AccessFlags.ACC_PROTECTED);
    }

    public boolean isStatic() {
        return 0 != (this.accessFlags & AccessFlags.ACC_STATIC);
    }

    public boolean isFinal() {
        return 0 != (this.accessFlags & AccessFlags.ACC_FINAL);
    }

    public boolean isSynthetic() {
        return 0 != (this.accessFlags & AccessFlags.ACC_SYNTHETIC);
    }

    public String name() {
        return this.name;
    }

    public String descriptor() {
        return this.descriptor;
    }

    public Class clazz() {
        return this.clazz;
    }

    public boolean isAccessibleTo(Class d) {
        if (this.isPublic()) {
            return true;
        }
        Class c = this.clazz;
        if (this.isProtected()) {
            return d == c || c.getPackageName().equals(d.getPackageName());
        }
        if (!this.isPrivate()) {
            return c.getPackageName().equals(d.getPackageName());
        }
        return d == c;
    }*/

}
