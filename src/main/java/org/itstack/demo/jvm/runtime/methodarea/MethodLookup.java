package org.itstack.demo.jvm.runtime.methodarea;


public class MethodLookup {

    static public JMethod lookupMethodInClass(Class clazz, String name, String descriptor) {
        for (Class c = clazz; c != null; c = c.superClass) {
            for (JMethod method : c.methods) {
                if (method.name.equals(name) && method.descriptor.equals(descriptor)) {
                    return method;
                }
            }
        }
        return null;
    }

    static public JMethod lookupMethodInInterfaces(Class[] ifaces, String name, String descriptor) {
        for (Class inface : ifaces) {
            for (JMethod method : inface.methods) {
                if (method.name.equals(name) && method.descriptor.equals(descriptor)) {
                    return method;
                }
            }
        }
        return null;
    }

}
