package org.itstack.demo.jvm.runtime.constantpool;


import com.sun.tools.classfile.ConstantPool;
import com.sun.tools.classfile.ConstantPoolException;
import org.itstack.demo.jvm.runtime.methodarea.Class;
import org.itstack.demo.jvm.runtime.methodarea.JField;


public class FieldRef extends MemberRef {

    private JField field;

    public static FieldRef newFieldRef(RunTimeConstantPool runTimeConstantPool, ConstantPool.CONSTANT_Fieldref_info refInfo) throws ConstantPoolException {
        FieldRef ref = new FieldRef();
        ref.runTimeConstantPool = runTimeConstantPool;
        ref.copyFieldRefInfo(refInfo);
       // ref.copyMemberRefInfo(refInfo);
        return ref;
    }

    public JField resolvedField() {
        if (null == field) {
            try {
                this.resolveFieldRef();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return this.field;
    }

    private void resolveFieldRef() throws NoSuchFieldException, ClassNotFoundException {
        Class d = this.runTimeConstantPool.getClazz();
        Class c = this.resolvedClass();

        JField field = this.lookupField(c, this.name, this.descriptor);
        if (null == field){
            throw new NoSuchFieldException();
        }

        if (!field.isAccessibleTo(d)){
            throw new IllegalAccessError();
        }

        this.field = field;
    }

    private JField lookupField(Class c, String name, String descriptor) {
        for (JField field : c.fields) {
            if (field.name.equals(name) && field.descriptor.equals(descriptor)) {
                return field;
            }
        }

        for (Class iface : c.interfaces) {
            JField field = lookupField(iface, name, descriptor);
            if (null != field) return field;
        }

        if (c.superClass != null) {
            return lookupField(c.superClass, name, descriptor);
        }

        return null;
    }

}
