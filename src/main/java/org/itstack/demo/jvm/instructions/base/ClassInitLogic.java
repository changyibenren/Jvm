package org.itstack.demo.jvm.instructions.base;


import com.sun.tools.classfile.ConstantPoolException;
import com.sun.tools.classfile.Descriptor;
import org.itstack.demo.jvm.runtime.Frame;
import org.itstack.demo.jvm.runtime.methodarea.JMethod;
import org.itstack.demo.jvm.runtime.methodarea.Class;
import org.itstack.demo.jvm.runtime.Thread;

public class ClassInitLogic {

    public static void initClass(Thread thread, Class clazz) throws Descriptor.InvalidDescriptor, ConstantPoolException {
        clazz.startInit();
        scheduleClinit(thread, clazz);
        initSuperClass(thread, clazz);
    }

    private static void scheduleClinit(Thread thread, Class clazz) throws Descriptor.InvalidDescriptor, ConstantPoolException {
        JMethod clinit = clazz.getClinitMethod();
        if (null == clinit) return;
        Frame newFrame = thread.newFrame(clinit);
        thread.pushFrame(newFrame);
    }

    private static void initSuperClass(Thread thread, Class clazz) throws Descriptor.InvalidDescriptor, ConstantPoolException {
        if (clazz.isInterface()) return;
        Class superClass = clazz.superClass();
        if (null != superClass && !superClass.initStarted()) {
            initClass(thread, superClass);
        }
    }


}
