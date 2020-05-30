package org.itstack.demo.jvm.runtime.methodarea;


import com.sun.tools.classfile.*;
import org.itstack.demo.jvm.runtime.ClassLoader;
import org.itstack.demo.jvm.runtime.Slots;
import org.itstack.demo.jvm.runtime.constantpool.RunTimeConstantPool;

import java.io.IOException;


public class Class {

    public AccessFlags accessFlags;  // 表示当前类的访问标志
    public String name;   //当前类名字(完全限定名)
    public String superClassName;  //父类名字(完全限定名)
    public String[] interfaceNames; //接口名字(完全限定名,不可以为null,若为实现接口,数组大小为0)
    public RunTimeConstantPool runTimeConstantPool; //运行时常量池
    public JField[] fields; //字段表,包括静态和非静态，此时并不分配 slotId；下面的staticVars 是其子集
    public JMethod[] methods; //方法表，包括静态和非静态
    public ClassLoader loader; //类加载器
    public Class superClass; //当前类的父类class,由类加载时,给父类赋值
    public Class[] interfaces;  //当前类的接口class,由类加载时,给父类赋值
    public int instanceSlotCount; //非静态变量占用slot大小,这里只是统计个数(从顶级父类Object开始算起)
    public int staticSlotCount; // 静态变量所占空间大小
    public Slots staticVars; // 存放静态变量
    public boolean initStarted; //判断类是否已经初始化，执行了类的<clinit>方法


    public Class(ClassFile classFile) throws ConstantPoolException, Descriptor.InvalidDescriptor, IOException {
        this.accessFlags = classFile.access_flags;
        this.name = classFile.getName();
        if(this.name.equals("java/lang/Object")){
            this.superClassName = "";
        }
        else if(!this.name.equals("java/lang/Object")){
            this.superClassName = classFile.getSuperclassName();
        }
        //this.interfaceNames = classFile.
        this.interfaceNames = new String[classFile.interfaces.length];
        for(int i=0;i<classFile.interfaces.length;i++){
                this.interfaceNames[i] = classFile.getInterfaceName(i);
        }
        this.runTimeConstantPool = new RunTimeConstantPool(this, classFile.constant_pool);
        //this.fields = classFile.fields;
       // this.methods = classFile.methods;
        this.fields = new JField().newFields(this, classFile.fields,classFile.constant_pool);
        //this.fields = new JField().newFields(this, MemberInfo.fields(classFile));
        //this.fields.setC
        this.methods = new JMethod().newMethods(this, classFile.methods,classFile.constant_pool);
       // this.methods = new JMethod().newMethods(this, MemberInfo.methods(classFile));
    }

    public boolean isPublic() {
        return 0 != (this.accessFlags.flags & AccessFlags.ACC_PUBLIC);
    }

    public boolean isFinal() {
        return 0 != (this.accessFlags.flags & AccessFlags.ACC_FINAL);
    }

    public boolean isSuper() {
        return 0 != (this.accessFlags.flags & AccessFlags.ACC_SUPER);
    }

    public boolean isInterface() {
        return 0 != (this.accessFlags.flags & AccessFlags.ACC_INTERFACE);
    }

    public boolean isAbstract() {
        return 0 != (this.accessFlags.flags & AccessFlags.ACC_ABSTRACT);
    }

    public boolean isSynthetic() {
        return 0 != (this.accessFlags.flags & AccessFlags.ACC_SYNTHETIC);
    }

    public boolean isAnnotation() {
        return 0 != (this.accessFlags.flags & AccessFlags.ACC_ANNOTATION);
    }

    public boolean isEnum() {
        return 0 != (this.accessFlags.flags & AccessFlags.ACC_ENUM);
    }

    public RunTimeConstantPool constantPool() {
        return this.runTimeConstantPool;
    }

    public Slots staticVars() {
        return this.staticVars;
    }

    public boolean isAccessibleTo(Class other) {
        return this.isPublic() || this.getPackageName().equals(other.getPackageName());
    }

    public String getPackageName() {
        int i = this.name.lastIndexOf("/");
        if (i >= 0) return this.name;
        return "";
    }

    public JMethod getMainMethod() throws Descriptor.InvalidDescriptor, ConstantPoolException {
        return this.getStaticMethod("main", "([Ljava/lang/String;)V");
    }

    private JMethod getStaticMethod(String name, String descriptor) throws ConstantPoolException, Descriptor.InvalidDescriptor {
        for (JMethod method : this.methods) {
            if (method.name.equals(name) && method.descriptor.equals(descriptor)) {
                return method;
            }
        }
        return null;
    }

    public Object newObject() {
        return new Object(this);
    }

    public boolean isAssignableFrom(Class other) {
        if (this == other) return true;
        if (!other.isInterface()) {
            return this.isSubClassOf(other);
        } else {
            return this.isImplements(other);
        }
    }

    public boolean isSubClassOf(Class other) {
        for (Class c = this.superClass; c != null; c = c.superClass) {
            if (c == other) {
                return true;
            }
        }
        return false;
    }

    private boolean isImplements(Class other) {

        for (Class c = this; c != null; c = c.superClass) {
            for (Class clazz : c.interfaces) {
                if (clazz == other || clazz.isSubInterfaceOf(other)) {
                    return true;
                }
            }
        }
        return false;

    }

    public boolean isSubInterfaceOf(Class iface) {
        for (Class superInterface : this.interfaces) {
            if (superInterface == iface || superInterface.isSubInterfaceOf(iface)) {
                return true;
            }
        }
        return false;
    }

    public boolean initStarted(){
        return this.initStarted;
    }

    public void startInit(){
        this.initStarted = true;
    }

    public JMethod getClinitMethod() throws Descriptor.InvalidDescriptor, ConstantPoolException {
        return this.getStaticMethod("<clinit>","()V");
    }

    public Class superClass() {
        return superClass;
    }

    public String name() {
        return name;
    }


}
