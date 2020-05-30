package org.itstack.demo.jvm.runtime.constantpool;

import org.itstack.demo.jvm.runtime.methodarea.Class;

public class SymRef {

    //存放符号引用所在的运行时常量池指针,可以通过符号引用访问到运行时常量池，进一步又可以访问到类数据
    public RunTimeConstantPool runTimeConstantPool;
    //存放类的完全限定名
    public String className;
    //上述运行时常量池的宿主类中的符号引用的真正类,在外面访问时，根据 clazz 是否为 null 来决定是否执行 loadClass
    public Class clazz;

    //类引用转直接引用，当前类(runtimeConstantPool的宿主类)d中,如果引用了类c,那么就将c加载进来
    public Class resolvedClass() throws ClassNotFoundException {
        if (null != this.clazz) return this.clazz;
        Class d = this.runTimeConstantPool.getClazz();
        Class c = d.loader.loadClass(this.className);
        this.clazz = c;
        return this.clazz;
    }

}
