package org.itstack.demo.jvm.runtime.methodarea;

import com.sun.tools.classfile.*;

import java.util.List;

public class JMethod extends ClassMember {

    public int maxStack;
    public int maxLocals;
    public byte[] code;
    private int argSlotCount;


    public JMethod[] newMethods(Class clazz, Method[] cfMethods,ConstantPool constantPool) throws ConstantPoolException, Descriptor.InvalidDescriptor {
        JMethod[] methods = new JMethod[cfMethods.length];
        for (int i = 0; i < cfMethods.length; i++) {
            methods[i] = new JMethod();
            methods[i].constantPool = constantPool;
            methods[i].clazz = clazz;
            methods[i].copyMethodInfo(cfMethods[i]);
            //methods[i].copyAttributes(cfMethods[i]);
            Attributes attributes = cfMethods[i].attributes;
            Code_attribute codeAttr = null;
            for (Attribute attrInfo : attributes) {
                if (attrInfo instanceof Code_attribute) codeAttr = (Code_attribute) attrInfo;
            }
            if (null != codeAttr) {
                methods[i].maxStack = codeAttr.max_stack;
                methods[i].maxLocals = codeAttr.max_locals;
                methods[i].code = codeAttr.code;
            }
            methods[i].calcArgSlotCount();
        }
        return methods;
    }

    private void calcArgSlotCount() {
        MethodDescriptor parsedDescriptor = MethodDescriptorParser.parseMethodDescriptorParser(this.descriptor);
        List<String> parameterTypes = parsedDescriptor.parameterTypes;
        for (String paramType : parameterTypes) {
            this.argSlotCount++;
            if ("J".equals(paramType) || "D".equals(paramType)) {
                this.argSlotCount++;
            }
        }
        if (!this.isStatic()) {
            this.argSlotCount++;
        }
    }


    /*public void copyAttributes(MemberInfo cfMethod) {
        Code_attribute codeAttr = cfMethod.code_attribute();
        if (null != codeAttr) {
            this.maxStack = codeAttr.max_stack;
            this.maxLocals = codeAttr.max_locals;
            this.code = codeAttr.code;
        }
    }*/

    /*public JMethod[] newMethods(Class clazz, MemberInfo[] cfMethods) throws ConstantPool.InvalidIndex, ConstantPool.UnexpectedEntry {
        JMethod[] methods = new JMethod[cfMethods.length];
        for (int i = 0; i < cfMethods.length; i++) {
            methods[i] = new JMethod();
            methods[i].clazz = clazz;
            methods[i].copyMemberInfo(cfMethods[i]);
            methods[i].copyAttributes(cfMethods[i]);
        }
        return methods;
    }

    public void copyAttributes(MemberInfo cfMethod) {
        Code_attribute codeAttr = cfMethod.code_attribute();
        if (null != codeAttr) {
            this.maxStack = codeAttr.max_stack;
            this.maxLocals = codeAttr.max_locals;
            this.code = codeAttr.code;
        }
    }*/

    public boolean isSynchronized() {
        return 0 != (this.accessFlags & AccessFlags.ACC_SYNCHRONIZED);
    }

    public boolean isBridge() {
        return 0 != (this.accessFlags & AccessFlags.ACC_BRIDGE);
    }

    public boolean isVarargs() {
        return 0 != (this.accessFlags & AccessFlags.ACC_VARARGS);
    }

    public boolean isNative() {
        return 0 != (this.accessFlags & AccessFlags.ACC_NATIVE);
    }

    public boolean isAbstract() {
        return 0 != (this.accessFlags & AccessFlags.ACC_ABSTRACT);
    }

    public boolean isStrict() {
        return 0 != (this.accessFlags & AccessFlags.ACC_STRICT);
    }

    public int maxStack() {
        return this.maxStack;
    }

    public int maxLocals() {
        return this.maxLocals;
    }

    public byte[] code() {
        return this.code;
    }

    public int argSlotCount() {
        return this.argSlotCount;
    }

}
